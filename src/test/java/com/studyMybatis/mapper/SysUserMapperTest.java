package com.studyMybatis.mapper;

import com.studyMybatis.entity.Country;
import com.studyMybatis.entity.SysUser;
import com.studyMybatis.entity.SysUserRole;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

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
            // 由于默认的 sqlSessionFactory openSession ()是不自动提交的，
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
            //由于默认的 sqlSessionFactory.openSes sion ()是不自动提交的，
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            //只查询 用户邮箱时
            query = new SysUser();
            query.setUserEmail("test @mybatis.tk");
            userList = userMapper.selectByUser(query);
//            Assert.assertTrue(userList.size() > 0);
            // 当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            // 由于没有同时符合这两个条件的用户 ，因此查询结采数为
            Assert.assertTrue(userList.size() == 0);
        } finally {
            //不要忘记关闭 sqlSession

            sqlSession.close();
        }
    }


    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //创建一个新的 user 对象
            SysUser user = new SysUser();
            //史新 id = 的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            // 更新邮箱，特别注意，这里的返回值 result 执行的是 SQL 影响的行数
            int result = userMapper.updateByIdSelective(user);
            //只更新 条数据
            Assert.assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字保持不变，但是邮箱变成了新的
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            //为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();

        }
    }


    @Test
    public void testInsert2SysUser() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo(" test_info");
            user.setCreateTime(new Date());
            //插入数据库
            userMapper.insert2(user);
            //获取插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        }
        //不要忘记关闭 sqlSession
    }

    @Test
    public void testSelectByIdOrUserName() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }
        //不要忘记关闭 sqlSession
    }


    @Test
    public void testSelectByIdList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2, userList.size());
        }
        //不要忘记关闭 sqlSession
    }


    @Test
    public void testInsertUserList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> userList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }

            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);
        }
        //不要忘记关闭 sqlSession
    }

    @Test
    public void testUpdateByMap() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("id", 1L);

            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "12345678");

            userMapper.updateByMap(map);

            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        }
        //不要忘记关闭 sqlSession
    }

}