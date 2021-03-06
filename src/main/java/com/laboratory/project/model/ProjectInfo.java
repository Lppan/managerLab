package com.laboratory.project.model;

import com.laboratory.labport.model.BaseModel;

import java.util.Date;

public class ProjectInfo extends BaseModel{

    private static final long serialVersionUID = 1903282647294811152L;

    private Integer id;

    private String projectNo;                   //项目编号

    private String projectName;                 //项目名

    private String parentProjectName;           //所属项目

    private String projectPrincipal;            //项目负责人

    private String machineType;                 //机型

    private String machineNo;                   //机号

    private Float powerRate;                    //标定点功率

    private Integer rotateSpeed;                //标定点功率转速

    private Integer oilConsumeSign;             //标定点油耗

    private Integer torsionSpace;               //最大扭矩

    private Integer oilConsumeLow;              //最低油耗

    private Integer speedPointTotal;            //怠速点

    private String mapPicture;                  //map图

    private String standard;                    //排放标准

    private String egr;                         //EGR

    private String ecu;                         //ECU

    private String fuelSystem;                  //燃油系统

    private String intake;                      //进气方式

    private String midleCooling;                //中冷方式

    private Integer valueNo;                    //气门数

    private String speedPointTotalLimit;        //高怠速

    private String speedPointTotalHight;        //低怠速

    private String stoke;                       //缸径冲程

    private String importanceLevel;             //项目重要度

    private Date planBeginTime;                 //计划开始时间

    private Date planEndTime;                   //计划结束时间

    private String projectCycle;                //项目周期

    private Integer projectContent;             //实验内容

    private String operationInstruction;

    private String propertyCharacterList;

    private String machineNoList;

    private Integer dais;

    private String status;

    private String isStoppage;

    private Date createTime;

    private Date updateTime;

    private String isDelete;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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

    public String getMapPicture() {
        return mapPicture;
    }

    public void setMapPicture(String mapPicture) {
        this.mapPicture = mapPicture;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getEgr() {
        return egr;
    }

    public String getEcu() {
        return ecu;
    }

    public void setEcu(String ecu) {
        this.ecu = ecu;
    }

    public void setEgr(String egr) {
        this.egr = egr;
    }

    public String getFuelSystem() {
        return fuelSystem;
    }

    public void setFuelSystem(String fuelSystem) {
        this.fuelSystem = fuelSystem;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getMidleCooling() {
        return midleCooling;
    }

    public void setMidleCooling(String midleCooling) {
        this.midleCooling = midleCooling;
    }

    public Integer getValueNo() {
        return valueNo;
    }

    public void setValueNo(Integer valueNo) {
        this.valueNo = valueNo;
    }

    public String getSpeedPointTotalLimit() {
        return speedPointTotalLimit;
    }

    public void setSpeedPointTotalLimit(String speedPointTotalLimit) {
        this.speedPointTotalLimit = speedPointTotalLimit;
    }

    public String getSpeedPointTotalHight() {
        return speedPointTotalHight;
    }

    public void setSpeedPointTotalHight(String speedPointTotalHight) {
        this.speedPointTotalHight = speedPointTotalHight;
    }

    public String getStoke() {
        return stoke;
    }

    public void setStoke(String stoke) {
        this.stoke = stoke;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "id=" + id +
                ", projectNo='" + projectNo + '\'' +
                ", projectName='" + projectName + '\'' +
                ", parentProjectName='" + parentProjectName + '\'' +
                ", projectPrincipal='" + projectPrincipal + '\'' +
                ", machineType='" + machineType + '\'' +
                ", machineNo='" + machineNo + '\'' +
                ", powerRate=" + powerRate +
                ", rotateSpeed=" + rotateSpeed +
                ", oilConsumeSign=" + oilConsumeSign +
                ", torsionSpace=" + torsionSpace +
                ", oilConsumeLow=" + oilConsumeLow +
                ", speedPointTotal=" + speedPointTotal +
                ", mapPicture='" + mapPicture + '\'' +
                ", standard='" + standard + '\'' +
                ", egr='" + egr + '\'' +
                ", fuelSystem='" + fuelSystem + '\'' +
                ", intake='" + intake + '\'' +
                ", midleCooling='" + midleCooling + '\'' +
                ", valueNo=" + valueNo +
                ", speedPointTotalLimit='" + speedPointTotalLimit + '\'' +
                ", speedPointTotalHight='" + speedPointTotalHight + '\'' +
                ", stoke='" + stoke + '\'' +
                ", importanceLevel='" + importanceLevel + '\'' +
                ", planBeginTime=" + planBeginTime +
                ", planEndTime=" + planEndTime +
                ", projectCycle='" + projectCycle + '\'' +
                ", projectContent=" + projectContent +
                ", operationInstruction='" + operationInstruction + '\'' +
                ", propertyCharacterList='" + propertyCharacterList + '\'' +
                ", machineNoList='" + machineNoList + '\'' +
                ", dais=" + dais +
                ", status='" + status + '\'' +
                ", isStoppage='" + isStoppage + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete='" + isDelete + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}