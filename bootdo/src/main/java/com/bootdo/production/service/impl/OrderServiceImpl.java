package com.bootdo.production.service.impl;

import com.bootdo.common.utils.Query;
import com.bootdo.production.dao.OrderDao;
import com.bootdo.production.domain.ProdctionDo;
import com.bootdo.production.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @Description:    订单管理之服务层
* @Author:         Cheney Master
* @CreateDate:     2018/8/7 13:34
* @Version:        1.0
*/
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;

    @Override
    public List<ProdctionDo> getProductXppcarList(Map<String, Object> params) {
        return orderDao.getProductXppcarList(params);
    }

    @Override
    public int getProductXppcarCount(Query query) {
        return orderDao.getProductXppcarCount(query);
    }

    @Override
    public int deleteProductionCar(String pid) {
        return orderDao.deleteProductionCar(pid);
    }

    @Override
    public ProdctionDo getProductionCarByPid(String pid) {
        return orderDao.getProductionCarByPid(pid);
    }
}
