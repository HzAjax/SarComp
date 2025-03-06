package ru.volodin.SarComp.service.reportFactory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.volodin.SarComp.entity.enums.ReportType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ReportFactory {
    private Map<ReportType, ReportProcessor> processorMap;

    private String uploadPathStart = "resources/storage/reports/start";
    private String uploadPathFinish = "resources/storage/reports/finish";

    @Autowired
    private ReportStart reportStart;

    @Autowired
    private ReportFinish reportFinish;

    @PostConstruct
    public void init() {
        processorMap = new HashMap<>();
        processorMap.put(ReportType.START, reportStart);
        processorMap.put(ReportType.FINISH, reportFinish);

        try {
            Files.createDirectories(Path.of(uploadPathStart));
            Files.createDirectories(Path.of(uploadPathFinish));
        } catch (IOException e) {
            log.error("Error creating doc upload directories");
            throw new RuntimeException(e);
        }
    }

    public ReportProcessor getProcessor(ReportType reportType) {
        return processorMap.getOrDefault(reportType,
                report -> {
                    throw new IllegalArgumentException("Unknown report type: " + reportType);
                });
    }
}
