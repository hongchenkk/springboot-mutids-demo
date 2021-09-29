package com.godink.springboot.mutids.demo.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godink.springboot.mutids.demo.annotation.TargetDataSource;
import com.godink.springboot.mutids.demo.enums.DataSourceKey;

@RestController
@RequestMapping("/mutids")
public class DemoRestController {

	//默认数据源：master, 配置注解，不配置value值即可
	@GetMapping("/default")
	@TargetDataSource
	public ResponseEntity<Object> useDefault() {
		return ResponseEntity.ok("default");
	}
	
	//master数据源
	@GetMapping("/master")
	@TargetDataSource(DataSourceKey.MASTER)
	public ResponseEntity<Object> useMaster() {
		return ResponseEntity.ok("master");
	}
	
	//salve数据源
	@GetMapping("/slave")
	@TargetDataSource(DataSourceKey.SLAVE)
	public ResponseEntity<Object> useSlave() {
		return ResponseEntity.ok("slave");
	}
}
