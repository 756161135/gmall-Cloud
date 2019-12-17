package com.atguigu.gmall.manage.service.impl;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.atguigu.gmall.service.PmsBaseAttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsBaseAttrServiceImpl implements PmsBaseAttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    //根据三级分类id查询平台属性
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo attrInfo = new PmsBaseAttrInfo();
        attrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> baseAttrInfoList = pmsBaseAttrInfoMapper.select(attrInfo);
        for (PmsBaseAttrInfo pmsBaseAttrInfo : baseAttrInfoList) {
            PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
            attrValue.setAttrId(pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> select = pmsBaseAttrValueMapper.select(attrValue);
            pmsBaseAttrInfo.setAttrValueList(select);
        }
        return baseAttrInfoList;
    }

    //添加平台属性
    public void saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id = pmsBaseAttrInfo.getId();
        if (!StringUtils.isNoneBlank(id)) {
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
        }else {
            pmsBaseAttrInfoMapper.updateByPrimaryKeySelective(pmsBaseAttrInfo);
            PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
            attrValue.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(attrValue);
        }
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue attrValue : attrValueList) {
                attrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(attrValue);
            }
    }

    //回显平台属性值
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
        attrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrValueMapper.select(attrValue);
        return pmsBaseAttrValueList;
    }
}
