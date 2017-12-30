package com.laboratory.system.user.controller;
import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.model.User;
import com.laboratory.system.user.service.UserService;
import com.laboratory.utils.BaseControllerRequest;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MD5Utils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        net.sf.json.JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        Map<String,Object> paramMap = net.sf.json.JSONObject.fromObject(jsonParams);
        if (null != paramMap && paramMap.containsKey("userName") && paramMap.containsKey("password")){
            //根据用户名查询是否有该用户
            String username = paramMap.get("userName").toString();
            String password = paramMap.get("password").toString();
            logger.info("登录用户名："+username+"登录密码："+password);
            if(null != username &&  !"".equals(username) && null != password && !"".equals(password)){
                try{
                    List<User> users = userService.selectUserByUserName(paramMap);
                if(null != users && users.size() > 0){
                    User user = users.get(0);
                    if(user.getPassword().equals(MD5Utils.encode(password)) && user.getUserName().equals(username)){
                        //用户名密码正确
    //                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    //                    Subject currentUser = SecurityUtils.getSubject();
    //                    currentUser.login(token);
                        //获取权限信息

                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_SUCCESS_CODE);
                        //responseModel.setCode(LabConstant.LabLogin.LOGIN_SUCCESS);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_SUCCESS_MESSAGE);

                    }else{
                        //用户名密码不正确
                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_FAILED_CODE);
                        //responseModel.setCode(LabConstant.LabLogin.LOGIN_FAILED);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_FAILED_MESSAGE);
                    }
                }else{
                    //用户不存在
                    responseModel.setStatus(LabConstant.LabLogin.LOGIN_ERROR_CODE);
                    //responseModel.setCode(LabConstant.LabLogin.LOGIN_ERROR);
                    responseModel.setMessage(LabConstant.LabLogin.LOGIN_ERROR_MESSAGE);
                }
                }catch (Exception e){
                    responseModel.setStatus(LabConstant.LabLogin.LOGIN_FAILED_CODE);
                    //responseModel.setCode(LabConstant.LabLogin.LOGIN_FAILED);
                    responseModel.setMessage(LabConstant.LabLogin.LOGIN_FAILED_MESSAGE);
                }
            }else{
                    //用户名或密码不能为空
                responseModel.setStatus(LabConstant.LabLogin.LOGIN_EMPTY_CODE);
                //responseModel.setCode(LabConstant.LabLogin.LOGIN_EMPTY);
                responseModel.setMessage(LabConstant.LabLogin.LOGIN_EMPTY_MESSAGE);
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        logger.info(jsonObject.toString());
        return jsonObject.toString();
    }



}
