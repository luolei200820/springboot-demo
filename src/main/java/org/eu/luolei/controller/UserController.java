package org.eu.luolei.controller;

import jakarta.validation.constraints.Pattern;
import org.eu.luolei.pojo.Result;
import org.eu.luolei.pojo.User;
import org.eu.luolei.service.UserService;
import org.eu.luolei.utils.JwtUtils;
import org.eu.luolei.utils.Md5Utils;
import org.eu.luolei.utils.ThreadLocalUtils;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            // claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            // 生成token
            String token = JwtUtils.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("用户名或者密码错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> claims = ThreadLocalUtils.get();
        String username = (String) claims.get("username");
        User u = userService.findByUsername(username);
        return Result.success(u);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        // 比对原密码是否相符
        Map<String, Object> claims = ThreadLocalUtils.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        if (!user.getPassword().equals(Md5Utils.encode(oldPwd))) {
            return Result.error("原密码不正确");
        }

        // 判断两次输入的新密码是否一样
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次输入的新密码不一样");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}
