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
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * user login  loginout
 * Created by Lpan on 2017/12/22.
 */
@Controller
@RequestMapping(value = "/system" , produces = "text/html;charset=UTF-8")
public class UserIndexController {

    private Logger logger = Logger.getLogger(UserIndexController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping(value= "/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        JSONObject jsonParams = BaseControllerRequest.getJSONParams(request);
        ResponseModel responseModel = new ResponseModel();
        if (null != jsonParams && !"".equals(jsonParams)){
            Map<String,Object> paramMap = JSONObject.fromObject(jsonParams);
            responseModel = checkUserNameAndPassword(paramMap);
            Integer status = responseModel.getStatus();
            if (null == responseModel.getData() && "".equals(responseModel.getData()) ){
                JSONObject jsonObject = JSONObject.fromObject(responseModel);
                return jsonObject.toString();
            }else{
                //根据用户名查询是否有该用户
                String username = paramMap.get("userName").toString();
                String password = paramMap.get("password").toString();
                logger.info("登录用户名："+username+",   登录密码："+password);

                List<User> users = userService.selectUserByUserName(paramMap);
                if(null != users && users.size() > 0){
                    User user = users.get(0);
                    if(user.getPassword().equals(MD5Utils.encode(password)) && user.getUserName().equals(username)){
                        //用户名密码正确
                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_SUCCESS_CODE);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_SUCCESS_MESSAGE);
                        request.getSession().setAttribute("user", user);
                        request.getSession().setMaxInactiveInterval(-1);
                        responseModel.setData(user);
                    }else{
                        //用户名密码不正确
                        responseModel.setStatus(LabConstant.LabLogin.LOGIN_FAILED_CODE);
                        responseModel.setMessage(LabConstant.LabLogin.LOGIN_FAILED_MESSAGE);
                    }
                }else{
                    //用户不存在
                    responseModel.setStatus(LabConstant.LabLogin.LOGIN_ERROR_CODE);
                    responseModel.setMessage(LabConstant.LabLogin.LOGIN_ERROR_MESSAGE);
                }
            }
        }else{
            responseModel.setStatus(LabConstant.LabLogin.LOGIN_EMPTY_CODE);
            responseModel.setMessage(LabConstant.LabLogin.LOGIN_EMPTY_MESSAGE);
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        logger.info(jsonObject.toString());
        return jsonObject.toString();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        ResponseModel responseModel = new ResponseModel();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (null != user){
            session.removeAttribute("user");
            responseModel.setStatus(LabConstant.LabLogin.LOGIN_SUCCESS_CODE);
            responseModel.setMessage(LabConstant.LabLogin.LOGIN_SUCCESS_LOGOUT_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.LabLogin.LOGIN_ERROR_CODE);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
        }
        JSONObject jsonObject = JSONObject.fromObject(responseModel);
        return jsonObject.toString();
    }


    public ResponseModel checkUserNameAndPassword(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            if (paramMap.containsKey("userName") && null == paramMap.get("userName") && "".equals(paramMap.get("userName"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("password") && null == paramMap.get("password") && "".equals(paramMap.get("password"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }


}
