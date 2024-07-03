package org.eu.luolei.service.impl;

import org.eu.luolei.mapper.CategoryMapper;
import org.eu.luolei.pojo.Category;
import org.eu.luolei.service.CategoryService;
import org.eu.luolei.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer userId = (Integer) claims.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer userId = (Integer) claims.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }
}
