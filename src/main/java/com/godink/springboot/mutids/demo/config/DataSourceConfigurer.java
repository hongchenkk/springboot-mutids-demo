package com.godink.springboot.mutids.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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
		return DruidDataSourceBuilder.create().build();
	}
	
	/**
	 * 配置从数据源
	 */
	@Bean(name = "slave")
	@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
	public DataSource slave() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean("dynamicDataSource")
	public DataSource dynamicDataSource() {
		Map<Object, Object> dataSources = new HashMap<>();
		dataSources.put(DataSourceKey.MASTER.getName(), master());
		dataSources.put(DataSourceKey.SLAVE.getName(), slave());
		
		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		//当不配置数据源注解，既TargetDataSouce时，默认使用的数据源
		dynamicRoutingDataSource.setDefaultTargetDataSource(master());
		dynamicRoutingDataSource.setTargetDataSources(dataSources);
		dynamicRoutingDataSource.afterPropertiesSet();
		
		DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSources.keySet());
		
		return dynamicRoutingDataSource;
	}
	
	@Bean
	//这里通过注入会启动失败，暂时使用代码解析mapper.xml和配置文件
//	@ConfigurationProperties(prefix = "mybatis")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		//这里如果配置了会启动报错，所以去掉
//		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//		configuration.setMapUnderscoreToCamelCase(true);
//		sqlSessionFactoryBean.setConfiguration(configuration);
		sqlSessionFactoryBean.setConfigLocation(resolveConfigLocation());
		sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations());
		sqlSessionFactoryBean.setDataSource(dynamicDataSource());
		return sqlSessionFactoryBean;
	}
	
	/**
	 * mapper sql文件
	 */
    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:mapper/**/*.xml");
//      mapperLocations.add("classpath*:com/pab/cc/ces/mapper/*Mapper*.xml");
//      mapperLocations.add("classpath*:com/pab/cc/ams/mapper/*Mapper*.xml");
        List<Resource> resources = new ArrayList();
        if (null != mapperLocations) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }
    
    /**
     * mybatis配置文件
     */
    public Resource resolveConfigLocation() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        return resourceResolver.getResource("classpath:mybatis/mybatis-config.xml");
	}
	
	/**
	 * 事务
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dynamicDataSource());
	}
}
