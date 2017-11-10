package com.walton.java.GoogleDriveForJava.processor;

import com.walton.java.GoogleDriveForJava.model.FileFormatInfo;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdentifyConversionFormatTest {
    @Test
    public void testExecute(){
        IdentifyConversionFormat identifyConversionFormat = new IdentifyConversionFormat();
        FileFormatInfo expected = new FileFormatInfo();
        FileFormatInfo actual = identifyConversionFormat.execute("application/zip");
        Assert.assertEquals(expected.getCorrespondingMimeType(),actual.getCorrespondingMimeType());
        Assert.assertEquals(expected.getExtension(),actual.getExtension());
        actual = identifyConversionFormat.execute("image/png");
        Assert.assertEquals(expected.getCorrespondingMimeType(),actual.getCorrespondingMimeType());
        Assert.assertEquals(expected.getExtension(),actual.getExtension());
        expected.setCorrespondingMimeType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        expected.setExtension(".docx");
        actual = identifyConversionFormat.execute("application/vnd.google-apps.document");
        Assert.assertEquals(expected.getCorrespondingMimeType(),actual.getCorrespondingMimeType());
        Assert.assertEquals(expected.getExtension(),actual.getExtension());
    }
}