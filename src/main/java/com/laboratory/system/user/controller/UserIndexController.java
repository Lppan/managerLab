package com.laboratory.system.user.controller;
import com.alibaba.fastjson.JSONObject;
import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.model.User;
import com.laboratory.system.user.service.UserService;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MD5Utils;
import com.laboratory.utils.MapUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * user login  loginout
 * Created by Lpan on 2017/12/22.
 */
@Controller
@RequestMapping("/system")
public class UserIndexController {

    private Logger logger = Logger.getLogger(UserIndexController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping(value= "/login" , produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        Map<String, String> mapParams = MapUtils.getMapParams(request);
        boolean flag = MapUtils.isEmptyS(mapParams);
        //map 不为空
        if(flag){
            //根据用户名查询是否有该用户
            String username = mapParams.get("userName");
            String password = mapParams.get("password");
            logger.info("登录用户名："+username+"登录密码："+password);
            if(null != username &&  !"".equals(username) && null != password && !"".equals(password)){
                try{
                    List<User> users = userService.selectUserByUserName(mapParams);
                if(null != users && users.size() > 0){
                    User user = users.get(0);
                    if(user.getPassword().equals(MD5Utils.encode(password)) && user.getUserName().equals(username)){
                        //用户名密码正确
    //                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    //                    Subject currentUser = SecurityUtils.getSubject();
    //                    currentUser.login(token);
                        //获取权限信息

                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_SUCCESS_CODE);
                        responseModel.setCode(LabConstant.LabLogin.LOGIN_SUCCESS);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_SUCCESS_MESSAGE);

                    }else{
                        //用户名密码不正确
                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_FAILED_CODE);
                        responseModel.setCode(LabConstant.LabLogin.LOGIN_FAILED);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_FAILED_MESSAGE);
                    }
                }else{
                    //用户不存在
                    responseModel.setStatus(LabConstant.LabLogin.LOGIN_ERROR_CODE);
                    responseModel.setCode(LabConstant.LabLogin.LOGIN_ERROR);
                    responseModel.setMessage(LabConstant.LabLogin.LOGIN_ERROR_MESSAGE);
                }
                }catch (Exception e){
                    responseModel.setStatus(LabConstant.LabLogin.LOGIN_FAILED_CODE);
                    responseModel.setCode(LabConstant.LabLogin.LOGIN_FAILED);
                    responseModel.setMessage(LabConstant.LabLogin.LOGIN_FAILED_MESSAGE);
                }
            }else{
                    //用户名或密码不能为空
                responseModel.setStatus(LabConstant.LabLogin.LOGIN_EMPTY_CODE);
                responseModel.setCode(LabConstant.LabLogin.LOGIN_EMPTY);
                responseModel.setMessage(LabConstant.LabLogin.LOGIN_EMPTY_MESSAGE);
            }
        }
        String jsonString = JSONObject.toJSONString(responseModel);
        logger.info(jsonString);
        return jsonString;
    }



}
