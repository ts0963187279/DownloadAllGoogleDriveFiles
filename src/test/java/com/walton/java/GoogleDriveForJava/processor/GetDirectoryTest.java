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


import com.walton.java.GoogleDriveForJava.model.FileInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class GetDirectoryTest {
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
        String actual = new GetDirectory(stubFiles).execute(fileC);
        Assert.assertEquals(expected,actual);
        stubFiles = new TreeMap<String,FileInfo>();
        stubFiles.put("R",fileR);
        stubFiles.put("A",fileA);
        expected = "GoogleDrive/R/";
        actual = new GetDirectory(stubFiles).execute(fileA);
        Assert.assertEquals(expected,actual);
        stubFiles.put("R",fileR);
        expected = "GoogleDrive/";
        actual = new GetDirectory(stubFiles).execute(fileR);
        Assert.assertEquals(expected,actual);
    }
}