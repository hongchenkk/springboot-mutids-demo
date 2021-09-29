package com.godink.springboot.mutids.demo.model;

import java.io.Serializable;

/**
 * 域层对象，下层为Dto和Po
 */
public class BaseDo<ID extends Serializable> extends BaseModel<ID>{
	private static final long serialVersionUID = 1L;

}
