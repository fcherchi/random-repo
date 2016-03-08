
package com.lunatech.airports.persistence;

import java.util.Map;

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
	@Select("SELECT * FROM COUNTRY WHERE CODE = #{code}")
	Map<String, Object> selectByCode(String code);

	/**
	 * Selects by name (like name)
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM COUNTRY WHERE NAME LIKE #{name}")
	Map<String, Object> selectByName(String name);
	


}
