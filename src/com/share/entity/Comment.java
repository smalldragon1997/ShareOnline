package com.share.entity;

import java.sql.Timestamp;

public class Comment
{
    private int commId;
    private int userId;
    private String info;
    private int level;
    private Timestamp commentTime;

    public Comment(int commId, int userId, String info, int level, Timestamp commentTime)
    {
        this.commId = commId;
        this.userId = userId;
        this.info = info;
        this.level = level;
        this.commentTime = commentTime;
    }

    public Comment()
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

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public Timestamp getCommentTime()
    {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime)
    {
        this.commentTime = commentTime;
    }

}
