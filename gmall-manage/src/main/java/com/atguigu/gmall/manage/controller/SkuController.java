package com.atguigu.gmall.manage.controller;

import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.atguigu.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class SkuController {
    @Autowired
    SkuService skuService;

    @RequestMapping("/saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }
    @RequestMapping("/item")
    @ResponseBody
    public PmsSkuInfo item(@RequestParam("skuId") String skuId){
        PmsSkuInfo pmsSkuInfo  =skuService.seleteSkuInfo(skuId);
        return pmsSkuInfo;
    }
    @RequestMapping("/getSkuList")
    @ResponseBody
    public List<PmsSkuInfo> getSkuList(@RequestParam("spuId")String spuId){
        List<PmsSkuInfo> pmsSkuInfoList= skuService.getSkuList(spuId);
        return pmsSkuInfoList;
    }
}
