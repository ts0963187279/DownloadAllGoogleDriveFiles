package com.walton.java.GoogleDriveForJava.model;

public class FileFormatInfo {
    private String correspondingMimeType;
    private String extension;

    public void setCorrespondingMimeType(String correspondingMimeType) {
        this.correspondingMimeType = correspondingMimeType;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getCorrespondingMimeType() {
        return correspondingMimeType;
    }

    public String getExtension() {
        return extension;
    }
}
