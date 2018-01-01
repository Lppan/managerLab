package com.laboratory.system.dataDictionary.dao;


import com.laboratory.system.dataDictionary.model.DataInfo;

import java.util.List;
import java.util.Map;

public interface DataInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DataInfo record);

    int insertSelective(Map<String,Object> paramMap);

    DataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Map<String,Object> paramMap);

    int updateByPrimaryKey(DataInfo record);

    //查询项目实验内容 一级、二级、三级菜单
    List<DataInfo> selectDataDictionary(Map<String,Object> paramMap);

    //
}