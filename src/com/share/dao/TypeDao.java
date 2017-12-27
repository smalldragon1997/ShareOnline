package com.share.dao;

import com.share.entity.Type;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TypeDao
{

    public abstract List<Type> getTypeListByCount(@Param(value="start") int start, @Param(value="count") int connt);

    public abstract List<Type> getAllTypeList();

    public abstract Type getTypeByTypeId(int typeId);
    
}
