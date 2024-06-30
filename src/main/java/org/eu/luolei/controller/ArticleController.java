package org.eu.luolei.controller;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletResponse;
import org.eu.luolei.pojo.Result;
import org.eu.luolei.utils.JWTUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result list(@RequestHeader("Authorization") String token, HttpServletResponse response) {

        try {
            Map<String, Claim> claims = JWTUtils.parseToken(token);
            return Result.success("所有文章书");
        } catch (Exception e) {
            response.setStatus(401);
            return Result.error("未登录");
        }
    }
}
