package com.laboratory.stoppage.model;

import com.laboratory.labport.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 故障类型
 * Created by shipan on 2017/12/23.
 */
public class StopPage extends BaseModel{

    private static final long serialVersionUID = 692276059290174785L;

    private Integer id;

    private Integer bugId;

    private Integer bugParentId;

    private String bugType;

    private String bugDetail;

    private String bugInfo;

    private String bugSlove;

    private Integer projectId;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Integer getBugParentId() {
        return bugParentId;
    }

    public void setBugParentId(Integer bugParentId) {
        this.bugParentId = bugParentId;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getBugDetail() {
        return bugDetail;
    }

    public void setBugDetail(String bugDetail) {
        this.bugDetail = bugDetail;
    }

    public String getBugInfo() {
        return bugInfo;
    }

    public void setBugInfo(String bugInfo) {
        this.bugInfo = bugInfo;
    }

    public String getBugSlove() {
        return bugSlove;
    }

    public void setBugSlove(String bugSlove) {
        this.bugSlove = bugSlove;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
