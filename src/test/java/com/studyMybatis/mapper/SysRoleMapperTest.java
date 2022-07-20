package com.studyMybatis.mapper;

import com.studyMybatis.entity.SysRole;
import com.studyMybatis.entity.SysUser;
import com.studyMybatis.enums.Enabled;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/19/ 13:23
 */
public class SysRoleMapperTest {


    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper userMapper = sqlSession.getMapper(SysRoleMapper.class);
            //调用 selectRolesByUseridAndRoleEnabled 方法查询用户的角色
            List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            //结果不为空
            Assert.assertNotNull(userList);
            //角色数量大于
            Assert.assertTrue(userList.size() > 0);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectRolesByUserAndRole() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper userMapper = sqlSession.getMapper(SysRoleMapper.class);
            //调用 selectRolesByUseridAndRoleEnabled 方法查询用户的角色
            SysRole role = new SysRole();
            SysUser user = new SysUser();
            user.setId(1L);
            List<SysRole> userList = userMapper.selectRolesByUserAndRole(user, role);
            //结果不为空
            Assert.assertNotNull(userList);
            //角色数量大于
            Assert.assertTrue(userList.size() > 0);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper userMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole userList = userMapper.selectById(1L);
            //结果不为空
            Assert.assertNotNull(userList);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectById2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper userMapper = sqlSession.getMapper(SysRoleMapper.class);
//            SysRole userList = userMapper.selectById2(1L);
            //结果不为空
//            Assert.assertNotNull(userList);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
