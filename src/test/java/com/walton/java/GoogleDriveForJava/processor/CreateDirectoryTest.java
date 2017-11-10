package com.walton.java.GoogleDriveForJava.processor;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class CreateDirectoryTest {
    @Test
    public void testExecute(){
        CreateDirectory createDirectory = new CreateDirectory();
        String directory = "test/test1/test2";
        createDirectory.execute(directory);
        File file = new File(directory);
        Boolean expected = true;
        Boolean actual = file.exists();
        Assert.assertEquals(expected,actual);
    }
}