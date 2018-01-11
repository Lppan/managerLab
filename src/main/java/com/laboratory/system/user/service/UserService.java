package com.laboratory.system.user.service;
import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.user.dao.UserMapper;
import com.laboratory.system.user.model.User;
import com.laboratory.utils.Base64Utils;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MapUtils;
import com.laboratory.utils.PageUtils;
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
     * @param user
     * @return
     */
    public ResponseModel insert(User user) {
        ResponseModel responseModel = checkUserMust(user);
        if (null != responseModel.getStatus()){
            return responseModel;
        }
        user.setPassword(Base64Utils.encodeStr(LabConstant.LabLogin.DEFAULT_PASSWORD));
        List<User> users = userDao.selectUser(user);
        if(null != users && users.size() > 0){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EXIST_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EXIST_MESSAGE);
        }else{
            String status = user.getStatus();
            if (status.equals("true")){
                user.setStatus("1");
            }else if (status.equals("false")){
                user.setStatus("0");
            }
            int insert = userDao.insertSelective(user);
            if (insert > 0) {
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            } else {
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
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
        if (null != id && !"".equals(id)){
            int i = userDao.deleteByPrimaryKey(id);
            if ( i > 0 ){
                //删除成功
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
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
        if (null != id && !"".equals(id)){
            User user = userDao.selectByPrimaryKey(id);
            if(null != user ){
                logger.info("根据id查询到用户信息："+user.toString());
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(user);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        return responseModel;
    }

    /**
     * 根据主键字段选择性的更新
     * @param user
     * @return
     */
    public ResponseModel updateByPrimaryKeySelective(User user){
//      ResponseModel responseModel = new ResponseModel();
        ResponseModel responseModel = checkUserMust(user);
        if (null != responseModel.getStatus()){
            return responseModel;
        }
        List<User> users = userDao.selectUser(user);
        if (null != users && users.size()>0){
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EXIST_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EXIST_MESSAGE);
            return responseModel;
        }
        //paramMap.put("password",MD5Utils.encode(paramMap.get("password").toString()));
        int updatestatus = userDao.updateByPrimaryKeySelective(user);
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
    public ResponseModel updateStatus(String id){
        ResponseModel responseModel = new ResponseModel();
        if (null != null && !"".equals(id)){
            int status = userDao.updateStatus(id);
            if( status > 0 ){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        return responseModel;
    }

    //根据登录名密码查询用户是否存在
    public List<User> selectUserByUserName(Map<String,Object> paramMap){
        List<User> users = new ArrayList<User>();
        if (null != paramMap){
            users = userDao.selectUserByUserName(paramMap);
        }
        return users;
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


    public ResponseModel checkUserMust(User user){
        ResponseModel responseModel = new ResponseModel();
        if (null != user){
            if (null == user.getUserName() || "".equals(user.getUserName())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
            }
            if (null == user.getStatus() || "".equals(user.getStatus())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":状态不能为空");
            }
            if (null == user.getType() || "".equals(user.getType())){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户类型不能为空");
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
//        if (null != paramMap && !paramMap.isEmpty()){
//            if (paramMap.containsKey("password") && null == paramMap.get("password") ||"".equals(paramMap.get("password"))){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
//                return responseModel;
//            }else if ( !paramMap.containsKey("password")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":密码不能为空");
//                return responseModel;
//            }
//            if (paramMap.containsKey("userName") && "".equals(paramMap.get("userName")) || null == paramMap.get("userName")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
//                return responseModel;
//            }else if ( !paramMap.containsKey("userName")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户名不能为空");
//                return responseModel;
//            }
//            if (paramMap.containsKey("type") && (null == paramMap.get("type").toString() || "".equals(paramMap.get("type").toString()))){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户类型不能为空");
//                return responseModel;
//            }else if ( !paramMap.containsKey("type")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户类型不能为空");
//                return responseModel;
//            }
//            if (paramMap.containsKey("status") && (null == paramMap.get("status").toString() || "".equals(paramMap.get("status").toString()))){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户状态不能为空");
//                return responseModel;
//            } else if ( !paramMap.containsKey("status")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":用户状态不能为空");
//                return responseModel;
//            }
//            if (paramMap.containsKey("status") && (null == paramMap.get("status").toString() || "".equals(paramMap.get("status").toString()))){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
//                return responseModel;
//            }else if ( !paramMap.containsKey("status")){
//                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":id不能为空");
//                return responseModel;
//            }
//        }else{
//            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
//            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
//        }
        return responseModel;
    }
}
