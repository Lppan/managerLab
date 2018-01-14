package com.laboratory.laboratory.model;

import java.util.Date;

public class Laboratory {

    private Integer id;

    private Integer labId;              //实验室id

    private Integer projectId;          //项目id

    private String labName;             //实验室名称

    private String labFunction;         //实验室功能

    private String labFunctionM;        //实验室功能备

    private String labPerson;           //实验人

    private String status;              //实验室状态

    private String isDelete;            //是否删除

    private Date beginTime;             //实验开始时间

    private Date endTime;               //实验结束时间

    private String memo;                //备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabFunction() {
        return labFunction;
    }

    public void setLabFunction(String labFunction) {
        this.labFunction = labFunction;
    }

    public String getLabFunctionM() {
        return labFunctionM;
    }

    public void setLabFunctionM(String labFunctionM) {
        this.labFunctionM = labFunctionM;
    }

    public String getLabPerson() {
        return labPerson;
    }

    public void setLabPerson(String labPerson) {
        this.labPerson = labPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}