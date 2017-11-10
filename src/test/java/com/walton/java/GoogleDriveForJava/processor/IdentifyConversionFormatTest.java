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