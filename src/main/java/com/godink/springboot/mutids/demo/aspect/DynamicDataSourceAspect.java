package com.godink.springboot.mutids.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.godink.springboot.mutids.demo.annotation.TargetDataSource;
import com.godink.springboot.mutids.demo.context.DynamicDataSourceContextHolder;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {
	
	@Before("@annotation(targetDataSource)")
	public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
		// TODO Auto-generated method stub
		if(!DynamicDataSourceContextHolder.dataSourceKeys.contains(targetDataSource.value().getName())) {
			log.info("DataSource [{}] 不存在，使用默认的数据源master");
			return;
		}
		DynamicDataSourceContextHolder.setDataSourceKey(targetDataSource.value().getName());
		log.info("Switch dataSource to [{}] in method [{}]"
				, DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());

	}
	
	@After("@annotation(targetDataSource)")
	public void restoreDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
		DynamicDataSourceContextHolder.clearDataSourceKey();
		log.info("Restore datasource to [{}] in method [{}]"
				, DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
	}
}
