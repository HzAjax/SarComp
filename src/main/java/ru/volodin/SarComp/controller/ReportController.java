package ru.volodin.SarComp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.Report;
import ru.volodin.SarComp.service.ReportService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/reports")
@SuppressWarnings({"unused"})
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<?> addReport(@RequestBody @Valid Report report) {
        try {
            Report processed = reportService.addReport(report);

            FileSystemResource resource = new FileSystemResource(processed.getFilePath());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, //TODO: URLEncoded works?
                    "attachment; filename=" + URLEncoder.encode(processed.getReportName(), StandardCharsets.UTF_8));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReportById(@PathVariable("reportId") UUID reportId) {
        try {
            return ResponseEntity.ok(reportService.getReportById(reportId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteById(@PathVariable("reportId") UUID reportId) {
        try {
            reportService.deleteById(reportId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
