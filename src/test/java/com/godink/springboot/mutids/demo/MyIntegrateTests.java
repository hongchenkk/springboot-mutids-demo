package com.godink.springboot.mutids.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 集成测试例子: 一般测试bean，service，dao，mapper等
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MyIntegrateTests {
	
//	@Autowired
//	private Executor taskExecutor;

	@Test
	void test() throws Exception {
		System.out.println(123);
//		System.out.println(taskExecutor);

	}

}
