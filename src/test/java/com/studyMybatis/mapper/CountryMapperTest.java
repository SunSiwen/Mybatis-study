package com.studyMybatis.mapper;

import com.studyMybatis.entity.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/19/ 13:20
 */
public class CountryMapperTest {

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
    public void testSelectAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countryList = mapper.selectAll();
            Assert.assertNotNull(countryList);
            printCountryList(countryList);
        }
        //不要忘记关闭 sqlSession
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d-%4s-%4s\n",
                    country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }


}
