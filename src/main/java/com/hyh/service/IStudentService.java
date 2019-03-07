package com.hyh.service;

import com.hyh.model.User;

import java.util.List;


public interface IStudentService {
    User queryStudent(String name, String pwd);

    List<User> queryAllStudent();

    void removeStudent(Integer uid, Integer cid);

    User queryStudentById(Integer uid);

    void saveStudent(User stu);

    void addStudent(User stu);
}
