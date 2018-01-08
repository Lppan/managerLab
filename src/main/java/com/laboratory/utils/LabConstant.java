package com.laboratory.utils;/**
 * Created by Administrator on 2017/12/25.
 */

/**
 * lab constant
 * Created by Lpan on 2017/12/25.
 */
public class LabConstant {
    public static class LabLogin{

        //系统默认密码
        public static final String DEFAULT_PASSWORD = "123456";

        public static final int LOGIN_SUCCESS_CODE = 0000;
        public static final int LOGIN_FAILED_CODE = 0001;
        public static final int LOGIN_ERROR_CODE = 0002;
        public static final int LOGIN_EMPTY_CODE = 0003;
        public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";
        public static final String LOGIN_SUCCESS_LOGOUT_MESSAGE = "登出成功";
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

        public static final String OPERATE_SUCCESS_MESSAGE = "操作成功";
        public static final String OPERATE_FAILED_MESSAGE = "操作失败";
        public static final String OPERATE_EXCEPTION_MESSAGE = "操作异常";
        public static final String OPERATE_EMPTY_MESSAGE = "查询结果为空";
        public static final String OPERATE_EMPTY_MUST_MESSAGE = "必要参数不能为空";
        public static final String OPERATE_EXIST_MESSAGE = "用户已经存在";

    }
    public class filePath{

        //油耗实验数据路径
        public static final String FILE_DIRECTORY_PATH_WIN = "D:\\lab";
        public static final String FILE_DIRECTORY_PATH_LINUX = "/lab";
    }

    public static class fileType{

        public static final  int  INSTRUCT_BOOK = 01;
        public static final String INSTRUCT_BOOK_NAME ="作业指导书";
        public static final String INSTRUCT_BOOK_CODE ="GUIDBOOK";

        public static final  int PROPERTY_LIST = 02;
        public static final String PROPERTY_LIST_NAME="性能特性清单";
        public static final String IPROPERTY_LIST_CODE ="PROLIST";

        public static final  int MACHINE_TYPE = 03;
        public static final String MACHINE_TYPE_NAME ="机型信息表";
        public static final String MACHINE_TYPE_CODE ="MACHINETYPE";

        public static final String getName(int type ){
            String  resultStr = "";
            switch (type){
                case  INSTRUCT_BOOK:
                    resultStr = INSTRUCT_BOOK_NAME;
                    break;

                case  PROPERTY_LIST:
                    resultStr =  PROPERTY_LIST_NAME;
                    break;

                case MACHINE_TYPE:
                    resultStr =  MACHINE_TYPE_NAME;
                    break;
            }
            return resultStr;
        }

    }

}
