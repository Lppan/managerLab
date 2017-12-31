package com.laboratory.stoppage.model;

import java.util.Date;

public class DataStopPage {
    private Integer id;

    private String bugName;

    private String bugType;

    private String bugCode;

    private Integer parentId;

    private Date createTime;

    private String status;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugName() {
        return bugName;
    }

    public void setBugName(String bugName) {
        this.bugName = bugName == null ? null : bugName.trim();
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType == null ? null : bugType.trim();
    }

    public String getBugCode() {
        return bugCode;
    }

    public void setBugCode(String bugCode) {
        this.bugCode = bugCode == null ? null : bugCode.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}