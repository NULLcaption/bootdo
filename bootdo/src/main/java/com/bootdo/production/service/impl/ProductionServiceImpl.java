package com.bootdo.production.service.impl;

import com.bootdo.common.utils.Query;
import com.bootdo.production.dao.ProductionDao;
import com.bootdo.production.domain.ProdctionDo;
import com.bootdo.production.domain.ProductCarDo;
import com.bootdo.production.domain.ProductCollectionDo;
import com.bootdo.production.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @Description:    产品服务类实现类
* @Author:         Cheney Master
* @CreateDate:     2018/7/27 15:01
* @Version:        1.0
*/
@Service
public class ProductionServiceImpl implements ProductionService {
    @Autowired
    ProductionDao productionDao;

    @Override
    public List<ProdctionDo> getProductXppList(Map<String, Object> params) {
        return productionDao.getProductXppList(params);
    }

    @Override
    public int getProductXppCount(Query query) {
        return productionDao.getProductXppCount(query);
    }

    @Override
    public List<ProdctionDo> getProductMecoList(Map<String, Object> params) {
        return productionDao.getProductMecoList(params);
    }

    @Override
    public int getProductMecoCount(Query query) {
        return productionDao.getProductMecoCount(query);
    }

    @Override
    public List<ProdctionDo> getProductLfyList(Map<String, Object> params) {
        return productionDao.getProductLfyList(params);
    }

    @Override
    public int getProductLfyCount(Query query) {
        return productionDao.getProductLfyCount(query);
    }

    @Override
    public ProdctionDo getProductionByPid(String pid) {
        return productionDao.getProductionByPid(pid);
    }

    @Override
    public int saveXppImage(ProdctionDo prodction) {
        return productionDao.saveXppImage(prodction);
    }

    @Override
    public int getProductCarById(ProductCarDo productCarDo) {
        return productionDao.getProductCarById(productCarDo);
    }

    @Override
    public int creatProductCar(ProductCarDo productCarDo) {
        return productionDao.creatProductCar(productCarDo);
    }

    @Override
    public int updateProductCarById(ProductCarDo productCarDo) {
        return productionDao.updateProductCarById(productCarDo);
    }

    @Override
    public int getProductCollectById(ProductCollectionDo productCollectionDo) {
        return productionDao.getProductCollectById(productCollectionDo);
    }

    @Override
    public int creatProductCollet(ProductCollectionDo productCollectionDo) {
        return productionDao.creatProductCollet(productCollectionDo);
    }

    @Override
    public ProdctionDo getViewProductionsById(String pid) {
        return productionDao.getViewProductionsById(pid);
    }

    @Override
    public List<ProdctionDo> getProductionByPids(Long[] pids) {
        return productionDao.getProductionByPids(pids);
    }

    @Override
    public List<ProdctionDo> getProductCollect(Long userId) {
        return productionDao.getProductCollect(userId);
    }
}
