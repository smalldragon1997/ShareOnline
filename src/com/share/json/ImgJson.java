// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:56:27
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ImgJson.java

package com.share.json;


public class ImgJson
{
    private boolean state;
    private String imgName;
    private String imgUrl;
    private String msg;
    
    public ImgJson(boolean state, String imgName, String imgUrl, String msg)
    {
        this.state = state;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.msg = msg;
    }

    public ImgJson()
    {
    }

    public boolean isState()
    {
        return state;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public String getImgName()
    {
        return imgName;
    }

    public void setImgName(String imgName)
    {
        this.imgName = imgName;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
