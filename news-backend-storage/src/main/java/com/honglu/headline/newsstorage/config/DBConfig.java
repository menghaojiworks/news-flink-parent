package com.honglu.headline.newsstorage.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.honglu.headline.newsstorage.utils.PropertiesUtil;

@Configuration
public class DBConfig {
	
	private Logger logger = LoggerFactory.getLogger(DBConfig.class);
	
	private final static String url = PropertiesUtil.get("spring.datasource.url");

	
	private final static String user = PropertiesUtil.get("spring.datasource.username");

	
	private final static String password = PropertiesUtil.get("spring.datasource.password");

	
	private final static String driverClass = PropertiesUtil.get("spring.datasource.driverClassName");

	
	private final static String filters = PropertiesUtil.get("spring.datasource.filters");
	
	private final static int initialSize = Integer.parseInt(PropertiesUtil.get("spring.datasource.initialSize"));

	
	private final static int minIdle = Integer.parseInt(PropertiesUtil.get("spring.datasource.minIdle"));

	
	private final static int maxActive = Integer.parseInt(PropertiesUtil.get("spring.datasource.maxActive"));

	private final static int maxWait = Integer.parseInt(PropertiesUtil.get("spring.datasource.maxWait"));

	private final static int timeBetweenEvictionRunsMillis = Integer.parseInt(PropertiesUtil.get("spring.datasource.timeBetweenEvictionRunsMillis"));

	private final static int minEvictableIdleTimeMillis = Integer.parseInt(PropertiesUtil.get("spring.datasource.minEvictableIdleTimeMillis"));

	private final static String validationQuery = PropertiesUtil.get("spring.datasource.validationQuery");

	private final static boolean testWhileIdle = Boolean.valueOf(PropertiesUtil.get("spring.datasource.testWhileIdle"));

	private final static boolean testOnBorrow = Boolean.valueOf(PropertiesUtil.get("spring.datasource.testOnBorrow"));

	private final static boolean testOnReturn = Boolean.valueOf(PropertiesUtil.get("spring.datasource.testOnReturn"));

	private final static boolean poolPreparedStatements = Boolean.valueOf(PropertiesUtil.get("spring.datasource.poolPreparedStatements"));
	
	
	
	@Bean(name = "DataSource")
	public DataSource DataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		try {
			dataSource.setInitialSize(initialSize);
			dataSource.setMinIdle(minIdle);
			dataSource.setMaxActive(maxActive);
			dataSource.setMaxWait(maxWait);
			dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			dataSource.setValidationQuery(validationQuery);
			dataSource.setTestWhileIdle(testWhileIdle);
			dataSource.setTestOnBorrow(testOnBorrow);
			dataSource.setTestOnReturn(testOnReturn);
			dataSource.setPoolPreparedStatements(poolPreparedStatements);
			dataSource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("数据源监听器失败：{}", e);
		}
		return dataSource;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource());
		logger.info("初始化jdbcTemplate完成=============================>");
		return jdbcTemplate;
	} 
}
