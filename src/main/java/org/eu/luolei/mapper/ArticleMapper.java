package org.eu.luolei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.eu.luolei.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{state},#{categoryId},#{createUser},now(),now());")
    void add(Article article);


    List<Article> list(Integer userId, Integer categoryId, String state);
}
