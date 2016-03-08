package com.lunatech.airports.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lunatech.airports.persistence.AirportMapper;
import com.lunatech.airports.persistence.CountryMapper;
import com.lunatech.airports.persistence.columns.CountryColumn;
import com.lunatech.airports.services.Page;
import com.lunatech.airports.services.QueryService;
import com.lunatech.airports.services.ServiceException;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceImplTest {

	@Mock
	private CountryMapper countryMapper;
	
	@Mock
	private AirportMapper airportMapper;

	@InjectMocks
	private QueryService queryService = new QueryServiceImpl();

	private List<Map<String, Object>> mockAirports;

	private Map<String, Object> mockCountry;

	/**
	 * Prepares the behavior of the mocks
	 */
	@Before
	public void setUp() {
		
		this.mockAirports = getMockAirports();
		this.mockCountry = getMockCountry();
		//inner call to the airports
		Mockito.when(this.airportMapper.selectByCountryCodeWithRunaways(
				Mockito.eq("NL"), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(this.mockAirports);
		
		//first it will call to verify/get the country 
		Mockito.when(this.countryMapper.selectByCode("NL")).thenReturn(this.mockCountry);
		Mockito.when(this.countryMapper.selectByName("Neth%")).thenReturn(this.mockCountry);
		//XX no success
		Mockito.when(this.countryMapper.selectByCode("XX")).thenReturn(null);
	}

	
	@Test
	public void testByName() throws Exception {
		
		List<Map<String, Object>> airports = this.queryService.getAirports("Neth", 1, 1);
		//check is calling country mapper
		Mockito.verify(this.countryMapper).selectByName("Neth%");
		
		Assert.assertNotNull(airports);
		Assert.assertArrayEquals(mockAirports.toArray(), airports.toArray());
	}
	
	
	@Test
	public void testByCountryCode() throws Exception {
		
		List<Map<String, Object>> airports = this.queryService.getAirports("NL", 1, 1);
		//check is calling country mapper
		Mockito.verify(this.countryMapper).selectByCode("NL");
		
		Assert.assertNotNull(airports);
		Assert.assertArrayEquals(mockAirports.toArray(), airports.toArray());
	}
	
	@Test(expected=ServiceException.class)
	public void testWhenNoCountryFoundByCode() throws Exception {
		
		
		List<Map<String, Object>> airports = this.queryService.getAirports("XX", 1, 1);
		
		//first it is called by code, nothing found
		Mockito.verify(this.countryMapper).selectByCode("XX");
		
		//then it should call by name, nothing found, exception
		Mockito.verify(this.countryMapper).selectByName("XX");
		
		//normally should not get there
		Assert.assertFalse("Exception should have occurred.", airports == null);
	}
	


	/**
	 * @return
	 */
	private List<Map<String, Object>> getMockAirports() {

		
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Map<String, Object> schiphol = new HashMap<String, Object>();
		
		schiphol.put("id", 2513);
		res.add(schiphol);
		
		return res;
	}
	
	private Map<String, Object> getMockCountry() {

		Map<String, Object> nl = new HashMap<String, Object>();
		nl.put("id", 2513);
		nl.put(CountryColumn.CODE, "NL");
		
		return nl;
	}


	@Test
	public void testGetNextPage() throws Exception {
		Mockito.when(this.airportMapper.countByCountryCode(Mockito.anyString())).thenReturn(22);
		Page nextPage = this.queryService.getNextPage("NL", 0, 10);
		//from the beginning, 0 to 10 should return next page from 10 to 20
		Assert.assertEquals(10, nextPage.getOffset());
		Assert.assertEquals(20, nextPage.getLimit());
	}
	
	@Test
	public void testGetNextPageWhenLast() throws Exception {
		
		Mockito.when(this.airportMapper.countByCountryCode(Mockito.anyString())).thenReturn(22);
		
		Page nextPage = this.queryService.getNextPage("NL", 10, 10);
		//we are in page 2 (10), and we want 10 more, but we only have 2 more
		Assert.assertEquals(20, nextPage.getOffset());
		Assert.assertEquals(22, nextPage.getLimit());

		//we are in last page should return the the same (no change)
		nextPage = this.queryService.getNextPage("NL", 20, 10);
		//we are in page 2 (10), and we want 10 more, but we only have 2 more
		Assert.assertEquals(20, nextPage.getOffset());
		Assert.assertEquals(22, nextPage.getLimit());
	}
	
	@Test
	public void testGetPrevPage() throws Exception {
		Mockito.when(this.airportMapper.countByCountryCode(Mockito.anyString())).thenReturn(22);
		Page nextPage = this.queryService.getPreviousPage("NL", 10, 10);
		
		Assert.assertEquals(0, nextPage.getOffset());
		Assert.assertEquals(10, nextPage.getLimit());
	}
	

	@Test
	public void testGetPrevPageWhenFirst() throws Exception {
		
		Mockito.when(this.airportMapper.countByCountryCode(Mockito.anyString())).thenReturn(22);
		
		Page nextPage = this.queryService.getPreviousPage("NL", 0, 10);
		
		Assert.assertEquals(0, nextPage.getOffset());
		Assert.assertEquals(10, nextPage.getLimit());
	}


}
