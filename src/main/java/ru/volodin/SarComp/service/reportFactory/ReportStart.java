package ru.volodin.SarComp.service.reportFactory;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.volodin.SarComp.entity.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class ReportStart implements ReportProcessor {

    private String templatePath = "storage/reports/templates/start.docx";
    private String uploadPath = "D:/JavaProjects/SarComp/src/main/resources/storage/reports/start/";

    @Override
    public Report process(Report report) throws IOException {
        try (InputStream docxStream = new ClassPathResource(templatePath).getInputStream()) {
            report.setFilePath(uploadPath + UUID.randomUUID() + ".docx");
            try (OutputStream outputStream = new FileOutputStream(report.getFilePath())) {
                Configure config = Configure.builder()
                        .bind("addition", new LoopRowTableRenderPolicy()).build();

                XWPFTemplate.compile(docxStream, config)
                        .render(prepareTemplate(report))
                        .writeAndClose(outputStream);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }

        report.setReportName("Start_" + report.getDocNumber()+ "_" + report.getClient().getName() + ".docx");

        return report;
    }

    private Map<String, Object> prepareTemplate(Report report) {
        Map<String, Object> data = new HashMap<>();

        long orderDays = countDaysBetween(report.getComp().getStartOrder(), report.getComp().getEndOrder());

        data.put("docNumber", report.getDocNumber());
        data.put("nowDate", LocalDate.now());
        data.put("clientName", report.getClient().getName());
        data.put("CompCost", report.getComp().getCost());
        data.put("days", orderDays);
        data.put("clientPhone", report.getClient().getPhone());

        return data;
    }

    private long countDaysBetween(Date startOrder, Date endOrder) {
        return ChronoUnit.DAYS.between(
                startOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }
}
