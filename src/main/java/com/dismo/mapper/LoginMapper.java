package com.dismo.mapper;

import com.dismo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author  by Edwin Yang on 2017/11/8 0008.
 */
@Mapper
public interface LoginMapper {

    @Select("select * from user")
     List<User> findUserByName();

}
