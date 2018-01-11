package com.laboratory.project.dao;


import com.laboratory.project.model.ProjectInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo projectInfo);

    ProjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectInfo projectInfo);

    int updateByPrimaryKey(ProjectInfo record);

    List<ProjectInfo> selectProjectAllByPage(Map<String,Object> paramMap);

    int selectProjectAllCount(Map<String,Object> parmMap);

    int updateStataus(Map<String,Object> projectMap);
}