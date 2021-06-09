package com.example.supermarket_management.mapper;

import com.example.supermarket_management.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where username = #{username} and pwd = #{pwd}")
    User getUser(String username,String pwd);

    @Select("select id from user where username = #{username} ")
    String getUserIdByName(String username);

    @Select("select count(*) from user where username = #{username}")
    int checkUser(String user);

    @Insert("insert into user (username, pwd) values (#{username}, #{pwd})")
    int registerUser(String username,String pwd);
}
