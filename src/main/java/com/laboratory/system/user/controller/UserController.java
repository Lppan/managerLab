package com.laboratory.system.user.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.model.User;
import com.laboratory.system.user.service.UserService;
import com.laboratory.utils.BaseControllerRequest;
import com.laboratory.utils.LabConstant;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * usercontroller
 * Created by Lpan on 2017/12/22.
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUser(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> userMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = new ResponseModel();
        if(null != userMap && !userMap.isEmpty()){
            logger.info("提交的添加用户信息:"+ userMap.toString());
            responseModel= userService.insert(userMap);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setCode(LabConstant.operateModel.OPERATE_EMPTY);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"：用户信息不能为空");
        }
        //responseModel.setData("");
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/selectUserById" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String selectUserById(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
        Integer id = null;
        if(paramMap.containsKey("id")){
            id = Integer.valueOf(paramMap.get("id").toString());
        }
        ResponseModel responseModel = new ResponseModel();
        logger.info("根据用户id查询用户，用户id为："+ id);
        User user = new User();
        if(id != null && !"".equals(id)){
            responseModel = userService.selectByPrimaryKey(id);
        }else{
            //必要参数为空
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setCode(LabConstant.operateModel.OPERATE_EMPTY);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"：id不能为空");
        }
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/deleteById" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteById(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
        ResponseModel responseModel = new ResponseModel();
        if(paramMap.containsKey("id")){
           Integer id = Integer.valueOf(paramMap.get("id").toString());
            logger.info("根据用户id查询用户，用户id为："+ id);
            responseModel = userService.deleteByPrimaryKey(id);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/updateUser",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateUserById(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
        logger.info("修改的用户信息为:"+paramMap.toString());
        if(paramMap != null && !paramMap.isEmpty()){
            responseModel = userService.updateByPrimaryKeySelective(paramMap);
        }else{

        }
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public String updateStatus(HttpServletRequest request,String id){
        String status = null;
        if(null != id && !"".equals(id)){
            status = userService.updateStatus(id);
        }else{

        }
        return status;
    }

    @RequestMapping("/selectAllByPage")
    @ResponseBody
    public String selectAllByPage(HttpServletRequest request , @RequestParam Map<String,Object> paramMap){
        String returnStr = null;

        return returnStr;
    }


}
