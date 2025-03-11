package ru.volodin.SarComp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.volodin.SarComp.entity.Report;
import ru.volodin.SarComp.service.ReportService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockMvc).build();
    }

    @Test
    void addReport() throws Exception {
        Report report = new Report();
        report.setReportName("Test Report");
        report.setFilePath("/path/to/report");

        when(reportService.addReport(any(Report.class))).thenReturn(report);

        mockMvc.perform(post("/sarcomp/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reportName\":\"Test Report\",\"filePath\":\"/path/to/report\"}"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Test+Report"))
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));

        verify(reportService, times(1)).addReport(any(Report.class));
    }

    @Test
    void getReportById() throws Exception {
        UUID reportId = UUID.randomUUID();
        Report report = new Report();
        report.setReportName("Test Report");
        report.setFilePath("/path/to/report");

        when(reportService.getReportById(reportId)).thenReturn(report);

        mockMvc.perform(get("/sarcomp/reports/{reportId}", reportId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reportName").value("Test Report"))
                .andExpect(jsonPath("$.filePath").value("/path/to/report"));

        verify(reportService, times(1)).getReportById(reportId);
    }

    @Test
    void deleteById() throws Exception {
        UUID reportId = UUID.randomUUID();

        doNothing().when(reportService).deleteById(reportId);

        mockMvc.perform(delete("/sarcomp/reports/{reportId}", reportId))
                .andExpect(status().isOk());

        verify(reportService, times(1)).deleteById(reportId);
    }
}