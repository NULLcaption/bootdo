package com.bootdo.production.dao;

import com.bootdo.common.utils.Query;
import com.bootdo.production.domain.ProdctionDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @Description:    订单管理数据层
* @Author:         Cheney Master
* @CreateDate:     2018/8/7 13:31
* @Version:        1.0
*/
@Mapper
public interface OrderDao {
    List<ProdctionDo> getProductXppcarList(Map<String, Object> params);

    int getProductXppcarCount(Query query);

    int deleteProductionCar(String pid);

    ProdctionDo getProductionCarByPid(String pid);
}
