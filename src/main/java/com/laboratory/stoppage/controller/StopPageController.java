package com.laboratory.stoppage.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.stoppage.model.StopPage;
import com.laboratory.stoppage.service.StopPageService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by shipan on 2017/12/28.
 */
@RestController
@RequestMapping("/stopPage")
public class StopPageController {

    private Logger logger = Logger.getLogger(StopPageController.class);
    @Autowired
    private StopPageService stopPageService;

    @RequestMapping("/debugList")
    public String showDebugList(HttpServletRequest request, @RequestBody Map<String,Object> paramMap){
        ResponseModel responseModel = stopPageService.showDebugList(paramMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    @RequestMapping("/addStopPage")
    public String addDebugInfo(HttpServletRequest request, @RequestBody StopPage stopPage){
        ResponseModel responseModel = stopPageService.insert(stopPage);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }



}
