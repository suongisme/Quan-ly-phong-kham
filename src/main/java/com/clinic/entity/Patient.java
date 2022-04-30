package com.clinic.entity;

import com.clinic.annotation.Required;

public class Patient {

    private String code;
    private String name;
    private Integer age;
    private Integer gender;
    private String address;

    public Patient() {}

    public Patient(String code, String name, Integer age, Integer gender, String address) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    @Required(message = "Mã bệnh nhân không được trống")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Required(message = "Tên bệnh nhân không được trống")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Required(message = "Tuổi không được trống")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Required(message = "Giới tính không được trống")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Required(message = "Địa chỉ không được trống")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}