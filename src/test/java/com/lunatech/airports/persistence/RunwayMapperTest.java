package com.lunatech.airports.persistence;

import java.util.List;
import java.util.Map;

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
public class RunwayMapperTest  {

	

	/** The logger */
	final Logger logger = (Logger) LoggerFactory.getLogger(RunwayMapperTest.class);

	@Autowired
	private RunwayMapper runwayMapper;
	
	@Test
	public void testGetRunways() throws Exception {
		List<Map<String, Object>> runways = this.runwayMapper.getRunwaysPerCountry();
		
		runways.forEach(map -> {
			logger.info(map.get("name") + " [" + map.get("list") + "]");
		});	
	}

	
}
