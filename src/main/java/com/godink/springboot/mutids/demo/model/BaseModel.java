package com.godink.springboot.mutids.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础模型
 * 以下字段默认都是有的，可以看情况使用，表也可以没有
 */
public abstract class BaseModel<ID extends Serializable> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**主键：禁止使用0值*/
	protected ID id;
	
    /**创建人*/
    protected Long createUser;

    /**创建人姓名*/
    protected String createUsername;

    /**创建时间*/
    protected LocalDateTime createTime;
    
    /**最后修改人*/
    protected Long lastModifyUser;

    /**最后修改人名称*/
    protected String lastModifyUsername;

    /**最后修改时间*/
    protected LocalDateTime lastModifyTime;

    /**删除人*/
    protected Long deleteUser;

    /**删除人姓名*/
    protected String deleteUsername;

    /**删除时间*/
    protected LocalDateTime deleteTime;
}
