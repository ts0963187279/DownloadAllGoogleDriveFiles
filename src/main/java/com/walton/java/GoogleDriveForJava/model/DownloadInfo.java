package com.walton.java.GoogleDriveForJava.model;

public class DownloadInfo {
    private FileInfo fileInfo;
    private String directory;
    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }
}
