package com.dismo.service.impl;

import com.dismo.model.User;
import com.dismo.service.LoginService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  by Edwin Yang on 2017/11/8 0008.
 */
@Mapper
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    @Select("select * from user where name  = #{name}")
    public List<User> findUserByName(String name) {
        return null;
    }
}
