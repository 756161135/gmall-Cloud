package com.atguigu.gmall.manage.controller;

import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> catalog1List=catalogService.getCatalog1();
        return catalog1List;
    }

    @RequestMapping("/getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
      List<PmsBaseCatalog2> catalog2List= catalogService.getCatalog2(catalog1Id);
      return catalog2List;
    }
    @RequestMapping("/getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> catalog3List=catalogService.getCatalog3(catalog2Id);
        return catalog3List;
    }

}