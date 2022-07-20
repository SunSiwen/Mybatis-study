package com.studyMybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:04
 */
public class CreateInfo implements Serializable {
    private static final long serialVersionUID = 8275281589408844992L;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}