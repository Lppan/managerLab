package com.laboratory.stoppage.dao;


import com.laboratory.stoppage.model.StopPage;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public interface StopPageMapper {

    int insert(Map<String,Object> map);

    int insertSelective(Map<String,Object> map);

    int deleteByPrimaryKey(Integer id);

    StopPage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StopPage record);

    int updateByPrimaryKey(StopPage record);
}