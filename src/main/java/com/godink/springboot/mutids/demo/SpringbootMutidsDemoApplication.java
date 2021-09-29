package com.godink.springboot.mutids.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.godink.springboot.mutids.demo.mapper")
public class SpringbootMutidsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMutidsDemoApplication.class, args);
	}

}
