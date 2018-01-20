package com.laboratory.labport.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.labport.service.ImportExcelFileService;
import com.laboratory.labport.service.LoadFileService;
import com.laboratory.utils.BaseControllerRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 * import excel
 * Created by Lpan on 2018/1/5.
 */
@Controller
@RequestMapping("/importExcel")
public class ImportExcelFileController {
        private  Logger  logger = Logger.getLogger(ImportExcelFileController.class);

        @Autowired
        private LoadFileService loadFileService;

        @Autowired
        private ImportExcelFileService importExcelFileService;
        @RequestMapping("/importExcel")
        public ResponseModel importExcelModel (HttpServletRequest request, @RequestParam("file") MultipartFile file){
            Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
            String type = null;
            if(null != requestMap &&  requestMap.containsKey("type")){
                type = requestMap.get("type").toString();
            }
            //上穿文件
            ResponseModel responseModel = loadFileService.uploadFile(file, requestMap);
            String path = (String)responseModel.getData();
            //读取文件 并将数据保存到数据库
            importExcelFileService.readExcelFileOne(path);
            return responseModel;
        }






}
