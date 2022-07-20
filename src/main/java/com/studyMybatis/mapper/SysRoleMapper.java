package com.studyMybatis.mapper;


import com.studyMybatis.entity.SysRole;
import com.studyMybatis.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 13:01
 */
public interface SysRoleMapper {


    @Select({"select id, role_name roleName , enabled enabled, create_by createBy, create_time createTime from sys_role where id = #{id}"})
    SysRole selectById(Long id);

    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select({"select id, role_name, enabled, create_by, create_time from sys_role where id = #{id}"})
    SysRole selectById2(Long id);


    List<SysRole> selectAll();

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_ time) values(#{roleName}, #{enabled}, #{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SysRole sysRole);

    @Update({" update sys_role set role_name = #{roleName}, enabled = #{enabled}, create_by = #{createBy}, create time = #{createTime, jdbcType=TIMESTAMP} where id = #{id }"})
    int updateById(SysRole sysRole);

    @Delete({"delete from sys role where id = #{id }"})
    int deleteById(Long id);
}

