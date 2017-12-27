package com.share.dao;

import com.share.entity.User;

public interface UserDao
{

    public abstract User getUserByUserId(int i);

    public abstract int insertUser(User user);
}
