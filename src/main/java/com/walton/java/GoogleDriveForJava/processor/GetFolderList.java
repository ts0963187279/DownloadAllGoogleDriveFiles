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
import com.walton.java.GoogleDriveForJava.model.SearchFileInfo;
import poisondog.core.Mission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFolderList implements Mission<Drive>{
    @Override
    public List<SearchFileInfo> execute(Drive drive){
        String search = "mimeType = 'application/vnd.google-apps.folder'";
        FileList fileList = null;
        List<SearchFileInfo> folderInfoList;
        try {
            fileList = drive.files().list().setQ(search).execute();
            folderInfoList = new ArrayList<SearchFileInfo>();
            GetRootFolder getRootFolder = new GetRootFolder();
            File root = getRootFolder.execute(drive);
            SearchFileInfo rootInfo = new SearchFileInfo();
            rootInfo.setParentId(null);
            rootInfo.setId(root.getId());
            rootInfo.setMimeType(root.getMimeType());
            rootInfo.setTitle("MyGoogleDrive");
            rootInfo.setParentIsRoot(true);
            folderInfoList.add(rootInfo);
            PackageFileToSearchFileInfo packageFileToSearchFileInfo = new PackageFileToSearchFileInfo(drive);
            for(File file :fileList.getItems()){
                SearchFileInfo searchFileInfo = packageFileToSearchFileInfo.execute(file);
                folderInfoList.add(searchFileInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return folderInfoList;
    }
}
