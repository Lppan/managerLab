package com.laboratory.project.model;

import com.laboratory.labport.model.BaseModel;

import java.util.Date;

public class ProjectInfo extends BaseModel{

    private static final long serialVersionUID = 1903282647294811152L;

    private Integer id;

    private String projectName;                 //项目名

    private String parentProjectName;           //所属项目

    private String projectPrincipal;            //项目负责人

    private String machineType;                 //机型

    private String machineNo;                   //机号

    private Float powerRate;                    //标定点功率

    private Integer rotateSpeed;                //标定点功率转速

    private Integer oilConsumeSign;             //标定点油耗

    private Integer torsionSpace;               //最大扭矩

    private Integer oilConsumeLow;              //

    private Integer speedPointTotal;            //怠速点

    private String importanceLevel;             //项目重要度

    private Date planBeginTime;                 //计划开始时间

    private Date planEndTime;                   //计划结束时间

    private String projectCycle;                //项目周期

    private Integer projectContent;             //

    private String operationInstruction;

    private String propertyCharacterList;

    private String machineNoList;

    private Integer dais;

    private String status;

    private String isStoppage;

    private Date createTime;

    private Date updateTime;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getParentProjectName() {
        return parentProjectName;
    }

    public void setParentProjectName(String parentProjectName) {
        this.parentProjectName = parentProjectName == null ? null : parentProjectName.trim();
    }

    public String getProjectPrincipal() {
        return projectPrincipal;
    }

    public void setProjectPrincipal(String projectPrincipal) {
        this.projectPrincipal = projectPrincipal == null ? null : projectPrincipal.trim();
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType == null ? null : machineType.trim();
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo == null ? null : machineNo.trim();
    }

    public Float getPowerRate() {
        return powerRate;
    }

    public void setPowerRate(Float powerRate) {
        this.powerRate = powerRate;
    }

    public Integer getRotateSpeed() {
        return rotateSpeed;
    }

    public void setRotateSpeed(Integer rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    public Integer getOilConsumeSign() {
        return oilConsumeSign;
    }

    public void setOilConsumeSign(Integer oilConsumeSign) {
        this.oilConsumeSign = oilConsumeSign;
    }

    public Integer getTorsionSpace() {
        return torsionSpace;
    }

    public void setTorsionSpace(Integer torsionSpace) {
        this.torsionSpace = torsionSpace;
    }

    public Integer getOilConsumeLow() {
        return oilConsumeLow;
    }

    public void setOilConsumeLow(Integer oilConsumeLow) {
        this.oilConsumeLow = oilConsumeLow;
    }

    public Integer getSpeedPointTotal() {
        return speedPointTotal;
    }

    public void setSpeedPointTotal(Integer speedPointTotal) {
        this.speedPointTotal = speedPointTotal;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel == null ? null : importanceLevel.trim();
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getProjectCycle() {
        return projectCycle;
    }

    public void setProjectCycle(String projectCycle) {
        this.projectCycle = projectCycle == null ? null : projectCycle.trim();
    }

    public Integer getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(Integer projectContent) {
        this.projectContent = projectContent;
    }

    public String getOperationInstruction() {
        return operationInstruction;
    }

    public void setOperationInstruction(String operationInstruction) {
        this.operationInstruction = operationInstruction == null ? null : operationInstruction.trim();
    }

    public String getPropertyCharacterList() {
        return propertyCharacterList;
    }

    public void setPropertyCharacterList(String propertyCharacterList) {
        this.propertyCharacterList = propertyCharacterList == null ? null : propertyCharacterList.trim();
    }

    public String getMachineNoList() {
        return machineNoList;
    }

    public void setMachineNoList(String machineNoList) {
        this.machineNoList = machineNoList == null ? null : machineNoList.trim();
    }

    public Integer getDais() {
        return dais;
    }

    public void setDais(Integer dais) {
        this.dais = dais;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsStoppage() {
        return isStoppage;
    }

    public void setIsStoppage(String isStoppage) {
        this.isStoppage = isStoppage == null ? null : isStoppage.trim();
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}