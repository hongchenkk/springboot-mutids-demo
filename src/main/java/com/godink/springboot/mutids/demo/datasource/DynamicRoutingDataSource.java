package com.godink.springboot.mutids.demo.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.godink.springboot.mutids.demo.context.DynamicDataSourceContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 这个类AbstractRoutingDataSource,需要引入jdbc才有
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("current datasource is: {}", DynamicDataSourceContextHolder.getDataSourceKey());
		return DynamicDataSourceContextHolder.getDataSourceKey();
	}

}
