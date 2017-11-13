package com.walton.java.GoogleDriveForJava.processor;

import com.walton.java.GoogleDriveForJava.model.DownloadInfo;
import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TearDownStringFileInfoMap implements Mission<Map<String,FileInfo>>{
    @Override
    public List<DownloadInfo> execute(Map<String, FileInfo> stringFileInfoMap){
        List<DownloadInfo> downloadInfoList = new ArrayList<DownloadInfo>();
        for(Map.Entry<String,FileInfo> entry : stringFileInfoMap.entrySet()){
            DownloadInfo downloadInfo = new DownloadInfo();
            String directory = new GetDirectory(stringFileInfoMap).execute(entry.getValue());
            downloadInfo.setDirectory(directory);
            downloadInfo.setFileInfo(entry.getValue());
            downloadInfoList.add(downloadInfo);
        }
        return downloadInfoList;
    }
}
