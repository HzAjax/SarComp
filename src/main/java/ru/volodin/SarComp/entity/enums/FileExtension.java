package ru.volodin.SarComp.entity.enums;

import lombok.Getter;

@Getter
public enum FileExtension {
    JPG(".jpg", "image/jpeg"),
    PNG(".png", "image/png"),
    WEBP(".webp", "image/webp");

    private final String extension;
    private final String mimeType;

    FileExtension(String extension, String mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }
}
