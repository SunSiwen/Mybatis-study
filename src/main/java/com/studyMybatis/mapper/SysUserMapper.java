package com.studyMybatis.mapper;

import com.studyMybatis.entity.SysRole;
import com.studyMybatis.entity.SysUser;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 12:59
 */
public interface SysUserMapper {
    List<SysUser> selectAll();

    SysUser selectById(Long id);

    SysRole selectSysRoleBySysUserId(Long userId);

    int insert(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(SysUser sysUser);
}