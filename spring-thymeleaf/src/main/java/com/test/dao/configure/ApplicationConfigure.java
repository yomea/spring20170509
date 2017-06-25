package com.test.dao.configure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ComponentScan(basePackages={"com.test.service"})
@PropertySource("classpath:jdbc.properties")
public class ApplicationConfigure {
	
	@Autowired
    Environment env;
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		
		DruidDataSource dataSource = new DruidDataSource();
		
		dataSource.setUrl(env.getProperty("jdbc.url"));
		
		dataSource.setUsername(env.getProperty("jdbc.username"));
		
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		
		return dataSource;
	}
	
	@Bean(name="txManager")
	public DataSourceTransactionManager txManager() {
		
		DataSourceTransactionManager txM = new DataSourceTransactionManager();
		
		txM.setDataSource(dataSource());
		
		return txM;
	}
	

}
