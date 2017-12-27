package com.share.entity;

import java.sql.Timestamp;

public class Login
{
    private int userId;
    private Timestamp loginTime;
    private String ipAddress;

    public Login(int userId, Timestamp loginTime, String ipAddress)
    {
        this.userId = userId;
        this.loginTime = loginTime;
        this.ipAddress = ipAddress;
    }

    public Login()
    {
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public Timestamp getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

}
