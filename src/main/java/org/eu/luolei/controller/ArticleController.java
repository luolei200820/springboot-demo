package org.eu.luolei.controller;

import org.eu.luolei.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result list() {
        return Result.success("所有文章书");
    }
}
