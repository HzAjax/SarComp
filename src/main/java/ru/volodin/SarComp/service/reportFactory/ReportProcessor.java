package ru.volodin.SarComp.service.reportFactory;

import ru.volodin.SarComp.entity.Report;

import java.io.IOException;

public interface ReportProcessor {
    Report process(Report report) throws IOException;
}
