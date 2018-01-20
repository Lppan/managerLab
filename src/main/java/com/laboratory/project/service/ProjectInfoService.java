package com.laboratory.project.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.project.dao.ProjectInfoMapper;
import com.laboratory.project.model.ProjectInfo;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MapUtils;
import com.laboratory.utils.PageUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
            logger.info("查询结果："+projectInfoList.toString());
            PageUtils.calculate(paramMap);
            List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
            for (ProjectInfo projectInfo:projectInfoList){
                paramMap = MapUtils.getValue(projectInfo);
                mapList.add(paramMap);
            }
            if (null != projectInfoList && projectInfoList.size() > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(JSONArray.fromObject(projectInfoList));
                responseModel.setPageMap(paramMap);
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


    public ResponseModel insertSelective(ProjectInfo projectInfo){
        ResponseModel responseModel = checkProjectMust(projectInfo);
        if (null != responseModel.getStatus()){
            return responseModel;
        }else{
            //String planBeginTime = DateConversionUtils.valueToDate((Long)projectMap.get("planBeginTime"));
            //String planEndTime = DateConversionUtils.valueToDate((Long)projectMap.get("planEndTime"));

            //projectMap.put("planBeginTime",planBeginTime);
            //projectMap.put("planEndTime",planEndTime);

            int selective = projectInfoMapper.insertSelective(projectInfo);
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

    public ResponseModel updateByPrimaryKeySelective(ProjectInfo projectInfo){
        ResponseModel responseModel = checkProjectMust(projectInfo);
        if (null != responseModel){
            return responseModel;
        }else{
//            String planBeginTime = DateConversionUtils.valueToDate((Long)projectInfo.getPlanBeginTime());
//            String planEndTime = DateConversionUtils.valueToDate((Long)projectMap.get("planEndTime"));
//            projectMap.put("planBeginTime",planBeginTime);
//            projectMap.put("planEndTime",planEndTime);
            int keySelective = projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
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

    public ResponseModel setProjectStatus(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && paramMap.containsKey("id")){
            Integer id = Integer.valueOf(paramMap.get("id").toString());
            paramMap.put("status",3);
        }
        int i = projectInfoMapper.updateStataus(paramMap);
        if (i > 0){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel setProjectStatusBack(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty() && paramMap.containsKey("id")){
            Integer id = Integer.valueOf(paramMap.get("id").toString());
            paramMap.put("status",2);
            int i = projectInfoMapper.updateStataus(paramMap);
            if (i > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        return responseModel;
    }


    public ResponseModel checkProjectMust(ProjectInfo projectInfo){
        ResponseModel responseModel = new ResponseModel();
        if (null != projectInfo){
            //获取成员变量
           if (null == projectInfo.getProjectName() && "".equals(projectInfo.getProjectName())){
               responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
               responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目名不能为空");
               return responseModel;
           }
            if (null == projectInfo.getParentProjectName() && "".equals(projectInfo.getParentProjectName())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":所属项目名不能为空");
                return responseModel;
            }
            if (null == projectInfo.getProjectPrincipal()&& "".equals(projectInfo.getProjectPrincipal())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目负责人不能为空");
                return responseModel;
            }if (null == projectInfo.getMachineType() && "".equals(projectInfo.getMachineType())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机型不能为空");
                return responseModel;
            }if (null == projectInfo.getMachineNo() && "".equals(projectInfo.getMachineNo())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验机号不能为空");
                return responseModel;
            }if (null == projectInfo.getPowerRate() && "".equals(projectInfo.getPowerRate())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":机器功率不能为空");
                return responseModel;
            }if (null == projectInfo.getPlanBeginTime() && "".equals(projectInfo.getPlanBeginTime())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划开始时间不能为空");
                return responseModel;
            }if (null == projectInfo.getPlanEndTime() && "".equals(projectInfo.getPlanEndTime())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目计划结束时间不能为空");
                return responseModel;
            }if (null == projectInfo.getProjectCycle() && "".equals(projectInfo.getProjectCycle())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目周期不能为空");
                return responseModel;
            }if (null == projectInfo.getProjectContent()&& "".equals(projectInfo.getProjectContent())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验任务不能为空");
                return responseModel;
            }if (null == projectInfo.getDais() && "".equals(projectInfo.getDais())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":实验台架不能为空");
                return responseModel;
            }if (null == projectInfo.getStatus() && "".equals(projectInfo.getStatus())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目状态不能为空");
                return responseModel;
            }if (null == projectInfo.getProjectName() && "".equals(projectInfo.getProjectName())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":项目名不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }
}
