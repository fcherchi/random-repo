
package com.lunatech.airports.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunatech.airports.persistence.AirportMapper;
import com.lunatech.airports.persistence.RunwayMapper;
import com.lunatech.airports.services.ReportService;

/**
 * Service layer. In normal RDBM application we would configure the transactions
 * at this level
 * 
 * @author Fernando
 *
 */
@Service
public class ReportServiceImpl implements ReportService {

	

	/** Access to the airport repository */
	@Autowired
	private AirportMapper airportMapper;
	
	/** Access to the runway repository */
	@Autowired
	private RunwayMapper runwayMapper;

	/**
	 * @see com.lunatech.airports.services.QueryService#getAirports(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getTenCountriesWithMoreAirports() {

		List<Map<String, Object>> airports = this.airportMapper.selectTenBest();
		return airports;
	}

	/**
	 * @see com.lunatech.airports.services.ReportService#getTenCountriesWithLessAirports()
	 */
	@Override
	public List<Map<String, Object>> getTenCountriesWithLessAirports() {
		List<Map<String, Object>> airports = this.airportMapper.selectTenWorst();
		return airports;
	}

	/**
	 * @see com.lunatech.airports.services.ReportService#getSurfaces()
	 */
	@Override
	public List<Map<String, Object>> getSurfaces() {
		List<Map<String, Object>> surfaces = this.runwayMapper.getRunwaysPerCountry();
		return surfaces;
		
	}
}
