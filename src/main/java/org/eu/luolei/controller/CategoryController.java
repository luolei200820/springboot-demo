package org.eu.luolei.controller;

import org.eu.luolei.pojo.Category;
import org.eu.luolei.pojo.Result;
import org.eu.luolei.service.CategoryService;
import org.eu.luolei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // 新增分类
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    // 分裂列表
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> list = categoryService.list();
        return Result.success(list);
    }

    // 分类详情
    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    // 更新文章分类
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }
}
