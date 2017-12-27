package com.laboratory.utils;/**
 * Created by Administrator on 2017/12/25.
 */

/**
 * lab constant
 * Created by Lpan on 2017/12/25.
 */
public class LabConstant {
    public static class LabLogin{
        public static final int LOGIN_SUCCESS_CODE = 0000;
        public static final int LOGIN_FAILED_CODE = 0001;
        public static final int LOGIN_ERROR_CODE = 0002;
        public static final int LOGIN_EMPTY_CODE = 0003;
        public static final String LOGIN_SUCCESS = "SUCCESS";
        public static final String LOGIN_FAILED= "FAILD";
        public static final String LOGIN_ERROR= "ERROR";
        public static final String LOGIN_EMPTY= "EMPTY";
        public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";
        public static final String LOGIN_FAILED_MESSAGE = "用户名或密码不正确";
        public static final String LOGIN_ERROR_MESSAGE = "用户不存在";
        public static final String LOGIN_EMPTY_MESSAGE = "用户不或密码不能为空";

    }
    public static class  operateModel{
        public static final int OPERATE_SUCCESS_STATUS = 0000;
        public static final int OPERATE_FAILED_STATUS = 0001;
        public static final int OPERATE_EXCEPTION_STATUS = 0002;
        public static final int OPERATE_EMPTY_STATUS = 0003;
        public static final int OPERATE_EXIST_STATUS = 0004;

        public static final String OPERATE_SUCCESS = "SUCCESS";
        public static final String OPERATE_FAILED= "FAILED";
        public static final String OPERATE_EXCEPTION= "EXCEPTION";
        public static final String OPERATE_EMPTY= "EMPTY";
        public static final String OPERATE_EXIST= "EXIST";
        public static final String OPERATE_SUCCESS_MESSAGE = "操作成功";
        public static final String OPERATE_FAILED_MESSAGE = "操作失败";
        public static final String OPERATE_EXCEPTION_MESSAGE = "操作异常";
        public static final String OPERATE_EMPTY_MESSAGE = "查询结果为空";
        public static final String OPERATE_EMPTY_MUST_MESSAGE = "必要参数不能为空";
        public static final String OPERATE_EXIST_MESSAGE = "用户已经存在";

    }
}
