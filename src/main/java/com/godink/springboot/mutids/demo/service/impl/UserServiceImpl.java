package com.godink.springboot.mutids.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godink.springboot.mutids.demo.mapper.UserMapper;
import com.godink.springboot.mutids.demo.model.UserDo;
import com.godink.springboot.mutids.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserDo> getAllUser() {
		return userMapper.getAllUser();
	}

}
