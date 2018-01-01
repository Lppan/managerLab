package com.laboratory.project.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.project.dao.ProjectInfoMapper;
import com.laboratory.project.model.ProjectInfo;
import com.laboratory.utils.DateConversionUtils;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MapUtils;
import com.laboratory.utils.PageUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shipan on 2017/12/28.
 */
@Service
@Transactional
public class ProjectInfoService {

    private Logger logger = Logger.getLogger(ProjectInfoService.class);

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    public ResponseModel selectProjectAllByPage(Map<String,Object> paramMap, HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            int pageCount = projectInfoMapper.selectProjectAllCount(paramMap);
            paramMap.put("count",pageCount);
            List<ProjectInfo> projectInfoList = projectInfoMapper.selectProjectAllByPage(PageUtils.Page(request,paramMap));
            Map<String,Object> pageMap = new HashMap<String,Object>();
            pageMap.put("count",pageCount);
            PageUtils.calculate(pageMap);
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            for (ProjectInfo projectInfo:projectInfoList){
                paramMap = MapUtils.getValue(projectInfo);
                mapList.add(paramMap);
            }
            if (null != projectInfoList && projectInfoList.size() > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(mapList);
                responseModel.setPageMap(pageMap);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
            }

        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }


    public ResponseModel insertSelective(Map<String,Object> projectMap){
        ResponseModel responseModel = checkProjectMust(projectMap);
        if (null != responseModel.getStatus()){
            return responseModel;
        }else{
            String planBeginTime = DateConversionUtils.valueToDate((Long)projectMap.get("planBeginTime"));
            String planEndTime = DateConversionUtils.valueToDate((Long)projectMap.get("planEndTime"));
            projectMap.put("planBeginTime",planBeginTime);
            projectMap.put("planEndTime",planEndTime);
            int selective = projectInfoMapper.insertSelective(projectMap);
            if(selective > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }

    public ResponseModel deleteByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        if(null != id && !"".equals(id)){
            int primaryKey = projectInfoMapper.deleteByPrimaryKey(id);
            if(primaryKey > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        return responseModel;
    }

    public ResponseModel selectByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        if(null != id && !"".equals(id)){
            ProjectInfo projectInfo = projectInfoMapper.selectByPrimaryKey(id);
            Map projectMap = MapUtils.getValue(projectInfo);
            if(null != projectInfo){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(projectMap);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id 不能为空");
        }
        return responseModel;
    }

    public ResponseModel updateByPrimaryKeySelective(Map<String,Object> projectMap){
        ResponseModel responseModel = checkProjectMust(projectMap);
        if (null != responseModel){
            return responseModel;
        }else{
            String planBeginTime = DateConversionUtils.valueToDate((Long)projectMap.get("planBeginTime"));
            String planEndTime = DateConversionUtils.valueToDate((Long)projectMap.get("planEndTime"));
            projectMap.put("planBeginTime",planBeginTime);
            projectMap.put("planEndTime",planEndTime);
            int keySelective = projectInfoMapper.updateByPrimaryKeySelective(projectMap);
            if(keySelective >0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }

    public int updateByPrimaryKey(ProjectInfo record){
        return projectInfoMapper.updateByPrimaryKey(record);
    }


    public ResponseModel checkProjectMust(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            if (paramMap.containsKey("projectName") && null == paramMap.get("projectName") || "".equals(paramMap.get("projectName"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目名不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("projectName")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目名不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("parentProjectName") && null == paramMap.get("parentProjectName") || "".equals(paramMap.get("parentProjectName"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":所属项目不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("parentProjectName")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":所属项目不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("projectPrincipal") && null == paramMap.get("projectPrincipal") || "".equals(paramMap.get("projectPrincipal"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目负责人不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("projectPrincipal")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目负责人不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("machineType") && null == paramMap.get("machineType") || "".equals(paramMap.get("machineType"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机型不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("machineType")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机型不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("machineNo") && null == paramMap.get("machineNo") || "".equals(paramMap.get("machineNo"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机号不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("machineNo")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机号不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("powerRate") && null == paramMap.get("powerRate") || "".equals(paramMap.get("powerRate"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":机器功率不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("powerRate")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":机器功率不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("planBeginTime") && null == paramMap.get("planBeginTime") || "".equals(paramMap.get("planBeginTime"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划开始时间不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("planBeginTime")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划开始时间不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("planEndTime") && null == paramMap.get("planEndTime") || "".equals(paramMap.get("planEndTime"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划结束时间不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("planEndTime")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划结束时间不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("projectCycle") && null == paramMap.get("projectCycle") || "".equals(paramMap.get("projectCycle"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目周期不能为空");
                return responseModel;
            } else if (!paramMap.containsKey("projectCycle")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目周期不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("projectContent") && null == paramMap.get("projectContent") || "".equals(paramMap.get("projectContent"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验内容不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("projectContent")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验内容不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("operationInstruction") && null == paramMap.get("operationInstruction") || "".equals(paramMap.get("operationInstruction"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":作业指导书不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("operationInstruction")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":作业指导书不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("propertyCharacterList") && null == paramMap.get("propertyCharacterList") || "".equals(paramMap.get("propertyCharacterList"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":性能特性清单不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("propertyCharacterList")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":性能特性清单不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("machineNoList") && null == paramMap.get("machineNoList") || "".equals(paramMap.get("machineNoList"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":机型信息表不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("machineNoList")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":机型信息表不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("dais") && null == paramMap.get("dais") || "".equals(paramMap.get("dais"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验台架不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("dais")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验台架不能为空");
                return responseModel;
            }
            if(paramMap.containsKey("status") && null == paramMap.get("status") || "".equals(paramMap.get("status"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目状态不能为空");
                return responseModel;
            }else if (!paramMap.containsKey("status")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目状态不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }
}
