package com.laboratory.system.resource.model;

import java.util.Date;

public class Resources {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.uri
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String uri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.type
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.level
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Byte level;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.path
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.parent_id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.order
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String order;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.class_name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String className;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.permission_code
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String permissionCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.is_used
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String isUsed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.create_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.update_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lab_resources.comment
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    private String comment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.id
     *
     * @return the value of lab_resources.id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.id
     *
     * @param id the value for lab_resources.id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.name
     *
     * @return the value of lab_resources.name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.name
     *
     * @param name the value for lab_resources.name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.uri
     *
     * @return the value of lab_resources.uri
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.uri
     *
     * @param uri the value for lab_resources.uri
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.type
     *
     * @return the value of lab_resources.type
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.type
     *
     * @param type the value for lab_resources.type
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.level
     *
     * @return the value of lab_resources.level
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.level
     *
     * @param level the value for lab_resources.level
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.path
     *
     * @return the value of lab_resources.path
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.path
     *
     * @param path the value for lab_resources.path
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.parent_id
     *
     * @return the value of lab_resources.parent_id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.parent_id
     *
     * @param parentId the value for lab_resources.parent_id
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.order
     *
     * @return the value of lab_resources.order
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.order
     *
     * @param order the value for lab_resources.order
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setOrder(String order) {
        this.order = order == null ? null : order.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.class_name
     *
     * @return the value of lab_resources.class_name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getClassName() {
        return className;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.class_name
     *
     * @param className the value for lab_resources.class_name
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.permission_code
     *
     * @return the value of lab_resources.permission_code
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.permission_code
     *
     * @param permissionCode the value for lab_resources.permission_code
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.is_used
     *
     * @return the value of lab_resources.is_used
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getIsUsed() {
        return isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.is_used
     *
     * @param isUsed the value for lab_resources.is_used
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed == null ? null : isUsed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.create_time
     *
     * @return the value of lab_resources.create_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.create_time
     *
     * @param createTime the value for lab_resources.create_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.update_time
     *
     * @return the value of lab_resources.update_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.update_time
     *
     * @param updateTime the value for lab_resources.update_time
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lab_resources.comment
     *
     * @return the value of lab_resources.comment
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lab_resources.comment
     *
     * @param comment the value for lab_resources.comment
     *
     * @mbg.generated Tue Dec 12 16:53:29 CST 2017
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}