package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentList;
import com.google.api.services.drive.model.ParentReference;
import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.io.IOException;

public class PackageFileToFileInfo implements Mission<File> {
    private Drive drive;
    public PackageFileToFileInfo(Drive drive){
        this.drive = drive;
    }
    @Override
    public FileInfo execute(File file){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setTitle(file.getTitle());
        fileInfo.setMimeType(file.getMimeType());
        fileInfo.setId(file.getId());
        ParentList parentList = null;
        try {
            parentList = drive.parents().list(file.getId()).execute();
            for(ParentReference parentReference : parentList.getItems()){
                fileInfo.setParentId(parentReference.getId());
                fileInfo.setParentIsRoot(parentReference.getIsRoot());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }
}
