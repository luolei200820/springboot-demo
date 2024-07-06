package org.eu.luolei.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.eu.luolei.mapper.ArticleMapper;
import org.eu.luolei.pojo.Article;
import org.eu.luolei.pojo.PageBean;
import org.eu.luolei.service.ArticleService;
import org.eu.luolei.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1. 创建PageBean对象
        PageBean<Article> pb = new PageBean<>();

        // 2. 开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 调用mapper
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer userId = (Integer) claims.get("id");
        List<Article> articles = articleMapper.list(userId, categoryId, state);

        // Page中提供了方法，可以获取PageHelper分页查询后得到的总记录和当前页数据
        Page<Article> p = (Page<Article>) articles;

        // 把数据填充到PageBean对象中
        pb.setItems(p.getResult());
        pb.setTotal(p.getTotal());
        return pb;
    }

    @Override
    public void add(Article article) {
        Map<String, Object> claims = ThreadLocalUtils.get();
        Integer userId = (Integer) claims.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }
}
