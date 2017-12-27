// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:58:08
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ReleaseJson.java

package com.share.json;


public class ReleaseJson
{
    private int typeId;
    private int userId;
    private String commTitle;
    private int commPrice;
    private String startTime;
    private String endTime;
    private String introduce;
    private String imgName;

    public ReleaseJson(int typeId, int userId, String commTitle, int commPrice, String startTime, String endTime, String introduce, 
            String imgName)
    {
        this.typeId = typeId;
        this.userId = userId;
        this.commTitle = commTitle;
        this.commPrice = commPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.introduce = introduce;
        this.imgName = imgName;
    }

    public ReleaseJson()
    {
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getCommTitle()
    {
        return commTitle;
    }

    public void setCommTitle(String commTitle)
    {
        this.commTitle = commTitle;
    }

    public int getCommPrice()
    {
        return commPrice;
    }

    public void setCommPrice(int commPrice)
    {
        this.commPrice = commPrice;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getIntroduce()
    {
        return introduce;
    }

    public void setIntroduce(String introduce)
    {
        this.introduce = introduce;
    }

    public String getImgName()
    {
        return imgName;
    }

    public void setImgName(String imgName)
    {
        this.imgName = imgName;
    }

}
