package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetDriveAllFilesMap implements Mission<Map<String,FileInfo>>{
    private Drive drive;
    public GetDriveAllFilesMap(Drive drive){
        this.drive = drive;
    }
    @Override
    public Map<String,FileInfo> execute(Map<String,FileInfo> folderInfoMap){
        Map<String,FileInfo> fileInfoMap = new HashMap<String,FileInfo>();
        fileInfoMap.putAll(folderInfoMap);
        PackageFileToFileInfo packageFileToFileInfo = new PackageFileToFileInfo(drive);
        for(Map.Entry<String,FileInfo> entry : folderInfoMap.entrySet()){
            FileInfo folderInfo = entry.getValue();
            System.out.println("folder : " + entry.getValue().getTitle() + "'s child :");
            ChildList childList = null;
            try {
                childList = drive.children().list(folderInfo.getId()).execute();
                for(ChildReference childReference : childList.getItems()){
                    String childId = childReference.getId();
                    File file = drive.files().get(childId).execute();
                    System.out.println("    file name : " +file.getTitle() +"  mime type : "+file.getMimeType());
                    FileInfo fileInfo = packageFileToFileInfo.execute(file);
                    fileInfoMap.put(fileInfo.getId(),fileInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileInfoMap;
    }
}
