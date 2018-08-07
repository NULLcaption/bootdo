package com.bootdo.production.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.*;
import com.bootdo.production.domain.ProdctionDo;
import com.bootdo.production.domain.ProductCarDo;
import com.bootdo.production.domain.ProductCollectionDo;
import com.bootdo.production.service.ProductionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @Description:    产品列表目录控制类
* @Author:         Cheney Master
* @CreateDate:     2018/7/26 11:26
* @Version:        1.0
*/
@RequestMapping("/production/production")
@Controller
public class ProductionController  extends BaseController {

    @Autowired
    ProductionService productionService;

    @Autowired
    private BootdoConfig bootdoConfig;

    @Log("产品图片")
    @GetMapping("/productionImage")
    public String productionImage () {
        return "production/productImage/productImage";
    }

    /**
     * xpp产品列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/productXppList")
    public PageUtils productXppList(@RequestParam Map<String, Object> params) {
        params.put("brand","X");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = productionService.getProductXppList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = productionService.getProductXppCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    /**
     * meco产品列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/productMecoList")
    public PageUtils productMecoList(@RequestParam Map<String, Object> params) {
        params.put("brand","M");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = productionService.getProductMecoList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = productionService.getProductMecoCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    /**
     * lfy产品列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/productLfyList")
    public PageUtils productLfyList(@RequestParam Map<String, Object> params) {
        params.put("brand","L");
        Query query = new Query(params);
        List<ProdctionDo> prodctionDoList = productionService.getProductLfyList(params);
        if (prodctionDoList.size()!=0){
            for(ProdctionDo production : prodctionDoList) {
                if (production.getProductImageUrl()!=null || "".equals(production.getProductImageUrl())) {
                    production.setProductImageUrl("/images/" + production.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        int total = productionService.getProductLfyCount(query);
        PageUtils pageUtils = new PageUtils(prodctionDoList, total);
        return pageUtils;
    }

    @Log("上传产品图片页面跳转")
    @GetMapping("/uploadXppImage/{pid}")
    public String uploadXppImage(@PathVariable("pid") String pid, Model model) {
        ProdctionDo prodctionDo = productionService.getProductionByPid(pid);
        model.addAttribute("prodctionDo",prodctionDo);
        return "production/productImage/uploadXppImage";
    }

    @Log("上传产品图片")
    @ResponseBody
    @PostMapping("/saveXppImage")
    public R saveXppImage (@RequestParam("avatar_file") MultipartFile file, ProdctionDo production,HttpServletRequest request) {
        //上传文件
        if (StringUtils.isNoneEmpty(file.getOriginalFilename())) {
            String fileName = file.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
            try {
                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "production/", fileName);
            } catch (Exception e) {
                return R.error();
            }
            production.setProductImage(file.getOriginalFilename());//文件名
            production.setProductImageUrl(sysFile.getUrl());//文件路径
        }
        int count = 0;
        if (production.getPid() != null || "".equals(production.getPid())) {
            count = productionService.saveXppImage(production);
        }
        if (count > 0) {
            return R.ok().put("pid", production.getPid());
        }
        return R.error();
    }

    /**
     * 产品展示——xpp
     * @return
     */
    @GetMapping("/productXpp")
    public String productionXpp(){
        return "production/product/productXpp";
    }

    /**
     * 产品展示——meco
     * @return
     */
    @GetMapping("/productMeco")
    public String productionMeco() {
        return "production/product/productMeco";
    }

    /**
     * 产品展示——lfy
     * @return
     */
    @GetMapping("/productLfy")
    public String productionLfy() {
        return "production/product/productLfy";
    }

    /**
     * 收藏夹展示
     * @return
     */
    @GetMapping("/collect")
    public String productionCollect(Model model) {
        List<ProdctionDo> prodctionDoList = productionService.getProductCollect(this.getUserId());
        if(prodctionDoList.size()!=0) {
            for(ProdctionDo prodctionDo : prodctionDoList) {
                if (StringUtils.isNotEmpty(prodctionDo.getProductImageUrl())) {
                    prodctionDo.setProductImageUrl("/images/" + prodctionDo.getProductImageUrl().replace("/files/", ""));
                }
            }
        }
        model.addAttribute("prodctionDoList",prodctionDoList);
        return "production/product/productCollect";
    }

    /**
     * 加入购物车页面
     * @return
     */
    @GetMapping("/addCar/{pid}")
    public String productionAddCar(@PathVariable("pid") String pid,Model model) {
        ProdctionDo prodctionDo = productionService.getProductionByPid(pid);
        if (StringUtils.isNotEmpty(prodctionDo.getProductImageUrl())) {
            prodctionDo.setProductImageUrl("/images/" + prodctionDo.getProductImageUrl().replace("/files/", ""));
        }
        model.addAttribute("prodctionDo",prodctionDo);
        return "production/product/productAddCar";
    }

    /**
     * 保存购物车
     * @param prodctionDo
     * @return
     */
    @Log("保存购物车")
    @ResponseBody
    @PostMapping("/saveProductionCar")
    public R saveProductionCar(ProdctionDo prodctionDo) {
        int count;
        if (StringUtils.isNotEmpty(String.valueOf(prodctionDo.getPid()))
                || StringUtils.isNotEmpty(String.valueOf(this.getUserId()))) {
            ProductCarDo productCarDo = new ProductCarDo();
            productCarDo.setPid(prodctionDo.getPid());
            productCarDo.setUserId(this.getUserId());
            count = productionService.getProductCarById(productCarDo);
            if (count == 0) {
                productCarDo.setNum(Double.valueOf(prodctionDo.getNum()));
                productCarDo.setCreatTime(new Date());
                int creatcount = productionService.creatProductCar(productCarDo);
                if (creatcount > 0) {
                    return R.ok().put("pid", prodctionDo.getPid());
                }
            } else {
                productCarDo.setNum(Double.valueOf(prodctionDo.getNum()));
                productCarDo.setCreatTime(new Date());
                int updatecount = productionService.updateProductCarById(productCarDo);
                if (updatecount > 0) {
                    return R.ok().put("pid", prodctionDo.getPid());
                }
            }
        }
        return R.error();
    }

