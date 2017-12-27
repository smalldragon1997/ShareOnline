package com.share.dao;

import com.share.entity.CommType;

import java.util.List;

public interface CommTypeDao
{

    public abstract int insertCommType(CommType commtype);

    public abstract int deleteCommType(int i);

    public abstract CommType getCommTypeByCommId(int i);

    public abstract List<CommType> getCommTypeByTypeId(int i);
}
