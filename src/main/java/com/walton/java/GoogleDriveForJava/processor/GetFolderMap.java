package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetFolderMap implements Mission<Drive>{
    @Override
    public Map<String,FileInfo> execute(Drive drive){
        String search = "mimeType = 'application/vnd.google-apps.folder'";
        FileList fileList = null;
        Map<String,FileInfo> folderInfoMap;
        try {
            fileList = drive.files().list().setQ(search).execute();
            folderInfoMap = new HashMap<String,FileInfo>();
            GetRootFolder getRootFolder = new GetRootFolder();
            File root = getRootFolder.execute(drive);
            FileInfo rootInfo = new FileInfo();
            rootInfo.setParentId(null);
            rootInfo.setId(root.getId());
            rootInfo.setMimeType(root.getMimeType());
            rootInfo.setTitle("MyGoogleDrive");
            rootInfo.setParentIsRoot(true);
            folderInfoMap.put(root.getId(),rootInfo);
            PackageFileToFileInfo packageFileToFileInfo = new PackageFileToFileInfo(drive);
            for(File file :fileList.getItems()){
                FileInfo fileInfo = packageFileToFileInfo.execute(file);
                folderInfoMap.put(file.getId(),fileInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        for(Map.Entry<String,FileInfo> entry : folderInfoMap.entrySet()){
            System.out.println("folder : " + entry.getValue().getTitle());
        }
        return folderInfoMap;
    }
}
