package org.eu.luolei.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.constraints.Pattern;
import org.eu.luolei.pojo.Result;
import org.eu.luolei.pojo.User;
import org.eu.luolei.service.UserService;
import org.eu.luolei.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findByUsername(username);
        if (u == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户已经存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUsername(username);
        if (u != null && Md5Utils.encode(password).equals(u.getPassword())) {
            // algorithm
            Algorithm algorithm = Algorithm.HMAC256("123456");

            // claims
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",u.getUsername());

            String token = JWT.create().withIssuer("luolei")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                    .withClaim("user",claims)
                    .sign(algorithm);

            return Result.success(token);
        } else {
            return Result.error("用户名或者密码错误");
        }
    }
}
