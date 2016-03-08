
package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Fernando
 *
 */
@Repository
public interface CountryMapper {

	/** 
	 * Selects by country code
	 * @param code
	 * @return
	 */
	@Select("SELECT * FROM country WHERE code = #{code}")
	Map<String, Object> selectByCode(String code);

	/**
	 * Selects by name (like name)
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM country WHERE name like #{name}")
	Map<String, Object> selectByName(String name);
	


}
