package com.lunatech.airports.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lunatech.airports.persistence.AirportMapper;
import com.lunatech.airports.persistence.RunwayMapper;
import com.lunatech.airports.services.ReportService;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {

	/** Access to the airport repository */
	@Mock
	private AirportMapper airportMapper;
	
	/** Access to the runway repository */
	@Mock
	private RunwayMapper runwayMapper;
	
	
	@InjectMocks
	private ReportService reportService = new ReportServiceImpl();

	@Test
	public void testGetTenCountriesWithLessAirports() throws Exception {
		this.reportService.getTenCountriesWithLessAirports();
		Mockito.verify(this.airportMapper).selectTenWorst();
	}
	
	@Test
	public void testGetTenCountriesWithMoreAirports() throws Exception {
		this.reportService.getTenCountriesWithMoreAirports();
		Mockito.verify(this.airportMapper).selectTenBest();
	}

	@Test
	public void testGetSurfaces() throws Exception {
		this.reportService.getSurfaces();
		Mockito.verify(this.runwayMapper).getRunwaysPerCountry();
	}

	

}
