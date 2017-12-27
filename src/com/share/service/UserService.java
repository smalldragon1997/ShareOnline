// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:58:59
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UserService.java

package com.share.service;

import com.share.json.MsgJson;

public interface UserService
{

    public abstract MsgJson<String, Object> register(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> freeLogin(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> login(MsgJson<String, Object> msgFromController);

    public abstract MsgJson<String, Object> exit(MsgJson<String, Object> msgFromController);
}
