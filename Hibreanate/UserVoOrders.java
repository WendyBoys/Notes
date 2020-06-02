package com.xuan.domain;

public class UserVoOrders {
    private int id;
    private Integer uid;
    private Integer age;
    private String name;
    private String phone;

    public UserVoOrders(int id, Integer uid, Integer age, String name, String phone) {
        this.id = id;
        this.uid = uid;
        this.age = age;
        this.name = name;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserVoOrders{" +
                "id=" + id +
                ", uid=" + uid +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
