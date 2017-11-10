package com.walton.java.GoogleDriveForJava.processor;

import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchDirectory implements Mission<FileInfo>{
    private Map<String,FileInfo> files;
    private String path = "GoogleDrive/";
    public SearchDirectory(Map<String,FileInfo> files){
        this.files = files;
    }
    @Override
    public String execute(FileInfo fileInfo){
        List<String> directoryTmp = new ArrayList<String>();
        try{
            FileInfo parentInfo = files.get(fileInfo.getParentId());
            int i;
            for(i=0;!parentInfo.isParentIsRoot();i++) {
                directoryTmp.add(parentInfo.getTitle());
                parentInfo = files.get(parentInfo.getParentId());
            }
            directoryTmp.add(parentInfo.getTitle());
            parentInfo = files.get(parentInfo.getParentId());
            directoryTmp.add(parentInfo.getTitle());
            for(int j=i+1;j>=0;j--)
                path += directoryTmp.get(j)+"/";
        }catch (NullPointerException e){
            try {
                path += files.get(fileInfo.getParentId()).getTitle() + "/";
            }catch (NullPointerException e1){
                return path;
            }
        }
        return path;
    }
}
