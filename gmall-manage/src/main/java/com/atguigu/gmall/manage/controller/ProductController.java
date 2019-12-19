package com.atguigu.gmall.manage.controller;

import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.service.ProductService;
import com.atguigu.gmall.util.MyFileUpload;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> productInfoList=productService.spuList(catalog3Id);
        return productInfoList;
    }
    @ResponseBody
    @RequestMapping("/baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList= productService.baseSaleAttrList();
        return pmsBaseSaleAttrList;
    }
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload (@RequestParam("file") MultipartFile file) throws IOException, MyException {
        String imgUrl="";
         //获取文件上传的url地址
        imgUrl= MyFileUpload.uploadImage(file);
        return imgUrl;
    }
    @RequestMapping("/saveSpuInfo")
    @ResponseBody
    public void saveSpuInfo(@RequestBody PmsProductInfo productInfo){
        productService.saveSpuInfo(productInfo);
    }

    @ResponseBody
    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> imageList =productService.spuImageList(spuId);
        return imageList;
    }
    @RequestMapping("/spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam("skuId") String skuId ,@RequestParam("spuId") String spuId)  {
        List<PmsProductSaleAttr> productSaleAttrs=productService.spuSaleAttrList(skuId,spuId);
        return productSaleAttrs;
    }
}
