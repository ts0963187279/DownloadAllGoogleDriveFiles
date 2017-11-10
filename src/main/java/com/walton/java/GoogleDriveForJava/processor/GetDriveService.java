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
