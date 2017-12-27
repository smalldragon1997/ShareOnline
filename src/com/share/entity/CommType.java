package com.share.entity;


public class CommType
{

    private int commId;
    private int typeId;

    public CommType(int commId, int typeId)
    {
        this.commId = commId;
        this.typeId = typeId;
    }

    public CommType()
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

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }
}
