package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.lunatech.airports.config.ApplicationConfig;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
public class AirportMapperTest {

	@Autowired
	AirportMapper airportMapper;

	

	/** The logger */
	final Logger logger = (Logger) LoggerFactory.getLogger(AirportMapperTest.class);

	
	@Test
	public void testSelectByCountryCode() throws Exception {
		List<Map<String, Object>> airports =  this.airportMapper.selectByCountryCode("NL");
		
		//logger.info("{} Airports in NL", airports.size());
		//logger.info(airports.get(0).toString());
		
		Assert.assertEquals(80, airports.size());
	}

	@Test
	public void testSelectTenBest() throws Exception {
		List<Map<String, Object>> countries =  this.airportMapper.selectTenBest();
		logger.info(countries.get(0).toString());
	}

	@Test
	public void testGetRunways() throws Exception {
		
		//List<Map<String, Object>> runways =  this.airportMapper.getRunways("6669");
		List<Map<String, Object>> list = this.airportMapper.selectByCountryCodeWithRunaways("ES", 10, 4);
		logger.debug("" + list.size());
		//Map<String, Object> airports = this.airportMapper.selectByCodeWithRunways("6669");
		//logger.debug("" + airports.keySet().size());
		//logger.debug(airports.toString());
		
		//Airport airport = this.airportMapper.selectByCodeWithRunways("6669");
		//logger.debug(airport.getRunways().toString());
	}
}
