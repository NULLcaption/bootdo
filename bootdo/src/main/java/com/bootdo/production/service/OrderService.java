package com.bootdo.production.service;

import com.bootdo.common.utils.Query;
import com.bootdo.production.domain.ProdctionDo;

import java.util.List;
import java.util.Map;

/**
* @Description:    订单管理之服务层接口
* @Author:         Cheney Master
* @CreateDate:     2018/8/7 13:32
* @Version:        1.0
*/

public interface OrderService {
    List<ProdctionDo> getProductXppcarList(Map<String, Object> params);

    int getProductXppcarCount(Query query);

    int deleteProductionCar(String pid);

    ProdctionDo getProductionCarByPid(String pid);
}
