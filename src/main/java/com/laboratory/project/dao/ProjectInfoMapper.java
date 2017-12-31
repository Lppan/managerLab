package com.laboratory.project.dao;


import com.laboratory.project.model.ProjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInfo record);

    int insertSelective(Map<String,Object> projectMap);

    ProjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Map<String,Object> projectMap);

    int updateByPrimaryKey(ProjectInfo record);

    List<ProjectInfo> selectProjectAllByPage(Map<String,Object> paramMap);

    int selectProjectAllCount(Map<String,Object> parmMap);
}