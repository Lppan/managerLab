package com.laboratory.system.user.service;
import com.alibaba.fastjson.JSONObject;
import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.dao.UserMapper;
import com.laboratory.system.user.model.User;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MD5Utils;
import com.laboratory.utils.MapUtils;
import com.laboratory.utils.PageUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理服务层
 * Created by Lpan on 2017/7/17.
 */
@Service
@Transactional
public class UserService {
    private Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private UserMapper userDao;

    /**
     * 添加用户
     * @param paramMap
     * @return
     */
    public ResponseModel insert(Map<String , Object> paramMap) {
        paramMap.put("password",LabConstant.LabLogin.DEFAULT_PASSWORD);
        ResponseModel responseModel = checkUserMust(paramMap);
        if (responseModel != null){
            return  responseModel;
        }
        try {
            List<User> users = userDao.selectUserByUserName(paramMap);
            if(null != users && users.size() > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EXIST_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EXIST_MESSAGE);
            }else{
                int insert = userDao.insertSelective(paramMap);
                if (insert > 0) {
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                } else {
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
                }
            }
        } catch (Exception e) {
            logger.info("保存用户异常：" + e.toString());
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EXCEPTION_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EXCEPTION_MESSAGE);
        }
        return responseModel;
    }

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    public ResponseModel deleteByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        int i = userDao.deleteByPrimaryKey(id);
        if ( i > 0 ){
            //删除成功
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
        }
        return responseModel;
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    public ResponseModel selectByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        User user = userDao.selectByPrimaryKey(id);
        if(null != user ){
            logger.info("根据id查询到用户信息："+user.toString());
            Map map = MapUtils.getValue(user);
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            responseModel.setData(map);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }
        return responseModel;
    }

    /**
     * 根据主键字段选择性的更新
     * @param paramMap
     * @return
     */
    public ResponseModel updateByPrimaryKeySelective(Map<String,Object> paramMap){
        ResponseModel responseModel = checkUserMust(paramMap);
        if (null != responseModel.getStatus()){
            return responseModel;
        }
        List<User> users = userDao.selectUserByUserName(paramMap);
        if (null != users && users.size()>0){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EXIST_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EXIST_MESSAGE);
            return responseModel;
        }
        paramMap.put("password",MD5Utils.encode(paramMap.get("password").toString()));
        int updatestatus = userDao.updateByPrimaryKeySelective(paramMap);
        if(updatestatus > 0 ){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }
        return responseModel;
    }

    //根据主键更新
    public int updateByPrimaryKey(User user){
        return userDao.updateByPrimaryKey(user);
    }
    //根据id 修改激活状态
    public String updateStatus(String id){
        ResponseModel responseModel = new ResponseModel();
        int status = userDao.updateStatus(id);
        if( status > 0 ){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
        }
        return JSONObject.toJSONString(responseModel);
    }

    //根据登录名手机号查询用户是否存在
    public List<User> selectUserByUserName(Map<String,Object> paramMap){
        return userDao.selectUserByUserName(paramMap);
    }
    //分页查询
    public ResponseModel selectAllByPage(HttpServletRequest request,Map<String, Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        int pageCount = userDao.selectAllByPageCount(paramMap);
        paramMap.put("count",pageCount);
        Map<String,Object> pageMap = new HashMap<String,Object>();
        pageMap.put("count",pageCount);
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        List<User> userList = userDao.selectAllByPage(PageUtils.Page(request, paramMap));
        PageUtils.calculate(pageMap);
        for(User user:userList){
            paramMap = MapUtils.getValue(user);
            mapList.add(paramMap);
        }
        if( null != userList && userList.size() > 0 ){
            responseModel.setData(mapList);
            responseModel.setPageMap(pageMap);
            responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }
        return responseModel;
    }


    public ResponseModel checkUserMust(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            if (paramMap.containsKey("password") && null == paramMap.get("password") ||"".equals(paramMap.get("password"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("password")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("userName") && "".equals(paramMap.get("userName")) || null == paramMap.get("userName")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("userName")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("type") && (null == paramMap.get("type").toString() || "".equals(paramMap.get("type").toString()))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户类型不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("type")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户类型不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("status") && (null == paramMap.get("status").toString() || "".equals(paramMap.get("status").toString()))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户状态不能为空");
                return responseModel;
            } else if ( !paramMap.containsKey("status")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户状态不能为空");
                return responseModel;
            }
            if (paramMap.containsKey("status") && (null == paramMap.get("status").toString() || "".equals(paramMap.get("status").toString()))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
                return responseModel;
            }else if ( !paramMap.containsKey("status")){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }
}
