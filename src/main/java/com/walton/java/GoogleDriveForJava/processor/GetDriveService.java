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

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.drive.Drive;
import com.walton.java.accessgoogleservice.module.OAuth2Data;
import poisondog.core.Mission;

public class GetDriveService implements Mission<Credential>{
    private OAuth2Data oAuth2Data;
    public GetDriveService(OAuth2Data oAuth2Data){
        this.oAuth2Data = oAuth2Data;
    }
    @Override
    public Drive execute(Credential credential){
        HttpTransport httpTransport = oAuth2Data.getTransport();
        JsonFactory jsonFactory = oAuth2Data.getJsonFactory();
        Drive.Builder builder = new Drive.Builder(httpTransport,jsonFactory,credential);
        builder.setApplicationName("GoogleDriveForJava");
        return builder.build();
    }
}
