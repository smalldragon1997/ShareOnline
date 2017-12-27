// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:52:37
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Release.java

package com.share.entity;

import java.sql.Timestamp;

public class Release
{
    private int commId;
    private int userId;
    private String info;
    private int price;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp releaseTime;
    private int state; // 1.正在出售 2.一交易 3.过期
    private String title;
    private String img;

    public Release(int commId, int userId, String info, int price, Timestamp startTime, Timestamp endTime, Timestamp releaseTime, 
            int state, String title, String img)
    {
        this.commId = commId;
        this.userId = userId;
        this.info = info;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.releaseTime = releaseTime;
        this.state = state;
        this.title = title;
        this.img = img;
    }

    public Release()
    {
    }

    public int getCommId()
    {
        return commId;
    }

    public void setCommId(int commId)
    {
        this.commId = commId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Timestamp getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Timestamp startTime)
    {
        this.startTime = startTime;
    }

    public Timestamp getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Timestamp endTime)
    {
        this.endTime = endTime;
    }

    public Timestamp getReleaseTime()
    {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

}
