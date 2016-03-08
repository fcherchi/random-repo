
package com.lunatech.airports.services;

import java.util.List;
import java.util.Map;

/**
 * Service layer
 * @author Fernando
 */
public interface ReportService {
	
	/**
	 * Gets the ten countries with more airports
	 * @return
	 */
	List<Map<String, Object>> getTenCountriesWithMoreAirports();

	/**
	 * Gets the ten countries with less airports
	 * @return
	 */
	List<Map<String, Object>> getTenCountriesWithLessAirports();

	/**
	 * Gets the surfaces
	 * @return
	 */
	List<Map<String, Object>> getSurfaces();
}
