package com.laboratory.laboratory.model;

import java.util.Date;

public class Laboratory {
    private Integer id;

    private String labNo;

    private String status;

    private String labTask;

    private String parentProject;

    private String machineType;

    private String labPrincipal;

    private Date beginTime;

    private Date endTime;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabNo() {
        return labNo;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo == null ? null : labNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLabTask() {
        return labTask;
    }

    public void setLabTask(String labTask) {
        this.labTask = labTask == null ? null : labTask.trim();
    }

    public String getParentProject() {
        return parentProject;
    }

    public void setParentProject(String parentProject) {
        this.parentProject = parentProject == null ? null : parentProject.trim();
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType == null ? null : machineType.trim();
    }

    public String getLabPrincipal() {
        return labPrincipal;
    }

    public void setLabPrincipal(String labPrincipal) {
        this.labPrincipal = labPrincipal == null ? null : labPrincipal.trim();
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
        this.memo = memo == null ? null : memo.trim();
    }
}