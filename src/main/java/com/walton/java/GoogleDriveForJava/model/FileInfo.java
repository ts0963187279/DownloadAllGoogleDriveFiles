package com.walton.java.GoogleDriveForJava.model;

public class FileInfo {
    private String parentId;
    private String title;
    private boolean parentIsRoot;
    private String mimeType;
    private String Id;
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public void setParentIsRoot(boolean parentIsRoot) {
        this.parentIsRoot = parentIsRoot;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getTitle() {
        return title;
    }
    public String getParentId() {
        return parentId;
    }
    public String getMimeType() {
        return mimeType;
    }
    public String getId() {
        return Id;
    }
    public boolean isParentIsRoot() {
        return parentIsRoot;
    }
}
