package com.hyh.dao;

import com.hyh.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
public interface IStudentDao {
//    @Select("select * from user where name=#{name} and pwd=#{pwd}")
    User selectStudentByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);
//    @Select("select * from user")
    List<User> selectAll();
//    @Delete("delete from user where id=#{uid}")
    int deleteStudent(Integer uid);
//    @Select("select * from user where id=#{uid}")
    User selectById(Integer uid);
    @Insert("insert into user(name, pwd, age, sex, clazz) values(#{name}, #{pwd}, #{age}, #{sex}, #{clazz.id})")
    int insertStudent(User stu);

    int updateStudent(User stu);
}
