package com.godink.springboot.mutids.demo.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godink.springboot.mutids.demo.annotation.TargetDataSource;
import com.godink.springboot.mutids.demo.enums.DataSourceKey;
import com.godink.springboot.mutids.demo.model.UserDo;
import com.godink.springboot.mutids.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;

	/**注解在controller层*/
	@GetMapping("/default")
	@TargetDataSource
	public ResponseEntity<List<UserDo>> getAllUserDefault() {
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@GetMapping("/master")
	@TargetDataSource(DataSourceKey.MASTER)
	public ResponseEntity<List<UserDo>> getAllUserMaster() {
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@GetMapping("/slave")
	@TargetDataSource(DataSourceKey.SLAVE)
	public ResponseEntity<List<UserDo>> getAllUserSlave() {
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	/**注解在service层*/
	@GetMapping("/service/default")
	public ResponseEntity<List<UserDo>> getAllUserDefaultService() {
		return ResponseEntity.ok(userService.getAllUserDefault());
	}
	
	@GetMapping("/service/master")
	public ResponseEntity<List<UserDo>> getAllUserMasterService() {
		return ResponseEntity.ok(userService.getAllUserMaster());
	}
	
	@GetMapping("/service/slave")
	public ResponseEntity<List<UserDo>> getAllUserSlaveService() {
		return ResponseEntity.ok(userService.getAllUserSlave());
	}
}
