package org.eu.luolei.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.eu.luolei.pojo.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username};")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username,password,create_time,update_time) " +
            "VALUES(#{username},#{password},NOW(),NOW());")
    void add(String username, String password);

    @Update("UPDATE user " +
            "SET nickname=#{nickname},email=#{email},update_time=#{updateTime} " +
            "WHERE id=#{id};")
    void update(User user);

    @Update("UPDATE user " +
            "SET user_pic=#{avatarUrl},update_time=NOW() " +
            "WHERE id=#{id};")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("UPDATE user " +
            "SET password=#{newPwd},update_time=NOW() " +
            "WHERE id=#{id};")
    void updatePwd(String newPwd, Integer id);
}
