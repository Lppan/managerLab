package com.laboratory.system.user.controller;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.model.User;
import com.laboratory.system.user.service.UserService;
import com.laboratory.utils.LabConstant;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * usercontroller
 * Created by Lpan on 2017/12/22.
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String addUser(HttpServletRequest request, @RequestBody User user){
        //JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        //Map<String,Object> userMap = JSONObject.fromObject(jsonParams);
        //ResponseModel responseModel = new ResponseModel();
        logger.info("提交的添加用户信息:"+ user.toString());
        ResponseModel responseModel= userService.insert(user);
        responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
        responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"：用户信息不能为空");
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/selectUserById" , produces = "text/html;charset=UTF-8")
    public String selectUserById(HttpServletRequest request,@RequestBody Integer id){
//       JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//       Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
//       Integer id = null;
         logger.info("根据用户id查询用户，用户id为："+ id);
         ResponseModel responseModel = userService.selectByPrimaryKey(id);
         JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/deleteById" , produces = "text/html;charset=UTF-8")
    public String deleteById(HttpServletRequest request,@RequestBody Integer id){
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
//        ResponseModel responseModel = new ResponseModel();
          logger.info("根据用户id查询用户，用户id为："+ id);
          ResponseModel responseModel = userService.deleteByPrimaryKey(id);
          JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/updateUser",produces = "text/html;charset=UTF-8")
    public String updateUserById(HttpServletRequest request,@RequestBody User user){
        ResponseModel responseModel = new ResponseModel();
//      JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//      Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
        logger.info("修改的用户信息为:"+user.toString());
        responseModel = userService.updateByPrimaryKeySelective(user);

        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(HttpServletRequest request,String id){
        ResponseModel responseModel = userService.updateStatus(id);
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }

    @RequestMapping(value = "/getUserList",produces = "text/html;charset=UTF-8")
    public String selectAllByPage(HttpServletRequest request){
//        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
//        Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        if (null == paramMap){
            paramMap = new HashedMap();
        }
        ResponseModel responseModel = userService.selectAllByPage(request, paramMap);
        JSONObject object = JSONObject.fromObject(responseModel);
        return object.toString();
    }


}
