package com.share.entity;

import java.sql.Timestamp;

public class Order
{
    private int commId;
    private int userId;
    private int price;
    private Timestamp orderTime;

    public Order(int commId, int userId, int price, Timestamp orderTime)
    {
        this.commId = commId;
        this.userId = userId;
        this.price = price;
        this.orderTime = orderTime;
    }

    public Order()
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

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Timestamp getOrderTime()
    {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime)
    {
        this.orderTime = orderTime;
    }

}
