
package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Access to the DB
 * 
 * @author Fernando
 *
 */
@Repository
public interface AirportMapper {

	
	/** Select countries with more airports */
	@Select("SELECT TOP 10 ISO_COUNTRY, NAME, AIR_COUNT FROM VIEW_COUNTRY_MAX INNER JOIN COUNTRY ON CODE=ISO_COUNTRY ORDER BY AIR_COUNT DESC ")
	List<Map<String, Object>> selectTenBest();

	/** Select countries with less airports */
	@Select("SELECT TOP 10 ISO_COUNTRY, NAME, AIR_COUNT FROM VIEW_COUNTRY_MAX INNER JOIN COUNTRY ON CODE=ISO_COUNTRY ORDER BY AIR_COUNT ASC ")
	List<Map<String, Object>> selectTenWorst();

	/** Selects by iso country 
	 * Part One of the One To Many */
	@Select("SELECT ID, IDENT, TYPE, NAME, LATITUDE_DEG, LONGITUDE_DEG FROM AIRPORT  WHERE ISO_COUNTRY = #{param1} LIMIT #{param2}, #{param3} ")
	@Results(value = { 
			@Result(column = "ID", property="ID"),
			@Result(column = "NAME", property="NAME"),
			@Result(column = "ID", property="RUNWAYS", many = @Many(select = "getRunways") ) })
	List<Map<String, Object>> selectByCountryCodeWithRunaways(String code, int offset, int limit);


	/**
	 * Gets the runways.
	 * Part Many of the One to Many
	 * @param airportRef
	 * @return
	 */
	@Select("SELECT ID, SURFACE, LENGTH_FT, WIDTH_FT FROM RUNWAY WHERE AIRPORT_REF = #{airport_ref}")
	List<Map<String, Object>> getRunways(String airportRef);

	/**
	 * @param string
	 * @return
	 */
	@Select("SELECT COUNT(ID) FROM AIRPORT WHERE ISO_COUNTRY = #{code}")
	int countByCountryCode(String string);

}
