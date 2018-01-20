package com.laboratory.project.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.project.model.ProjectInfo;
import com.laboratory.project.service.ProjectInfoService;
import com.laboratory.utils.LabConstant;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 *
 *
 *
 * Created by shipan on 2017/12/28.
 */
@RestController
@RequestMapping(value = "/projectInfo",produces = "application/json;charset=UTF-8")
public class ProjectInfoController {

    private Logger logger = Logger.getLogger(ProjectInfoController.class);

    @Autowired
    private ProjectInfoService projectInfoService;
    @RequestMapping("/projectList")
    public String showListProject(HttpServletRequest request, @RequestBody Map<String,Object> projectMap){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        //Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        //Map<String, String> mapParams = BaseControllerRequest.getMapParams(request);
        ResponseModel responseModel = projectInfoService.selectProjectAllByPage(projectMap, request);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/addProjectInfo")
    public String addProjectInfo(HttpServletRequest request, @RequestBody ProjectInfo projectInfo){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        //Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        logger.info("添加项目："+projectInfo.toString());
        ResponseModel responseModel = projectInfoService.insertSelective(projectInfo);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }
    @RequestMapping("/selectById")
    public String selectProjectById(HttpServletRequest request,@RequestBody Integer id){
        ResponseModel responseModel = new ResponseModel();
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
//        if(null != projectMap && projectMap.containsKey("id")){
//            Integer id = Integer.valueOf(projectMap.get("id").toString());
            logger.info("根据id查询项目，id:"+id);
            responseModel = projectInfoService.selectByPrimaryKey(id);
//        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
//        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/deleteProjectById")
    public String deleteProjectById(HttpServletRequest request,@RequestBody Integer id){
        ResponseModel responseModel = new ResponseModel();
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
//        if(null != projectMap && projectMap.containsKey("id")){
//            Integer id = Integer.valueOf(projectMap.get("id").toString());
            logger.info("根据id删除项目：id"+id);
            responseModel = projectInfoService.deleteByPrimaryKey(id);
//        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
//        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateProject")
    public String updateProject(HttpServletRequest request,@RequestBody ProjectInfo projectInfo){
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        logger.info("更新项目："+projectInfo.toString());
        ResponseModel responseModel = projectInfoService.updateByPrimaryKeySelective(projectInfo);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/pass")
    public String setProjectStatus(HttpServletRequest request,@RequestBody Map<String,Object> projectMap){
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = projectInfoService.setProjectStatus(projectMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/back")
    public String setProjectStatusBack(HttpServletRequest request,@RequestBody Map<String,Object> projectMap){
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> projectMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = projectInfoService.setProjectStatusBack(projectMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

}
