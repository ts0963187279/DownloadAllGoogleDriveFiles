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
import com.walton.java.GoogleDriveForJava.model.DownloadFileInfo;
import com.walton.java.GoogleDriveForJava.model.FileFormatInfo;
import poisondog.core.Mission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadDriveFiles implements Mission<DownloadFileInfo>{
    private Drive drive;
    private String systemDirectory = "";
    public DownloadDriveFiles(Drive drive){
        this.drive = drive;
    }
    public void setSystemDirectory(String systemDirectory) {
        this.systemDirectory = systemDirectory;
    }
    public Void execute(DownloadFileInfo downloadFileInfo){
        String directory = downloadFileInfo.getDirectory();
        String path = systemDirectory + directory + "/" + downloadFileInfo.getTitle();
        try {
            CreateDirectory createDirectory = new CreateDirectory();
            createDirectory.execute(systemDirectory + directory);
            FileFormatTransfer fileFormatTransfer = new FileFormatTransfer();
            FileFormatInfo fileFormatInfo = fileFormatTransfer.execute(downloadFileInfo.getMimeType());
            String correspondingMimeType = fileFormatInfo.getCorrespondingMimeType();
            System.out.println("Start download" +downloadFileInfo.getTitle());
            if (correspondingMimeType == null) {
                File file = new File(path);
                OutputStream outputStream = new FileOutputStream(file);
                drive.files().get(downloadFileInfo.getId()).executeMediaAndDownloadTo(outputStream);
            } else if (correspondingMimeType == "folder") {
                File file = new File(path);
                file.mkdirs();
            } else {
                File file = new File(path + fileFormatInfo.getExtension());
                OutputStream outputStream = new FileOutputStream(file);
                drive.files().export(downloadFileInfo.getId(), correspondingMimeType).executeMediaAndDownloadTo(outputStream);
            }
            System.out.println("download " + downloadFileInfo.getTitle() + " done!");
        } catch (IOException e) {
            System.out.println("exception");
            System.out.println(downloadFileInfo.getTitle());
            System.out.println(downloadFileInfo.getMimeType());
            e.printStackTrace();
        }
        return null;
    }
}
