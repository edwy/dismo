package com.dismo.dao;

import com.dismo.model.novel.NovelInfo;
import org.apache.ibatis.annotations.Insert;

/**
 * @author code4crafer@gmail.com
 *         Date: 13-6-23
 *         Time: 下午4:27
 */
public interface NovelInfoDAO {

    @Insert("insert into JobInfo (`title`,`salary`,`company`,`description`,`source`,`url`,`urlMd5`) values (#{title},#{salary},#{company},#{description},#{source},#{url},#{urlMd5})")
    public int add(NovelInfo novelInfo);
}
