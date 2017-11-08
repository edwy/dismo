package com.dismo.service;


import com.dismo.model.User;

import java.util.List;

/**
 * @author  by Edwin Yang on 2017/11/8 0008.
 */
public interface LoginService {

    List<User> findUserByName(String name);
}
