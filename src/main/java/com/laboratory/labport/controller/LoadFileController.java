package com.laboratory.labport.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.labport.service.LoadFileService;
import com.laboratory.utils.BaseControllerRequest;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
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
        ResponseModel responseModel = loadFileService.uploadFile(file, requestMap);
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
        Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
        ResponseModel responseModel = loadFileService.multUploadFile(files,requestMap);
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }

    /**
     * 下载文件
     * @param request
     * @param response
     */
    @RequestMapping(value = "/download" )
    public  void downloadFile(HttpServletRequest request, HttpServletResponse response){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        //Map<String,Object> requestMap = JSONObject.fromObject(jsonParams);
        Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
        if (null != requestMap && !requestMap.isEmpty() && requestMap.containsKey("path")){
            String path = requestMap.get("path").toString();
            File file = new File(path);
            String fileName = file.getName();
            try {
                InputStream fis = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                String agent = (String)request.getHeader("USER-AGENT").toLowerCase();
                if(agent != null && agent.indexOf("msie") >0){ //IE
                    fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                }else if(agent != null && agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){		//IE11
                    fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                }else if(agent != null && agent.indexOf("chrome") > 0){   			//chrome
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }else if(agent != null && agent.indexOf("firefox") > 0){				//firefox
                    fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                }else{
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件
     * @param response
     * @param request
     */
    @RequestMapping("/downloadLocal")
    public void downloadLocalFile(HttpServletResponse response,HttpServletRequest request){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        //Map<String,Object> requestMap = JSONObject.fromObject(jsonParams);
        Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
        if (null != requestMap && !requestMap.isEmpty() && requestMap.containsKey("path")){
            String path = requestMap.get("path").toString();
            File file = new File(path);
            String fileName = file.getName();                   //获取文件名
            try {
                InputStream inputStream = new FileInputStream(path);        //根据路径读取文件
                String agent = (String)request.getHeader("USER-AGENT").toLowerCase();
                if(agent != null && agent.indexOf("msie") >0){ //IE
                    fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                }else if(agent != null && agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){		//IE11
                    fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                }else if(agent != null && agent.indexOf("chrome") > 0){   			//chrome
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }else if(agent != null && agent.indexOf("firefox") > 0){				//firefox
                    fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                }else{
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                byte [] bytes = new byte[100];
                int len;
                while((len = inputStream.read(bytes)) > 0){
                    response.getOutputStream().write(bytes,0,len);
                }
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/openFile")
    public void downloadOpen(HttpServletRequest request,HttpServletResponse response){
        Map<String, String> requestMap = BaseControllerRequest.getMapParams(request);
        if (null != requestMap && !requestMap.isEmpty() && requestMap.containsKey("path")){
            String path = requestMap.get("path").toString();
            File file = new File(path);
            if (!file.exists()){

                return;
            }
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
                byte []  bytes = new byte[1024];
                int len;
                response.reset();
                URL url = new URL("file:///"+path);
                response.setContentType(url.openConnection().getContentType());
                String fileName = file.getName();                   //获取文件名
                    String agent = (String)request.getHeader("USER-AGENT").toLowerCase();
                    if(agent != null && agent.indexOf("msie") >0){ //IE
                        fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                    }else if(agent != null && agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){		//IE11
                        fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
                    }else if(agent != null && agent.indexOf("chrome") > 0){   			//chrome
                        fileName = URLEncoder.encode(fileName, "UTF-8");
                    }else if(agent != null && agent.indexOf("firefox") > 0){				//firefox
                        fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
                    }else{
                        fileName = URLEncoder.encode(fileName, "UTF-8");
                    }
                //response.setHeader("Content-disposition", "inline;filename=" + fileName);
                 response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                OutputStream out = response.getOutputStream();
                while ((len = bufferedInputStream.read(bytes)) > 0)
                    out.write(bytes, 0, len);
                    bufferedInputStream.close();
                    out.close();
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
    }

}
