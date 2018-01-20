package com.laboratory.stoppage.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.stoppage.service.DataStopPageService;
import com.laboratory.utils.BaseControllerRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by shipan on 2017/12/29.
 */
@RestController
@RequestMapping("/dataStopPage")
public class DataStopPageController {

    private Logger logger = Logger.getLogger(DataStopPageController.class);

    @Autowired
    private DataStopPageService dataStopPageService;

    @RequestMapping("/addDataStopPage")
    public String addDataStopPage(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> dataStopPageMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataStopPageService.addDataStopPage(dataStopPageMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/deleteDataById")
    public String deleteDataById(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> dataStopPageMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataStopPageService.deleteDataaById(dataStopPageMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/selectDataById")
    public String selectDataStopPageById(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> dataStopPageMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataStopPageService.selectDataById(dataStopPageMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateDataById")
    public String updateDataStopPage(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> dataStopPageMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataStopPageService.updateDataStopPage(dataStopPageMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/debugType")
    public String showDebugType(HttpServletRequest request){
        ResponseModel responseModel = dataStopPageService.getDebugType();
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/debugInfoByType")
    public String getDebugInfoByType(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> dataStopPageMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataStopPageService.getDebugInfoByType(dataStopPageMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

}
