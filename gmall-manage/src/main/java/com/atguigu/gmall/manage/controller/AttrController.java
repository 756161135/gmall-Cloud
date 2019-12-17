package com.atguigu.gmall.manage.controller;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.service.PmsBaseAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
public class AttrController {
    @Autowired
    PmsBaseAttrService pmsBaseAttrService;

    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList=  pmsBaseAttrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfoList;
    }

    @RequestMapping("/saveAttrInfo")
    @ResponseBody
    public String  saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        pmsBaseAttrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValueList =pmsBaseAttrService.getAttrValueList(attrId);
        return pmsBaseAttrValueList;
    }
}
