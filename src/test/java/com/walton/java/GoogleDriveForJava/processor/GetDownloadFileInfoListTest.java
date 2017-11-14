package com.walton.java.GoogleDriveForJava.processor;

import com.google.api.client.util.ArrayMap;
import com.walton.java.GoogleDriveForJava.model.DownloadFileInfo;
import com.walton.java.GoogleDriveForJava.model.SearchFileInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetDownloadFileInfoListTest {
    @Test
    public void testExecute(){
        GetDownloadFileInfoList getDownloadFileInfoList = new GetDownloadFileInfoList();
        DownloadFileInfo stubDownloadFileInfo = new DownloadFileInfo();
        stubDownloadFileInfo.setDirectory("GoogleDrive/");
        stubDownloadFileInfo.setId("Id");
        stubDownloadFileInfo.setMimeType("MimeType");
        stubDownloadFileInfo.setTitle("Title");
        SearchFileInfo searchFileInfo = new SearchFileInfo();
        searchFileInfo.setParentId("ParentId");
        searchFileInfo.setParentIsRoot(true);
        searchFileInfo.setId("Id");
        searchFileInfo.setMimeType("MimeType");
        searchFileInfo.setTitle("Title");
        Map<String,SearchFileInfo> stubSearchFileInfoMap = new ArrayMap<String,SearchFileInfo>();
        stubSearchFileInfoMap.put("Id",searchFileInfo);
        List<DownloadFileInfo> expected = new ArrayList<DownloadFileInfo>();
        expected.add(stubDownloadFileInfo);
        List<DownloadFileInfo> actual = getDownloadFileInfoList.execute(stubSearchFileInfoMap);
        Assert.assertEquals(expected.get(0).getDirectory(),actual.get(0).getDirectory());
        Assert.assertEquals(expected.get(0).getId(),actual.get(0).getId());
        Assert.assertEquals(expected.get(0).getMimeType(),actual.get(0).getMimeType());
        Assert.assertEquals(expected.get(0).getTitle(),actual.get(0).getTitle());
    }
}