package com.godink.springboot.mutids.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.godink.springboot.mutids.demo.context.DynamicDataSourceContextHolder;
import com.godink.springboot.mutids.demo.datasource.DynamicRoutingDataSource;
import com.godink.springboot.mutids.demo.enums.DataSourceKey;

/*
 * 数据源配置类
 */
@Configuration
public class DataSourceConfigurer {
	
	/**
	 * 配置主数据源
	 */
	@Bean(name = "master")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.druid.master")
	public DataSource master() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 配置从数据源
	 */
	@Bean(name = "slave")
	@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
	public DataSource slave() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("dynamicDataSource")
	public DataSource dynamicDataSource() {
		Map<Object, Object> dataSources = new HashMap<>();
		dataSources.put(DataSourceKey.MASTER.getName(), master());
		dataSources.put(DataSourceKey.SLAVE.getName(), slave());
		
		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		dynamicRoutingDataSource.setDefaultTargetDataSource(master());
		dynamicRoutingDataSource.setTargetDataSources(dataSources);
		
		DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSources.keySet());
		
		return dynamicRoutingDataSource;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		
		sqlSessionFactoryBean.setConfiguration(configuration);	
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		return sqlSessionFactoryBean;
	}
	
	/**
	 * 事务
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}
}
