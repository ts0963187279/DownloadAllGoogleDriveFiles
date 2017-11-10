package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.services.drive.Drive;
import com.walton.java.GoogleDriveForJava.model.DownloadInfo;
import com.walton.java.GoogleDriveForJava.model.FileFormatInfo;
import com.walton.java.GoogleDriveForJava.model.FileInfo;
import poisondog.core.Mission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadDriveFile implements Mission<DownloadInfo>{
    private Drive drive;
    public DownloadDriveFile(Drive drive){
        this.drive = drive;
    }
    @Override
    public Boolean execute(DownloadInfo downloadInfo){
        FileInfo fileInfo = downloadInfo.getFileInfo();
        String directory = downloadInfo.getDirectory();
        String path = directory + "/" +fileInfo.getTitle();
        try {
            CreateDirectory createDirectory = new CreateDirectory();
            createDirectory.execute(directory);
            IdentifyConversionFormat identifyConversionFormat = new IdentifyConversionFormat();
            FileFormatInfo fileFormatInfo = identifyConversionFormat.execute(fileInfo.getMimeType());
            String correspondingMimeType = fileFormatInfo.getCorrespondingMimeType();
            if(correspondingMimeType == null){
                File file = new File(path);
                OutputStream outputStream = new FileOutputStream(file);
                drive.files().get(fileInfo.getId()).executeMediaAndDownloadTo(outputStream);
            }else if(correspondingMimeType == "folder"){
                File file = new File(path);
                file.mkdirs();
            }else{
                File file = new File(path + fileFormatInfo.getExtension());
                OutputStream outputStream = new FileOutputStream(file);
                drive.files().export(fileInfo.getId(),correspondingMimeType).executeMediaAndDownloadTo(outputStream);
            }
            System.out.println("download "+ fileInfo.getTitle() + " done!");
            return true;
        }catch (IOException e){
            System.out.println("exception");
            System.out.println(fileInfo.getTitle());
            System.out.println(fileInfo.getMimeType());
            e.printStackTrace();
            return false;
        }
    }
}
