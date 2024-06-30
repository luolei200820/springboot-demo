package org.eu.luolei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.eu.luolei.pojo.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username};")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username,password) VALUES(#{username},#{password});")
    void add(String username,String password);
}
