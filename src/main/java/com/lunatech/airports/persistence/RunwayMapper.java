package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Fernando
 *
 */
@Repository
public interface RunwayMapper {
	
	/**
	 * Gets the surfaces per country (one)
	 * @return
	 */
	@Select("SELECT name from VIEW_SURFACE GROUP BY name order by name")
	@Results(value = { 
			@Result(column = "name", property="name"),
			@Result(column = "name", property="list", many = @Many(select = "getSurfaces") ) })
	List<Map<String, Object>> getRunwaysPerCountry();
	
	/**
	 * Gets surfaces by name (many)
	 * @param name
	 * @return
	 */
	@Select("SELECT surface from VIEW_SURFACE where name=#{name}")
	List<String> getSurfaces(String name);

}
