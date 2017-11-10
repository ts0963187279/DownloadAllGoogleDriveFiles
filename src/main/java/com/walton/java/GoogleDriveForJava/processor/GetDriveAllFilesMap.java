/*
 * Copyright (C) 2017 RS Wong <ts0963187279@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
