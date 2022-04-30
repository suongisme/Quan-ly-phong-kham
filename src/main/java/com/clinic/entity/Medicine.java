package com.clinic.entity;

import com.clinic.annotation.Required;

import java.util.Date;

public class Medicine {

    private String code;
    private String name;
    private String country;
    private Double price;
    private Date expire;
    private String description;

    public Medicine() {}

    public Medicine(String code, String name, String country, Double price, Date expire, String description) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.price = price;
        this.expire = expire;
        this.description = description;
    }

    @Required(message = "Mã thuốc không được trống")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Required(message = "Tên thuốc không được trống")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Required(message = "Nước sản xuất không được trống")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Required(message = "Giá tiền không được trống")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Required(message = "Hạn sử dụng không được trống")
    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    @Required(message = "Mô tả không được trống")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}