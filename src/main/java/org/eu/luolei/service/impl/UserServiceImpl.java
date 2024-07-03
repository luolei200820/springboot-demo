package org.eu.luolei.service.impl;

import org.eu.luolei.mapper.UserMapper;
import org.eu.luolei.pojo.User;
import org.eu.luolei.service.UserService;
import org.eu.luolei.utils.Md5Utils;
import org.eu.luolei.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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
        userMapper.add(username, encryptedPassword);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updatePwd(Md5Utils.encode(newPwd), id);
    }
}
