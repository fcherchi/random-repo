
package com.lunatech.airports.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lunatech.airports.persistence.AirportMapper;
import com.lunatech.airports.persistence.CountryMapper;
import com.lunatech.airports.persistence.columns.CountryColumn;
import com.lunatech.airports.services.Page;
import com.lunatech.airports.services.QueryService;
import com.lunatech.airports.services.ServiceException;

/**
 * Service layer.
 * 
 * @author Fernando
 *
 */
@Service
@Transactional
public class QueryServiceImpl implements QueryService {

	/** Access to the country repository */
	@Autowired
	private CountryMapper countryMapper;

	/** Access to the airport repository */
	@Autowired
	private AirportMapper airportMapper;

	

	/**
	 * @see com.lunatech.airports.services.QueryService#getAirports(java.lang.String, int, int)
	 */
	@Override
	public List<Map<String, Object>> getAirports(String codeOrName, int offset, int limit) {
		
		if (codeOrName.length() < 2) {
			throw new RuntimeException ("Minimum entry should contain 2 characters");
		}
		Map<String, Object> country = getCountryCode(codeOrName);
		List<Map<String, Object>> airports = 
				this.airportMapper.selectByCountryCodeWithRunaways(country.get(CountryColumn.CODE).toString(), offset, limit);
		return airports;
	}

	/**
	 * @see com.lunatech.airports.services.QueryService#countAirports(java.lang.String)
	 */
	private int countAirports(String codeOrName) {
		Map<String, Object> country = getCountryCode(codeOrName);
		int res = this.airportMapper.countByCountryCode(country.get(CountryColumn.CODE).toString());
		return res;
	}
	
	/**
	 * @param codeOrName
	 * @return
	 */
	private Map<String, Object> getCountryCode(String codeOrName) {
		
		Map<String, Object> country = null;
		if (StringUtils.hasText(codeOrName)) {
			// first try country code
			if (codeOrName.length() == 2) {
				country = this.countryMapper.selectByCode(codeOrName);
			}
			// none found by country code, try by name
			if (country == null) {
				country = this.countryMapper.selectByName(codeOrName + "%");
			}
		}
		if (country == null || country.size() == 0) {
			throw new ServiceException("No country found with the given criteria");
		}
		return country;
	}

	/**
	 * @see com.lunatech.airports.services.QueryService#getNextPage(java.lang.String, int, int)
	 */
	@Override
	public Page getNextPage(String code, int offset, int pageSize) {
		
		//gets the airports with that criteria
		int totalAirports = countAirports(code);
		
		int nextPageStart = offset + pageSize;
		if (nextPageStart > totalAirports) {
			nextPageStart = offset;
		}
		
		int nextPageEnd = nextPageStart + pageSize;
		//control limits
		if (nextPageEnd > totalAirports) {
			nextPageEnd = totalAirports;
		}
		
		Page page = new Page();
		page.setOffset(nextPageStart);
		page.setLimit(nextPageEnd);
		
		return page;
	}

	/**
	 * @see com.lunatech.airports.services.QueryService#getPreviousPage(java.lang.String, int, int)
	 */
	@Override
	public Page getPreviousPage(String code, int offset, int pageSize) {
		
		int previousPageStart = offset - pageSize;
		if (previousPageStart < 0) {
			previousPageStart = 0;
		}
		int previousPageEnd = previousPageStart + pageSize;
		
		Page page = new Page();
		page.setOffset(previousPageStart);
		page.setLimit(previousPageEnd);
		
		return page;
	}
}
