package com.laboratory.labport.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shipan on 2017/12/23.
 */
public class ResponseModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    private Integer status;				//接口返回状态(PayRouterConstant.ResponseStatus)
    //private String code;                //接口返回编码(PayRouterConstant.ResponseCode)
    private String message;             //接口返回信息
    private Object data = "";                //数据
    private Map<String,Object> pageMap = new HashMap<String,Object>();    //请求参数

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getPageMap() {
        return pageMap;
    }

    public void setPageMap(Map<String, Object> pageMap) {
        this.pageMap = pageMap;
    }
}
