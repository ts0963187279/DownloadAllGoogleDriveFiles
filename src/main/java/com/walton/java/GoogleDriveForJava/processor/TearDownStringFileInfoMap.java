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
