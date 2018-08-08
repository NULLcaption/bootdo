package com.bootdo.production.controller;

import com.bootdo.activiti.service.ProcessService;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.production.domain.ProdctionDo;
import com.bootdo.production.service.OrderService;
import com.bootdo.production.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @Description:    订单管理系统
 * 三个模块：购物车；我要下单；订单管理
* @Author:         Cheney Master
* @CreateDate:     2018/8/7 11:32
* @Version:        1.0
*/
@RequestMapping("/production/order")
@Controller
public class OrderController extends BaseController{

    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductionService productionService;

    @Log("购物车列表")
    @GetMapping("/productCar")
    public String toProductCarList() {
        return "/production/order/productCar";
    }

    @ResponseBody
    @GetMapping("/productXppCarList")
    public PageUtils getProductXppCarList(@RequestParam Map<String,Object> params) {
        params.put("brand","X");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = orderService.getProductXppcarList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = orderService.getProductXppcarCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/productMecoCarList")
    public PageUtils getProductMecoCarList(@RequestParam Map<String,Object> params) {
        params.put("brand","M");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = orderService.getProductXppcarList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = orderService.getProductXppcarCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/productLfyCarList")
    public PageUtils getProductLfyCarList(@RequestParam Map<String,Object> params) {
        params.put("brand","L");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = orderService.getProductXppcarList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = orderService.getProductXppcarCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    /**
     * 删除购物车
     * @param pid
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteCar")
    public R deleteProductionCar(String pid) {
        if (StringUtils.isNotEmpty(pid)) {
            int count = orderService.deleteProductionCar(pid);
            if (count > 0) {
                return R.ok().put("pid",pid);
            }
        }
        return R.error();
    }

    /**
     * 修改购物车中产品的数量
     * @param pid
     * @param model
     * @return
     */
    @GetMapping("/editCarNum/{pid}")
    public String editCarNum(@PathVariable("pid") Long pid, Model model) {
        ProdctionDo prodctionDo = orderService.getProductionCarByPid(String.valueOf(pid));
        if (StringUtils.isNotEmpty(prodctionDo.getProductImageUrl())) {
            prodctionDo.setProductImageUrl("/images/" + prodctionDo.getProductImageUrl().replace("/files/", ""));
        }
        model.addAttribute("prodctionDo",prodctionDo);
        return "/production/order/editCarNum";
    }

}
