
package com.lunatech.airports.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Repository;

/**
 * The spring configuration (annotation based)
 * 
 * @author Fernando
 */

@Configuration
@ComponentScan("com.lunatech.airports")
@MapperScan(basePackages = { "com.lunatech.airports.persistence" }, annotationClass = Repository.class)
// @EnableTransactionManagement
public class ApplicationConfig {

	/** The logger */
	final Logger logger = (Logger) LoggerFactory.getLogger(ApplicationConfig.class);

	// ===============================
	// Database Init
	// ===============================

	/**
	 * Creates the database in memory and reads the dbinit file from resources
	 */
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:db/dbinit.sql").build();
	}

	/**
	 * The session factory
	 * @return
	 */
	@Bean
	public SqlSessionFactoryBean sessionFactory() {

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		
		return sessionFactory;
	}

	

}
