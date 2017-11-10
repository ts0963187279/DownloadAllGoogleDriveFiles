package com.walton.java.GoogleDriveForJava.processor;


import com.walton.java.GoogleDriveForJava.model.FileInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class SearchDirectoryTest {
    @Test
    public void testExecute(){
        Map<String,FileInfo> stubFiles = new TreeMap<String,FileInfo>();
        FileInfo fileR = new FileInfo();
        fileR.setTitle("R");
        fileR.setId("R");
        stubFiles.put("R",fileR);
        FileInfo fileA = new FileInfo();
        fileA.setParentIsRoot(true);
        fileA.setTitle("A");
        fileA.setId("A");
        fileA.setParentId("R");
        stubFiles.put("A",fileA);
        FileInfo fileB = new FileInfo();
        fileB.setTitle("B");
        fileB.setId("B");
        fileB.setParentIsRoot(false);
        fileB.setParentId("A");
        stubFiles.put("B",fileB);
        FileInfo fileC = new FileInfo();
        fileC.setTitle("C");
        fileC.setId("C");
        fileC.setParentIsRoot(false);
        fileC.setParentId("B");
        String expected = "GoogleDrive/R/A/B/";
        String actual = new SearchDirectory(stubFiles).execute(fileC);
        Assert.assertEquals(expected,actual);
        stubFiles = new TreeMap<String,FileInfo>();
        stubFiles.put("R",fileR);
        stubFiles.put("A",fileA);
        expected = "GoogleDrive/R/";
        actual = new SearchDirectory(stubFiles).execute(fileA);
        Assert.assertEquals(expected,actual);
        stubFiles.put("R",fileR);
        expected = "GoogleDrive/";
        actual = new SearchDirectory(stubFiles).execute(fileR);
        Assert.assertEquals(expected,actual);
    }
}