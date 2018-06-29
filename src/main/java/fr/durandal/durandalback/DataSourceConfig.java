package fr.durandal.durandalback;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


public class DataSourceConfig {
	@Autowired
	private Environment environment;
	
	/*@Bean(destroyMethod = "close")
	public DataSource getDataSource() {		
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url(environment.getProperty("db.url"))
				.username(environment.getProperty("db.username"))
				.password(environment.getProperty("db.password"))
				.build();
	}*/
}
