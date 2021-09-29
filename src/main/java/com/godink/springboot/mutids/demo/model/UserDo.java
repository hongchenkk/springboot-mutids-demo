package com.godink.springboot.mutids.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDo /* extends DefaultBaseDo */{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long age;
	private Integer sex;
}
