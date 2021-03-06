package com.lunatech.airports.persistence;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.lunatech.airports.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })

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
public class CountryMapperTest {

	@Autowired
	CountryMapper countryMapper;

	@Test
	public void testSelectByCode() throws Exception {
		Map<String, Object> country = this.countryMapper.selectByCode("NL");
		Assert.assertEquals("Expected 'Netherlands'", "Netherlands", country.get("NAME"));
		
		Map<String, Object> byName = this.countryMapper.selectByName("Neth%");
		Assert.assertEquals("Expected 'Netherlands'", "Netherlands", byName.get("NAME"));
	}

}
