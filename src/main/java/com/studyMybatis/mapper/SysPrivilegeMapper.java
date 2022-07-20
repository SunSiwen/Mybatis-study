package com.studyMybatis.mapper;

import com.studyMybatis.entity.SysPrivilege;
import com.studyMybatis.mapper.providers.PrivilegeProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:05
 */
public interface SysPrivilegeMapper {

    List<SysPrivilege> selectAll();

    @SelectProvider(type = PrivilegeProvider.class , method = "selectById")
    SysPrivilege selectById(Long id);
}
