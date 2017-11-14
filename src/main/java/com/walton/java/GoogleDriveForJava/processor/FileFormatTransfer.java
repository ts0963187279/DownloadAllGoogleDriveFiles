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

import com.walton.java.GoogleDriveForJava.model.FileFormatInfo;
import poisondog.core.Mission;

import java.util.HashMap;
import java.util.Map;

public class FileFormatTransfer implements Mission<String>{
    /**
     * https://developers.google.com/drive/v3/web/manage-downloads
    **/
    private static final Map<String,String> googleFormatConvertMap = new HashMap<String,String>(){
        {
            put("application/vnd.google-apps.folder","folder");
            put("application/vnd.google-apps.document","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            put("application/vnd.google-apps.spreadsheet","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            put("application/vnd.google-apps.presentation","application/vnd.openxmlformats-officedocument.presentationml.presentation");
            put("application/vnd.google-apps.drawings","image/jpeg");
        }
    };
    private static final Map<String,String> extensionMap = new HashMap<String,String>(){
        {
            put("application/vnd.openxmlformats-officedocument.wordprocessingml.document",".docx");
            put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",".xlsx");
            put("application/vnd.openxmlformats-officedocument.presentationml.presentation",".pptx");
            put("image/jpeg",".jpg");
        }
    };
    @Override
    public FileFormatInfo execute(String mimeType) {
        FileFormatInfo fileFormatInfo = new FileFormatInfo();
        String correspondingMimeType = googleFormatConvertMap.get(mimeType);
        fileFormatInfo.setCorrespondingMimeType(correspondingMimeType);
        fileFormatInfo.setExtension(extensionMap.get(correspondingMimeType));
        return fileFormatInfo;
    }
}
