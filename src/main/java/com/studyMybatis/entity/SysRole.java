package com.studyMybatis.entity;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.studyMybatis.enums.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = 6320941908222932112L;

    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志
     */
    private Enabled enabled;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户信息
     */
    private SysUser user;
    /**
     * 创建信息
     */
    private CreateInfo createInfo;

    /**
     * 角色包含的权限列表
     */
    List<SysPrivilege> privilegeList;


    public void setEnabled(Integer num) {
        if (num == 1) {
            this.enabled = Enabled.enabled;
        } else {
            this.enabled = Enabled.disabled;
        }
    }
}
