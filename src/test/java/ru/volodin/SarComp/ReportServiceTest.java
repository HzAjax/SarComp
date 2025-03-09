package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.entity.Report;
import ru.volodin.SarComp.repository.AdditionRepository;
import ru.volodin.SarComp.repository.ClientRepository;
import ru.volodin.SarComp.repository.CompRepository;
import ru.volodin.SarComp.repository.ReportRepository;
import ru.volodin.SarComp.service.ReportService;
import ru.volodin.SarComp.service.reportFactory.ReportFactory;
import ru.volodin.SarComp.service.reportFactory.ReportProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;

    @MockitoBean
    private ReportRepository reportMockRepo;

    @MockitoBean
    private CompRepository compMockRepo;

    @MockitoBean
    private ClientRepository clientMockRepo;

    @MockitoBean
    private AdditionRepository additMockRepo;

    @MockitoBean
    private ReportFactory reportFactory;

    @Mock
    private ReportProcessor reportProcessor;

    private Report report;
    private Comp comp;
    private Client client;
    private Addition addition;
    private UUID reportId;
    private UUID compId;
    private UUID clientId;
    private UUID additionId;

    @BeforeEach
    void setUp() throws IOException {
        reportId = UUID.randomUUID();
        compId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        additionId = UUID.randomUUID();

        comp = new Comp();
        comp.setId(compId);

        client = new Client();
        client.setId(clientId);

        addition = new Addition();
        addition.setId(additionId);

        report = new Report();
        report.setId(reportId);
        report.setComp(comp);
        report.setClient(client);
        report.setAdditions(List.of(addition));
        report.setFilePath("test-report.pdf");

        when(reportFactory.getProcessor(any())).thenReturn(reportProcessor);
        when(reportProcessor.process(any())).thenReturn(report);
    }

    @Test
    void addReport_Success() throws IOException {
        when(compMockRepo.findById(compId)).thenReturn(Optional.of(comp));
        when(clientMockRepo.findById(clientId)).thenReturn(Optional.of(client));
        when(additMockRepo.findAllById(any())).thenReturn(List.of(addition));
        when(reportMockRepo.save(any(Report.class))).thenReturn(report);

        Report savedReport = reportService.addReport(report);

        assertNotNull(savedReport);
        assertEquals(report.getId(), savedReport.getId());
        verify(reportMockRepo, times(1)).save(any(Report.class));
    }

    @Test
    void addReport_ThrowsException_WhenCompNotFound() {
        when(compMockRepo.findById(compId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reportService.addReport(report));
        assertEquals("Авто не был найден в БД!", exception.getMessage());
    }

    @Test
    void addReport_ThrowsException_WhenClientNotFound() {
        when(compMockRepo.findById(compId)).thenReturn(Optional.of(comp));
        when(clientMockRepo.findById(clientId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reportService.addReport(report));
        assertEquals("Клиент не был найден в БД!", exception.getMessage());
    }

    @Test
    void getReportById_Success() {
        when(reportMockRepo.findById(reportId)).thenReturn(Optional.of(report));

        Report foundReport = reportService.getReportById(reportId);

        assertNotNull(foundReport);
        assertEquals(reportId, foundReport.getId());
    }

    @Test
    void getReportById_ThrowsException_WhenNotFound() {
        when(reportMockRepo.findById(reportId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reportService.getReportById(reportId));
        assertEquals("Отчет не найден!", exception.getMessage());
    }

    @Test
    void deleteById_Success() throws IOException {
        when(reportMockRepo.findById(reportId)).thenReturn(Optional.of(report));
        doNothing().when(reportMockRepo).deleteById(reportId);
        Files.createFile(Paths.get(report.getFilePath())); // Создаём файл, чтобы он удалялся

        reportService.deleteById(reportId);

        verify(reportMockRepo, times(1)).deleteById(reportId);
    }

    @Test
    void deleteById_ThrowsException_WhenNotFound() {
        when(reportMockRepo.findById(reportId)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> reportService.deleteById(reportId));
        assertEquals("Отчет не найден!", exception.getMessage());
    }
}
