package com.atguigu.gmall.manage.service.impl;

import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    //查找sup商品集合
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> select = pmsProductInfoMapper.select(pmsProductInfo);
        return select;
    }
    //查找销售属性
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        List<PmsBaseSaleAttr> baseSaleAttrs = pmsBaseSaleAttrMapper.selectAll();
        return baseSaleAttrs;
    }

    //添加spu
    public void saveSpuInfo(PmsProductInfo productInfo) {
        pmsProductInfoMapper.insertSelective(productInfo);
        List<PmsProductSaleAttr> spuSaleAttrList = productInfo.getSpuSaleAttrList();
        //循环添加销售属性
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(productInfo.getId());
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
           //循环添加销售属性值
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(productInfo.getId());
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        //添加图片
        List<PmsProductImage> spuImageList = productInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : spuImageList) {
            pmsProductImage.setProductId(productInfo.getId());
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
    }

    //根据商品id查找图片集
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> imageList = pmsProductImageMapper.select(pmsProductImage);
        return imageList;
    }
    //根据商品id查找销售属性和属性值
    public List<PmsProductSaleAttr> spuSaleAttrList(String skuId,String spuId) {

        List<PmsProductSaleAttr> productSaleAttrs = pmsProductSaleAttrMapper.getProductSaleAttrs(skuId,spuId);

        return productSaleAttrs;
    }
}
