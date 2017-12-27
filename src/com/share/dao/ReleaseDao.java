package com.share.dao;

import com.share.entity.Release;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ReleaseDao
{

    public abstract int insertRelease(Release release);
    public abstract int updateRelease(Release release);

    public abstract int deleteRelease(int i);

    public abstract Release getReleaseByCommId(int i);

    public abstract List<Release> getAllReleaseByUserId(int i);

    public abstract List<Release> getAllReleaseByUserIdAndState(@Param(value="userId") int i, @Param(value="state") int j);
}
