package com.laboratory.laboratory.dao;


import com.laboratory.laboratory.model.Laboratory;

public interface LaboratoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Laboratory record);

    int insertSelective(Laboratory record);

    Laboratory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Laboratory record);

    int updateByPrimaryKey(Laboratory record);
}