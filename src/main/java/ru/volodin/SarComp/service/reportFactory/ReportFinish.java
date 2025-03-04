package ru.volodin.SarComp.service.reportFactory;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.springframework.core.io.ClassPathResource;
import ru.volodin.SarComp.entity.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReportFinish implements ReportProcessor {

    private String templatePath = "resources/storage/reports/templates/finish";
    private String uploadPath = "resources/storage/reports/finish";

    @Override
    public Report process(Report report) throws IOException {
        try (InputStream docxStream = new ClassPathResource(templatePath).getInputStream()) {
            report.setFilePath(uploadPath + UUID.randomUUID() + ".docx");
            try (OutputStream outputStream = new FileOutputStream(report.getFilePath())) {
                Configure config = Configure.builder()
                        .bind("services", new LoopRowTableRenderPolicy()).build();

                XWPFTemplate.compile(docxStream, config)
                        .render(prepareTemplate(report))
                        .writeAndClose(outputStream);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }

        report.setReportName("Finish_" + report.getDocNumber()+ "_" + report.getClient().getName() + ".docx");

        return report;
    }

    private Map<String, Object> prepareTemplate(Report report) {
        Map<String, Object> data = new HashMap<>();

        //TODO накидать данных
        data.put("docNumber", report.getDocNumber());

        return data;
    }
}
