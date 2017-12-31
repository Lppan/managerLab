package com.laboratory.stoppage.dao;

import org.springframework.stereotype.Repository;

import com.laboratory.stoppage.model.DataStopPage;

import java.util.Map;

@Repository
public interface DataStopPageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DataStopPage record);

    int insertSelective(Map<String,Object> paramMap);

    DataStopPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Map<String,Object> paramMap);

    int updateByPrimaryKey(DataStopPage record);

    Map<String,Object> getDebugType();

    Map<String,Object> getDebugInfoByType(Integer parentId);
}