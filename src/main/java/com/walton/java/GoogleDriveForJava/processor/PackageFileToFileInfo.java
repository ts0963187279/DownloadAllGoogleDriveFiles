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
