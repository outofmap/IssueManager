package issueManager.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Configuration
@PropertySource("classpath:/dev.properties")
@ComponentScan(basePackages = {"issueManager.dao","issueManager.service","issueManager.controller.*"}, 
excludeFilters = @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION) )
public class AppConfig {
	
	@Value("${db.driver}")
	private String DB_DRIVER;
	@Value("${db.url}")
	private String DB_URL;
	@Value("${db.username}")
	private String DB_USERNAME;
	@Value("${db.pw}")
	private String DB_PW;
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DB_DRIVER);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USERNAME);
		ds.setPassword(DB_PW);
		logger.debug("data base:"+ds.getDriverClassName()+"username"+ds.getUsername());
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
