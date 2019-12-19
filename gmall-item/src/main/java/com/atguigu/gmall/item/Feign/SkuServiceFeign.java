package com.atguigu.gmall.item.Feign;

import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("GMALL-MANAGE")
public interface SkuServiceFeign {
     @RequestMapping("/item")
     PmsSkuInfo item(@RequestParam("skuId") String skuId);
     @RequestMapping("/spuSaleAttrList")
     List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam("skuId") String skuId ,@RequestParam("spuId") String spuId);
     @RequestMapping("/getSkuList")
     List<PmsSkuInfo> getSkuList(@RequestParam("spuId")String spuId);
}
