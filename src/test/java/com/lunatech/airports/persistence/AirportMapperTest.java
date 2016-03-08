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


/**
 * These are not normal unit tests, instead, these are accessing the real database.
 * It is normally not being done in production, but since here the database is memory based
 * and created at every execution, it is not a risk.
 * 
 * The tests in here are only testing that the sql syntax is correct.
 * 
 * @author Fernando
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
public class AirportMapperTest {

	@Autowired
	AirportMapper airportMapper;

	

	/** The logger */
	final Logger logger = (Logger) LoggerFactory.getLogger(AirportMapperTest.class);

	

	@Test
	public void testSelectTenBest() throws Exception {
		List<Map<String, Object>> countries =  this.airportMapper.selectTenBest();
		Assert.assertEquals(10, countries.size());
	}
	
	@Test
	public void testSelectTenWorst() throws Exception {
		List<Map<String, Object>> countries =  this.airportMapper.selectTenWorst();
		Assert.assertEquals(10, countries.size());
	}

	@Test
	public void testGetRunways() throws Exception {
		
		List<Map<String, Object>> list = this.airportMapper.selectByCountryCodeWithRunaways("ES", 10, 4);
		Assert.assertEquals(4, list.size());
	}

	@Test
	public void testCountByCountryCode() throws Exception {
		int res = this.airportMapper.countByCountryCode("ES");
		Assert.assertNotEquals(-1, res);
	}
}
