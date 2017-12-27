// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:58:50
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IndexService.java

package com.share.service;

import com.share.json.MsgJson;

public interface IndexService
{

    public abstract MsgJson<String, Object> getIndexHotList();

    public abstract MsgJson<String, Object> getIndexSlideList();

    public abstract MsgJson<String, Object> getIndexCommList(MsgJson<String, Object> msgFromController);
    
    public MsgJson<String, Object> reviseComm(MsgJson<String, Object> msgFromController);
}
