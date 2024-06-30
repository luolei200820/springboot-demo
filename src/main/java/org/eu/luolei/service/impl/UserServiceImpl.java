package org.eu.luolei.service.impl;

import org.eu.luolei.mapper.UserMapper;
import org.eu.luolei.pojo.User;
import org.eu.luolei.service.UserService;
import org.eu.luolei.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        String encryptedPassword = Md5Utils.encode(password);
        userMapper.add(username,encryptedPassword);
    }
}
