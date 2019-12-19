package com.atguigu.gmall.manage.service.impl;

import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuImageMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.atguigu.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
     @Autowired
     PmsSkuInfoMapper pmsSkuInfoMapper;
     @Autowired
     PmsSkuImageMapper pmsSkuImageMapper;
     @Autowired
     PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
     @Autowired
     PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    //添加sku
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insert(pmsSkuInfo);
        //添加sku图片集
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        //添加平台属性中间表
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //添加销售属性表
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
    }

    //根据skuid差库存
    public PmsSkuInfo seleteSkuInfo(String skuId) {
        PmsSkuInfo pmsSkuInfo1 =null;
        if (StringUtils.isNoneBlank(skuId)) {
            PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
            pmsSkuInfo.setId(skuId);
            pmsSkuInfo1 = pmsSkuInfoMapper.selectOne(pmsSkuInfo);
            if (pmsSkuInfo1!=null) {
                //获取skuId图片集
                PmsSkuImage pmsSkuImage = new PmsSkuImage();
                pmsSkuImage.setSkuId(skuId);
                List<PmsSkuImage> skuImageList = pmsSkuImageMapper.select(pmsSkuImage);
                pmsSkuInfo1.setSkuImageList(skuImageList);
            }
        }
        return pmsSkuInfo1;
    }

    //根据商品id获取sku
    public List<PmsSkuInfo> getSkuList(String spuId) {
        List<PmsSkuInfo> pmsSkuInfoList=new ArrayList<>();
        if(StringUtils.isNoneBlank(spuId)){
            PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
            pmsSkuInfo.setProductId(spuId);
            pmsSkuInfoList= pmsSkuInfoMapper.select(pmsSkuInfo);
            for (PmsSkuInfo skuInfo : pmsSkuInfoList) {
                PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
                pmsSkuSaleAttrValue.setSkuId(skuInfo.getId());
                List<PmsSkuSaleAttrValue> select = pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
                skuInfo.setSkuSaleAttrValueList(select);
            }
        }
        return pmsSkuInfoList;
    }
}
