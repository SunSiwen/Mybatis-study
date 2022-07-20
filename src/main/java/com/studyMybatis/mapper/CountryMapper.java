package com.studyMybatis.mapper;

import com.studyMybatis.entity.Country;

import java.util.List;

/**
 * @author Siwen Sun
 * @date 2022/07/18/ 12:21
 */
public interface CountryMapper {

    List<Country> selectAll();
}
