package com.walton.java.GoogleDriveForJava.processor;

import poisondog.core.Mission;

import java.io.File;

public class CreateDirectory implements Mission<String>{
    @Override
    public Void execute(String directory){
        File file = new File(directory);
        file.mkdirs();
        return null;
    }
}
