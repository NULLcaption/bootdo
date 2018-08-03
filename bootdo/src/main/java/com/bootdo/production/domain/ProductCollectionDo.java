package com.bootdo.production.domain;

import java.io.Serializable;
import java.util.Date;

/**
* @Description:    产品收藏夹之实体类
* @Author:         Cheney Master
* @CreateDate:     2018/8/3 9:39
* @Version:        1.0
*/
public class ProductCollectionDo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long pid;
    private Long userId;
    private Date creatTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid(Long aLong) {
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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "ProductCollectionDo{" +
                "id=" + id +
                ", pid=" + pid +
                ", userId=" + userId +
                ", creatTime=" + creatTime +
                '}';
    }
}
