package com.bootdo.production.domain;

import java.io.Serializable;

/**
* @Description:    产品实体类
* @Author:         Cheney Master
* @CreateDate:     2018/7/26 14:27
* @Version:        1.0
*/

public class ProdctionDo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pid;
    private String productImage;
    private String productImageUrl;
    private String productCode;
    private String productName;
    private String category;
    private String models;
    private String units;
    private String price;
    private String volume;
    private String weight;
    private String brand;
    private String num;
    private String isCollect;
    private String userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProdctionDo{" +
                "pid=" + pid +
                ", productImage='" + productImage + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", models='" + models + '\'' +
                ", units='" + units + '\'' +
                ", price='" + price + '\'' +
                ", volume='" + volume + '\'' +
                ", weight='" + weight + '\'' +
                ", brand='" + brand + '\'' +
                ", num='" + num + '\'' +
                ", isCollect='" + isCollect + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
