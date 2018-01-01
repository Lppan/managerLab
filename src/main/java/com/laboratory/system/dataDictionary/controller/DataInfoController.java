package com.laboratory.system.dataDictionary.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.dataDictionary.service.DataInfoService;
import com.laboratory.utils.BaseControllerRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by shipan on 2018/1/1.
 */
@Controller
@RequestMapping(value = "/data",produces = "application/json;charset=UTF-8")
public class DataInfoController {

    private Logger logger = Logger.getLogger(DataInfoController.class);

    @Autowired
    private DataInfoService dataInfoService;

    @RequestMapping("/addDataInfo")
    @ResponseBody
    public String addDataInfo(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> datatMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataInfoService.insertSelective(datatMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/selectDataDictionary")
    @ResponseBody
    public String selectDataDictionary(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> datatMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = dataInfoService.selectDataDictionary(datatMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

}
