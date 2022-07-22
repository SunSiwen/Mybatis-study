package com.studyMybatis.mapper;

import com.studyMybatis.entity.SysRole;
import com.studyMybatis.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 12:59
 */
public interface SysUserMapper {
    List<SysUser> selectAll();

    SysUser selectById(Long id);

    SysRole selectSysRoleBySysUserId(Long userId);

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(SysUser sysUser);

    List<SysUser> selectByUser(SysUser sysUser);


    /**
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);


    SysUser selectByIdOrUserName(SysUser sysUser);

    List<SysUser> selectByIdList(List<Long> idList);

    int insertList(List<SysUser> userList);

    int updateByMap(Map<String , Object> map) ;
}