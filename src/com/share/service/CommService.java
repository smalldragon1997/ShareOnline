// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:58:34
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CommService.java

package com.share.service;

import com.share.json.MsgJson;

public interface CommService
{

    public abstract MsgJson<String, Object> uploadImg(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> releaseComm(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> dropComm(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> reviseComm(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> getAllTypeList();

    public abstract MsgJson<String, Object> getReleasedComm(MsgJson<String, Object> msgFromController);
    
 	public MsgJson<String, Object> queryComm(MsgJson<String, Object> msgFromController);
}
