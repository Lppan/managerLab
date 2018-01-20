package com.laboratory.laboratory.controller;

import com.laboratory.laboratory.model.Laboratory;
import com.laboratory.laboratory.service.LaboratoryService;
import com.laboratory.labport.model.ResponseModel;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * Created by shipan on 2018/1/8.
 */
@RestController
@RequestMapping(value = "/laboratory",produces = "application/json;charset=UTF-8")
public class LaboratoryController {

    private Logger logger = Logger.getLogger(LaboratoryController.class);
    @Autowired
    private LaboratoryService laboratoryService;

    @RequestMapping("/showLabList")
    public String ShowLabList(HttpServletRequest request,Map<String,Object> paramMap){
        ResponseModel responseModel = laboratoryService.selectLaboratoryAllByPage(paramMap, request);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("addLaboratory")
    public String addLaboratory(HttpServletRequest request, @RequestBody Laboratory laboratory){
        ResponseModel responseModel = laboratoryService.insertSelective(laboratory);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateLaboratory")
    public String updateLaboratory(HttpServletRequest request,@RequestBody Laboratory laboratory){
        ResponseModel responseModel = laboratoryService.updateByPrimaryKeySelective(laboratory);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateStatusUp")
    public String updateStatusUp(HttpServletRequest request,@RequestBody Integer id){
        ResponseModel responseModel = laboratoryService.updateStatusUp(id);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/updateStatusDown")
    public String updateStatusDown(HttpServletRequest request,@RequestBody Integer id){
        ResponseModel responseModel = laboratoryService.updateStatusDown(id);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }
}
