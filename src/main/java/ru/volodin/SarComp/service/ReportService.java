package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.entity.Client;
import ru.volodin.SarComp.entity.Comp;
import ru.volodin.SarComp.entity.Report;
import ru.volodin.SarComp.repository.AdditionRepository;
import ru.volodin.SarComp.repository.ClientRepository;
import ru.volodin.SarComp.repository.CompRepository;
import ru.volodin.SarComp.repository.ReportRepository;
import ru.volodin.SarComp.service.reportFactory.ReportFactory;
import ru.volodin.SarComp.service.reportFactory.ReportProcessor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@SuppressWarnings({"unused"})
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CompRepository compRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdditionRepository additionRepository;

    @Autowired
    private ReportFactory reportFactory;

    public Report addReport(Report report) throws IOException {
        Comp enrichComp = compRepository.findById(report.getComp().getId()).orElseThrow(() ->
                new NoSuchElementException("Авто не был найден в БД!"));

        Client enrichClient = clientRepository.findById(report.getClient().getId()).orElseThrow(() ->
                new NoSuchElementException("Клиент не был найден в БД!"));

        List<Addition> enrichAdditional = additionRepository.findAllById(report.getAdditions().stream() //TODO: check services null
                .map(Addition::getId)
                .toList());

        report.setComp(enrichComp);
        report.setClient(enrichClient);
        report.setAdditions(enrichAdditional);
        report.setCreateTs(LocalDateTime.now());

        ReportProcessor processor = reportFactory.getProcessor(report.getType());
        Report processedReport = processor.process(report);

        return reportRepository.save(report);
    }

    public Report getReportById(UUID id) {
        return reportRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Отчет не найден!"));
    }

    public void deleteById(UUID id) {
        reportRepository.deleteById(id);
    }
}
