
package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

/**
 * Access to the DB
 * 
 * @author Fernando
 *
 */
@Repository
public interface AirportMapper {

	/** Selects by iso country */
	@Select("SELECT * FROM airport WHERE iso_country = #{code}")
	List<Map<String, Object>> selectByCountryCode(String code);

	/** Select countries with more airports */
	@Select("SELECT TOP 10 iso_country, name, air_count FROM VIEW_COUNTRY_MAX INNER JOIN COUNTRY ON code=iso_country ORDER BY air_count DESC ")
	List<Map<String, Object>> selectTenBest();

	/** Select countries with less airports */
	@Select("SELECT TOP 10 iso_country, name, air_count FROM VIEW_COUNTRY_MAX INNER JOIN COUNTRY ON code=iso_country ORDER BY air_count ASC ")
	List<Map<String, Object>> selectTenWorst();

	/** Selects by iso country */
	@Select("SELECT id, ident, type, name, latitude_deg, longitude_deg FROM airport  WHERE iso_country = #{param1} LIMIT #{param2}, #{param3} ")
	@Results(value = { 
			@Result(column = "id", property="id"),
			@Result(column = "name", property="name"),
			@Result(column = "id", property="runways", many = @Many(select = "getRunways") ) })
	List<Map<String, Object>> selectByCountryCodeWithRunaways(String code, int offset, int limit);



	@Select("SELECT id, surface, length_ft, width_ft FROM runway WHERE airport_ref = #{airport_ref}")
	List<Map<String, Object>> getRunways(String airportRef);

	/**
	 * @param string
	 * @return
	 */
	@Select("SELECT count(id) FROM airport WHERE iso_country = #{code}")
	int countByCountryCode(String string);

}
