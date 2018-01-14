package com.laboratory.laboratory.dao;


import com.laboratory.laboratory.model.Laboratory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LaboratoryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Laboratory record);

    int insertSelective(Laboratory record);

    Laboratory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Laboratory record);

    int updateByPrimaryKey(Laboratory record);

    int selectLaboratoryCount(Map<String,Object> paramaMap);

    List<Laboratory> selectLaboratoryAllByPage(Map<String, Object> paramMap);

    int updateStatus(Map<String, Object> paramMap);
}