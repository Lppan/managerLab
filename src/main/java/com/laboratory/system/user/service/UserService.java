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

    public ResponseModel insert(Map<String , Object> paramMap) {
        ResponseModel responseModel = new ResponseModel();
        if (paramMap.containsKey("password") && null != paramMap.get("password") && !"".equals(paramMap.get("password"))) {
            paramMap.put("password", MD5Utils.encode(paramMap.get("password").toString()));
        } else {
            //密码为空
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
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
    //根据主键id删除
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
    //根据id进行查询
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

    //根据主键字段选择性的更新
    public ResponseModel updateByPrimaryKeySelective(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (paramMap.containsKey("password") && null != paramMap.get("password") && !"".equals(paramMap.get("password"))) {
            paramMap.put("password", MD5Utils.encode(paramMap.get("password").toString()));
        } else {
            //密码为空

        }
        int updatesattus = userDao.updateByPrimaryKeySelective(paramMap);
        if(updatesattus > 0 ){
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
    //分查询总记录数
    public int selectAllByPageCount(Map<String,Object> paramMap){
        return userDao.selectAllByPageCount(paramMap);
    }
}
