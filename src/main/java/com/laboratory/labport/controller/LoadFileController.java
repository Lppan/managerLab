package com.laboratory.labport.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.labport.service.LoadFileService;
import com.laboratory.utils.BaseControllerRequest;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.PathUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * upload and download file
 * Created by Lpan on 2018/1/2.
 */
@Controller
@RequestMapping(value = "/file", produces = "application/json;charset=UTF-8")
public class LoadFileController {

    private Logger  logger = Logger.getLogger(LoadFileController.class);

    @Autowired
    private LoadFileService loadFileService;

    /**
     * 上传文件(单个文件)
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    @ResponseBody
    public String  fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
       // Map<String,Object> requestMap = JSONObject.fromObject(jsonParams);
        Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
        String type = null;
        if(null != requestMap &&  requestMap.containsKey("type")){
            type = requestMap.get("type").toString();
        }
        ResponseModel responseModel = loadFileService.uploadFile(file, type);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    /**
     *  上传文件(批量)
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value  = "/multUploadFile" , method= RequestMethod.POST)
    @ResponseBody
    public String MultUploadFile(HttpServletRequest request, MultipartHttpServletRequest files){
        ResponseModel responseModel = loadFileService.multUploadFile(files);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }


}
