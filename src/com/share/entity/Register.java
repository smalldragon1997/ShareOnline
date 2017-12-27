package com.share.entity;

import java.sql.Timestamp;

public class Register
{
    private int userId;
    private String account;
    private String password;
    private Timestamp registerTime;
    private Timestamp lastTime;

    public Register(int userId, String account, String password, Timestamp registerTime, Timestamp lastTime)
    {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.registerTime = registerTime;
        this.lastTime = lastTime;
    }

    public Register()
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

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Timestamp getRegisterTime()
    {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime)
    {
        this.registerTime = registerTime;
    }

    public Timestamp getLastTime()
    {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime)
    {
        this.lastTime = lastTime;
    }

}
