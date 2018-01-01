package com.laboratory.project.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.project.service.ProjectInfoService;
import com.laboratory.utils.BaseControllerRequest;
import com.laboratory.utils.LabConstant;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by shipan on 2017/12/28.
 */
@Controller
@RequestMapping(value = "/projectInfo",produces = "application/json;charset=UTF-8")
public class ProjectInfoController {

    private Logger logger = Logger.getLogger(ProjectInfoController.class);

    @Autowired
    private ProjectInfoService projectInfoService;
    @RequestMapping("/projectList")
    @ResponseBody
    public String showListProject(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = projectInfoService.selectProjectAllByPage(projectMap, request);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/addProjectInfo")
    @ResponseBody
    public String addProjectInfo(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        logger.info("添加项目："+projectMap.toString());
        ResponseModel responseModel = projectInfoService.insertSelective(projectMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }
    @RequestMapping("/selectById")
    @ResponseBody
    public String selectProjectById(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        if(null != projectMap && projectMap.containsKey("id")){
            Integer id = Integer.valueOf(projectMap.get("id").toString());
            logger.info("根据id查询项目，id:"+id);
            responseModel = projectInfoService.selectByPrimaryKey(id);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/deleteProjectById")
    @ResponseBody
    public String deleteProjectById(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        if(null != projectMap && projectMap.containsKey("id")){
            Integer id = Integer.valueOf(projectMap.get("id").toString());
            logger.info("根据id删除项目：id"+id);
            responseModel = projectInfoService.deleteByPrimaryKey(id);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateProject")
    @ResponseBody
    public String updateProject(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        logger.info("更新项目："+projectMap.toString());
        responseModel = projectInfoService.updateByPrimaryKeySelective(projectMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }
}
