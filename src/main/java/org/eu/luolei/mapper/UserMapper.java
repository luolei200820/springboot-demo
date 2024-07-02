package org.eu.luolei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.eu.luolei.pojo.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username};")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username,password,create_time,update_time) " +
            "VALUES(#{username},#{password},NOW(),NOW());")
    void add(String username,String password);
}
