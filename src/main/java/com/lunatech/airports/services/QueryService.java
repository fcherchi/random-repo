
package com.lunatech.airports.services;

import java.util.List;
import java.util.Map;

/**
 * Service layer
 * @author Fernando
 */
public interface QueryService {
	
	/**
	 * Gets the list of airports by country code
	 * @param countryCode
	 * @return
	 */
	List<Map<String, Object>> getAirports(String countryCode, int offset, int limit);

	/**
	 * Gets the first page controlling the limit
	 * @param code
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	Page getNextPage(String code, int offset, int pageSize);

	
	/**
	 * Gets the previous page controlling the limits
	 * @param code
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	Page getPreviousPage(String code, int offset, int pageSize);
}
