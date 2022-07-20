package com.studyMybatis.mapper.providers;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author Siwen Sun
 * @date 2022/07/19/ 23:35
 */
public class PrivilegeProvider {
    public String selectById(final Long id) {
//        return " select id, privilege_name, privilege_url " +
//                " from sys_privilege where id = #{id}";

        return new SQL() {
            {
                SELECT("id, privilege name, privilege url");
                FROM("sys privilege");
                WHERE(" id= #{id }");
            }
        }.toString();
    }
}