    /**
     * 添加至收藏夹
     * @return
     */
    @ResponseBody
    @PostMapping("/addCollect")
    public R addCollect(String pid) {
        if (StringUtils.isNotEmpty(pid)) {
            ProductCollectionDo productCollectionDo = new ProductCollectionDo();
            productCollectionDo.setUserId(this.getUserId());
            productCollectionDo.setPid(Long.valueOf(pid));
            int count = productionService.getProductCollectById(productCollectionDo);
            if (count == 0) {
                productCollectionDo.setCreatTime(new Date());
                int collectcount = productionService.creatProductCollet(productCollectionDo);
                if (collectcount > 0) {
                    return R.ok().put("pid",pid);
                }
            } else {
                return R.error(1,"该产品已经被收藏过了，不能重复收藏了！");
            }
        }
        return R.error();
    }

    /**
     * 批量添加至收藏夹
     * @param pids
     * @return
     */
    @ResponseBody
    @PostMapping("/batchAddCollect")
    public R batchAddCollect(@RequestParam("pids[]") Long[] pids) {
        if (pids.length != 0) {
            int collectcount = 0;
            for (int i=0; i<pids.length; i++) {
                ProductCollectionDo productCollectionDo = new ProductCollectionDo();
                productCollectionDo.setPid(pids[i]);
                productCollectionDo.setUserId(this.getUserId());
                int count = productionService.getProductCollectById(productCollectionDo);
                if (count == 0) {
                    productCollectionDo.setCreatTime(new Date());
                    collectcount = productionService.creatProductCollet(productCollectionDo);
                    if (collectcount > 0) {
                        collectcount++;
                    }
                } else {
                    return R.error(1,pids[i]+":该产品已经被收藏过了，不能重复收藏了！");
                }
            }
            if (collectcount > 0) {
                return R.ok();
            }
        }
        return R.error();
    }

    /**
     * 预览商品详情
     * @param pid
     * @return
     */
    @GetMapping("/preview/{pid}")
    public String viewProductionsById(@PathVariable("pid") String pid, Model model) {
        //产品列表
        ProdctionDo prodctionDo = productionService.getViewProductionsById(pid);
        if (StringUtils.isNotEmpty(prodctionDo.getProductImageUrl())) {
            prodctionDo.setProductImageUrl("/images/" + prodctionDo.getProductImageUrl().replace("/files/", ""));
        }
        //是否被收藏
        ProductCollectionDo productCollectionDo = new ProductCollectionDo();
        productCollectionDo.setPid(Long.valueOf(pid));
        productCollectionDo.setUserId(this.getUserId());
        int count = productionService.getProductCollectById(productCollectionDo);
        if (count != 0) {
            prodctionDo.setIsCollect("已收藏");
        } else {
            prodctionDo.setIsCollect("未收藏");
        }
        model.addAttribute("prodctionDo",prodctionDo);
        return "production/product/productView";
    }

    /**
     * 批量添加至购物车
     * @param pids
     * @return
     */
    @GetMapping("/batchAddCar/{pids}")
    public String batchAddProductCar (@PathVariable("pids") Long[] pids, Model model) {
        List<ProdctionDo> prodctionDoList = new ArrayList<>();
        if (pids.length != 0) {
            prodctionDoList = productionService.getProductionByPids(pids);
            if(prodctionDoList.size()!=0) {
                for(ProdctionDo prodctionDo : prodctionDoList) {
                    if (StringUtils.isNotEmpty(prodctionDo.getProductImageUrl())) {
                        prodctionDo.setProductImageUrl("/images/" + prodctionDo.getProductImageUrl().replace("/files/", ""));
                    }
                }
            }
        }
        model.addAttribute("prodctionDoList",prodctionDoList);
        return "production/product/batchAddProductCar";
    }

    /**
     * 批量添加至购物车
     * @param pids
     * @return
     */
    @ResponseBody
    @PostMapping("/batchAddCar")
    public R batchSaveProductionCar (@RequestParam("pids[]") Long[] pids) {
        if (pids.length != 0) {
            int count;
            for (int i=0; i<pids.length; i++) {
                ProductCarDo productCarDo = new ProductCarDo();
                productCarDo.setPid(pids[i]);
                productCarDo.setUserId(this.getUserId());
                count = productionService.getProductCarById(productCarDo);
                if (count == 0) {
                    productCarDo.setNum(Double.valueOf(0.0));
                    productCarDo.setCreatTime(new Date());
                    int creatcount = productionService.creatProductCar(productCarDo);
                    if (creatcount > 0) {
                        return R.ok().put("pid", pids[i]);
                    }
                } else {
                    productCarDo.setNum(Double.valueOf(Double.valueOf(0.0)));
                    productCarDo.setCreatTime(new Date());
                    int updatecount = productionService.updateProductCarById(productCarDo);
                    if (updatecount > 0) {
                        return R.ok().put("pid", pids[i]);
                    }
                }
            }
        }
        return R.error();
    }
}
