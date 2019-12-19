package com.atguigu.gmall.item.controller;


import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.atguigu.gmall.item.Feign.SkuServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
public class ItemController {

    @Autowired
    private SkuServiceFeign skuServiceFeign;

     @RequestMapping("/{skuId}.html")
     public String getSkuInfo(@PathVariable("skuId") String skuId, ModelMap modelMap){
         PmsSkuInfo pmsSkuInfo = skuServiceFeign.item(skuId);
         List<PmsProductSaleAttr> productSaleAttrs = skuServiceFeign.spuSaleAttrList(skuId,pmsSkuInfo.getProductId());
         modelMap.put("skuInfo",pmsSkuInfo);
         modelMap.put("spuSaleAttrListCheckBySku",productSaleAttrs);
         modelMap.put("spuId",pmsSkuInfo.getProductId());
         return "item";
     }
     @RequestMapping("/skuSaleAttrValueJson")
     @ResponseBody
     public String skuSaleAttrValueJson(String spuId){
         List<PmsSkuInfo> skuList = skuServiceFeign.getSkuList(spuId);
         Map<String,String> map =new HashMap<>();
         for (PmsSkuInfo pmsSkuInfo : skuList) {
             List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
             String skuJsonKey="";
             for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                 skuJsonKey=skuJsonKey+"|"+pmsSkuSaleAttrValue.getSaleAttrValueId();
             }
             map.put(skuJsonKey,pmsSkuInfo.getId());
         }
         //生成一份静态的json文件
         String jsonString = JSON.toJSONString(map);
         File file =new File("F:/gitApp/gmall-Cloud/gmall-item/src/main/resources/static/sku/spu_"+spuId+".json");
         FileOutputStream fileOutputStream=null;
         try {
             fileOutputStream= new FileOutputStream(file);
             fileOutputStream.write(jsonString.getBytes());
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             try {
                 fileOutputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return jsonString;
     }
}
