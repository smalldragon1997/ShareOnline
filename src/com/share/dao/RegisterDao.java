package com.share.dao;

import com.share.entity.Register;

public interface RegisterDao
{

    public abstract int insertRegister(Register register);

    public abstract Register getRegisterByAccount(String s);

    public abstract int updateRegisterLastTimeByAccount(Register register);
}
