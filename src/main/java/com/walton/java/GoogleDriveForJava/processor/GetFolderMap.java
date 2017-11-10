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
