package com.studyMybatis.mapper;

import com.studyMybatis.entity.SysUserRole;

import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:30
 */
public interface SysUserRoleMapper {
    List<SysUserRole> selectAll();
}