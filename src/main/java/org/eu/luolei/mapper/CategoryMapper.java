package org.eu.luolei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.eu.luolei.pojo.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO category(category_name,category_alias,create_user,create_time,update_time) " +
            "VALUES(#{categoryName},#{categoryAlias},#{createUser},NOW(),NOW());")
    void add(Category category);

    @Select("SELECT id,category_name,category_alias,create_time,update_time " +
            "FROM category " +
            "WHERE create_user=#{createUser}")
    List<Category> list(Integer createUser);

    @Select("SELECT * " +
            "FROM category " +
            "WHERE id=#{id}")
    Category findById(Integer id);

    @Update("UPDATE category " +
            "SET category_name=#{categoryName},category_alias=#{categoryAlias},update_time=NOW() " +
            "WHERE id=#{id}")
    void update(Category category);
}
