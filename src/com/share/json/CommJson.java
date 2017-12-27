// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:56:14
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CommJson.java

package com.share.json;


public class CommJson
{

    private String typeName;
    private String commName;
    private String releaseTime;
    private String availableTime;
    private String img;
    private int price;
    private int commId;

    public CommJson(String typeName, String commName, String releaseTime, String availableTime, String img, int price, int commId)
    {
        this.typeName = typeName;
        this.commName = commName;
        this.releaseTime = releaseTime;
        this.availableTime = availableTime;
        this.img = img;
        this.price = price;
        this.commId = commId;
    }

    public CommJson()
    {
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getCommName()
    {
        return commName;
    }

    public void setCommName(String commName)
    {
        this.commName = commName;
    }

    public String getReleaseTime()
    {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public String getAvailableTime()
    {
        return availableTime;
    }

    public void setAvailableTime(String availableTime)
    {
        this.availableTime = availableTime;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getCommId()
    {
        return commId;
    }

    public void setCommId(int commId)
    {
        this.commId = commId;
    }
}
