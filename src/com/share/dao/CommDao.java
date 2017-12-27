package com.share.dao;

import com.share.entity.Comm;

public interface CommDao
{

    public abstract int insertComm(Comm comm);
    public abstract int updateComm(Comm comm);

    public abstract int deleteComm(int i);

    public abstract Comm getCommByCommId(int i);
}
