package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import poisondog.core.Mission;

import java.io.IOException;


public class GetRootFolder implements Mission<Drive>{
    @Override
    public File execute(Drive drive){
        File root = null;
        try {
            String rootId = drive.about().get().execute().getRootFolderId();
            root = drive.files().get(rootId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
