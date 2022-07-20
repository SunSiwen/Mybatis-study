package com.studyMybatis.entity;

import java.io.Serializable;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:05
 */
public class SysPrivilege implements Serializable {
    private static final long serialVersionUID = 6315662516417216377L;
    /**
     * 权限ID
     */
    private Long id;
    /**
     * 权限名称
     */
    private String privilegeName;
    /**
     * 权限URL
     */
    private String privilegeUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }

}
