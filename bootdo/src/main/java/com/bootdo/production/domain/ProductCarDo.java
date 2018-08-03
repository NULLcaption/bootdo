package com.bootdo.production.domain;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:    产品购物车实体类
* @Author:         Cheney Master
* @CreateDate:     2018/8/2 15:07
* @Version:        1.0
*/
public class ProductCarDo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long pid;
    private Long userId;
    private Long orderId;
    private Double num;
    private Date creatTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "ProductCarDo{" +
                "id=" + id +
                ", pid=" + pid +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", num=" + num +
                ", creatTime=" + creatTime +
                '}';
    }
}
