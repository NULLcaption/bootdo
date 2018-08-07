package com.bootdo.production.service;

import com.bootdo.common.utils.Query;
import com.bootdo.production.domain.ProdctionDo;
import com.bootdo.production.domain.ProductCarDo;
import com.bootdo.production.domain.ProductCollectionDo;

import java.util.List;
import java.util.Map;

/**
* @Description:    产品服务类接口
* @Author:         Cheney Master
* @CreateDate:     2018/7/27 15:00
* @Version:        1.0
*/
public interface ProductionService {
    List<ProdctionDo> getProductXppList(Map<String, Object> params);

    int getProductXppCount(Query query);

    List<ProdctionDo> getProductMecoList(Map<String, Object> params);

    List<ProdctionDo> getProductLfyList(Map<String, Object> params);

    int getProductMecoCount(Query query);

    int getProductLfyCount(Query query);

    ProdctionDo getProductionByPid(String pid);

    int saveXppImage(ProdctionDo prodction);

    int getProductCarById(ProductCarDo productCarDo);

    int creatProductCar(ProductCarDo productCarDo);

    int updateProductCarById(ProductCarDo productCarDo);

    int getProductCollectById(ProductCollectionDo productCollectionDo);

    int creatProductCollet(ProductCollectionDo productCollectionDo);

    ProdctionDo getViewProductionsById(String pid);

    List<ProdctionDo> getProductionByPids(Long[] pids);

    List<ProdctionDo> getProductCollect(Long userId);
}
