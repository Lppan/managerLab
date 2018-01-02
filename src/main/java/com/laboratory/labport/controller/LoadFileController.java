package com.laboratory.labport.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.PathUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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

/**
 *
 * upload and download file
 * Created by Lpan on 2018/1/2.
 */
@Controller
@RequestMapping(value = "/file", produces = "application/json;charset=UTF-8")
public class LoadFileController {

    private Logger  logger = Logger.getLogger(LoadFileController.class);

    /**
     * 上传文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    @ResponseBody
    public String  fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        ResponseModel responseModel = new ResponseModel();
        String directory = PathUtils.checkDirectory("oil","WIN");
        try {
            System.out.println(file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(directory,file.getOriginalFilename()));
            //上传成功，记录保存路径

            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        } catch (IOException e) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    public String MuiltUploadFile(HttpServletRequest request, @RequestParam("files")MultipartHttpServletRequest files){
        ResponseModel responseModel = new ResponseModel();


        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }


}
