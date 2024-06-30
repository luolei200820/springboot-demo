package org.eu.luolei.controller;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletResponse;
import org.eu.luolei.pojo.Result;
import org.eu.luolei.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result list() {
        return Result.success("所有文章书");
    }
}
