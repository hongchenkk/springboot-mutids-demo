package com.godink.springboot.mutids.demo.integrate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.godink.springboot.mutids.demo.model.UserDo;
import com.godink.springboot.mutids.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**集成测试：测试数据源注解在service层的动态数据源切换*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MutiDsIntegrateTest {
	
	@Autowired
	private UserService userService;
	
	/**不配置数据源注解时，用的是默认的数据源master*/
	@Test
	void testServiceNoAnnotation() {
		List<UserDo> allUserNo = userService.getAllUser();
		log.info("testServiceNoAnnotation: [{}]", allUserNo);
	}

	@Test
	void testServiceDefault() {
		List<UserDo> allUserDefault = userService.getAllUserDefault();
		log.info("testServiceDefault: [{}]", allUserDefault);
	}
	
	@Test
	void testServiceMaster() {
		List<UserDo> allUserMaster = userService.getAllUserMaster();
		log.info("testServiceMaster: [{}]", allUserMaster);
	}
	
	
	@Test
	void testServiceSlave() {
		List<UserDo> allUserSlave = userService.getAllUserSlave();
		log.info("testServiceSlave: [{}]", allUserSlave);
	}

}
