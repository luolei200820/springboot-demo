package org.eu.luolei.service;

import org.eu.luolei.pojo.Article;
import org.eu.luolei.pojo.PageBean;

public interface ArticleService {
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void add(Article article);
}
