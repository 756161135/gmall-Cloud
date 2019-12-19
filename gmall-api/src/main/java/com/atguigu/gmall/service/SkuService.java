package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;

import java.util.List;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo seleteSkuInfo(String skuId);

    List<PmsSkuInfo> getSkuList(String spuId);
}
