package com.walton.java.GoogleDriveForJava.processor;

import com.walton.java.GoogleDriveForJava.model.FileFormatInfo;
import poisondog.core.Mission;

import java.util.HashMap;
import java.util.Map;

public class IdentifyConversionFormat implements Mission<String>{
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
