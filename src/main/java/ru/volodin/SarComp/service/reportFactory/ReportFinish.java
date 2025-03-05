package ru.volodin.SarComp.service.reportFactory;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.volodin.SarComp.entity.Addition;
import ru.volodin.SarComp.entity.Report;
import ru.volodin.SarComp.entity.dto.AdditionalRow;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class ReportFinish implements ReportProcessor {

    private String templatePath = "storage/reports/templates/finish.docx";
    private String uploadPath = "D:/JavaProjects/SarComp/src/main/resources/storage/reports/finish/";

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

        long orderDays = countDaysBetween(report.getComp().getStartOrder(), report.getComp().getEndOrder());
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        float additionRentCost = report.getAdditions() != null ?
                (float) report.getAdditions().stream().mapToDouble(Addition::getCost).sum() : 0;
        float compRentCost = orderDays * report.getComp().getCost();
        int serviceCount = report.getAdditions() != null ? report.getAdditions().size() + 1 : 1;
        float totallyPrice = compRentCost + additionRentCost;

        data.put("docNumber", report.getDocNumber());
        data.put("docEndDate", formatter.format(report.getComp().getEndOrder()));
        data.put("clientName", report.getClient().getName());
        data.put("docCompName", report.getComp().getName());
        data.put("docStartDate", formatter.format(report.getComp().getStartOrder()));
        data.put("docDays", orderDays);
        data.put("docCost", report.getComp().getCost().intValue());
        data.put("docPrice", (int) compRentCost);
        data.put("services", builAdditional(report));
        data.put("totallyPrice", (int) totallyPrice);
        data.put("sCount", serviceCount);
        data.put("clientPhone", report.getClient().getPhone());

        return data;
    }

    private List<AdditionalRow> builAdditional (Report report) {
        List<AdditionalRow> rows = new ArrayList<>();

        int i = 2;
        for (Addition addition : report.getAdditions()) {
            rows.add(AdditionalRow.builder()
                    .index(String.valueOf(i))
                    .name(addition.getName())
                    .cost(String.valueOf(addition.getCost()))
                    .build());
            i++;
        }

        return rows;
    }

    private long countDaysBetween(Date startOrder, Date endOrder) {
        return ChronoUnit.DAYS.between(
                startOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }
}
