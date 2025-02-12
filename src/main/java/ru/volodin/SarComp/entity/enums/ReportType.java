package ru.volodin.SarComp.entity.enums;

@SuppressWarnings({"unused"})
public enum ReportType {
    START("start"),    //Договор проката
    FINISH("finish");     //Акт выполненных работ

    public String type;

    ReportType(String type) {
        this.type = type;
    }
}
