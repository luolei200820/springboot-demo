package org.eu.luolei.service;

import org.eu.luolei.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);

    void register(String username,String password);

    void update(User user);

    void updateAvatar(String avatarUrl);
}
