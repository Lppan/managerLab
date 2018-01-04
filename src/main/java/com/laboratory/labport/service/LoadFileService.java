package com.laboratory.labport.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.PathUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * file operate
 * Created by Lpan on 2018/1/3.
 */
@Service
@Transactional
public class LoadFileService {

    private Logger logger = Logger.getLogger(LoadFileService.class);

    public ResponseModel uploadFile(MultipartFile file,String type){
        ResponseModel responseModel = new ResponseModel();
        logger.info("上传文件名："+file.getOriginalFilename());
        String directory = PathUtils.checkDirectory(type);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(directory,file.getOriginalFilename()));
            //上传成功，返回路径
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            responseModel.setData(directory+"/"+file.getOriginalFilename());
        } catch (IOException e) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            logger.error("上传文件异常！");
            e.printStackTrace();
        }
        return responseModel;
    }
    public ResponseModel  multUploadFile(MultipartHttpServletRequest files) {
        ResponseModel responseModel = new ResponseModel();          //返回模型
        Map<String, MultipartFile> fileMap = files.getFileMap();          //接受的文件
        Map<String,String> resultMap= new HashMap<String, String>();    //返回
        String directory = PathUtils.checkDirectory("");
        for (Map.Entry<String,MultipartFile> file:fileMap.entrySet()){
            MultipartFile fileValue = file.getValue();                              //上传文件
            String filename = fileValue.getOriginalFilename();              //文件名 (带后缀)
            try {
                FileUtils.copyInputStreamToFile(fileValue.getInputStream(),new File(directory,filename));
                resultMap.put(filename,directory+"/"+filename);
            } catch (IOException e) {
                resultMap.put(filename,"ERROR");
                logger.info("上传文件失败;"+filename);
                e.printStackTrace();
            }
        }
        responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
        responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
        responseModel.setData(resultMap);
        return responseModel;
    }


}
