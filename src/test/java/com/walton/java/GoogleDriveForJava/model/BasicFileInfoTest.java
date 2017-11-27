package com.walton.java.GoogleDriveForJava.model;

import org.junit.Assert;
import org.junit.Test;


public class BasicFileInfoTest {
    @Test
    public void testSetTitle(){
        BasicFileInfo basicFileInfo = new BasicFileInfo();
        basicFileInfo.setTitle("|\\/:*");
        String expected = "｜＼／：＊";
        String actual = basicFileInfo.getTitle();
        Assert.assertEquals(expected,actual);
    }
}