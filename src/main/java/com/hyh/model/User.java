package com.hyh.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String img;
    private String pwd;
    private String name;
    private int age;
    private String sex;
    private Clazz clazz;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public User(int id, String img, String pwd, String name, int age, String sex, Clazz clazz) {
        this.id = id;
        this.img = img;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.clazz = clazz;
    }
}