package com.godink.springboot.mutids.demo.enums;

import lombok.Getter;
import lombok.Setter;


public enum DataSourceKey {
	
	MASTER("master"),
	SLAVE("slave");
	
	@Getter
	@Setter
	private String name;
	
	private DataSourceKey(String name) {
		this.name = name;
	}

}
