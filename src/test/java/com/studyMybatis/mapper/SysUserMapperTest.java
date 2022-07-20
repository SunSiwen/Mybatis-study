package com.studyMybatis.mapper;

import com.studyMybatis.entity.Country;
import com.studyMybatis.entity.SysUser;
import com.studyMybatis.entity.SysUserRole;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 12:31
 */
public class SysUserMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }

    @Test
    public void testSelectAll2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserRoleMapper mapper = sqlSession.getMapper(SysUserRoleMapper.class);
            List<SysUserRole> sysUserRoleMapperList = mapper.selectAll();
            printSysUserRoleList(sysUserRoleMapperList);
        }
        //不要忘记关闭 sqlSession
    }

    private void printSysUserRoleList(List<SysUserRole> sysUserRoleMapperList) {
        for (SysUserRole sysUserRole : sysUserRoleMapperList) {
            System.out.printf("%-4d-%4s\n",
                    sysUserRole.getUserId(), sysUserRole.getRoleId());
        }
    }

    @Test
    public void testSelectAll3() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUserMapperList = mapper.selectAll();
            printSysUserList(sysUserMapperList);
        }
        //不要忘记关闭 sqlSession
    }

    private void printSysUserList(List<SysUser> sysUserRoleMapperList) {
        for (SysUser sysUser : sysUserRoleMapperList) {
            System.out.printf("%-4d-%4s\n",
                    sysUser.getId(), sysUser.getUserName());
        }
    }

    @Test
    public void testSelectSysUserById() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = mapper.selectById(1L);
            //            printSysUser(sysUser);
        }
        //不要忘记关闭 sqlSession
    }

    @Test
    public void testInsertSysUser() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("siwen");
            user.setUserPassword("sun");
            user.setUserEmail("ssw@mybatis.com");
            user.setUserInfo("test_info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = mapper.insert(user);
            System.out.println(user.getId());
            sqlSession.commit();
        }
        //不要忘记关闭 sqlSession
    }


    @Test
    public void testUpdateById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //从数据库查询 user 对象
            SysUser user = userMapper.selectById(1L);

            Assert.assertEquals("admin", user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            //修改邮箱
            user.setUserEmail("test @mybatis.tk");
            //史新数据，特别 注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateById(user);
            //只史新 条数据
            Assert.assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字是 adrnin test
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            // 为了不影响其他测试，这里选择回滚
            // 由于默认的 sqlSessionFactory openSession （）是不自动提交的，
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();

        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser user1 = userMapper.selectById(1L);
            //现在还能查询出 user 对象
            Assert.assertNotNull(user1);
            //调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(user1));
            //再次查询，这时应该没有佳，为 null
            Assert.assertNull(userMapper.selectById(1L));
            //使用 SysUser 参数再进行一次测试，根据 id = 1001 查询
            SysUser user2 = userMapper.selectById(1001L);
            //现在还能查询出 user 对象
            Assert.assertNotNull(user2);
            //调用方法删除，注意这里使用参数为 user2
            Assert.assertEquals(1, userMapper.deleteById(user2));
            //再次查询 这时应该没有值，为 null
            Assert.assertNull(userMapper.selectById(1001L));
            //使用 SysUser 参数再进行一次测试
        } finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的 sqlSessionFactory.openSes sion （）是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}