package com.iezview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iezview.domain.IezSwayUser;
import com.iezview.repository.IezSwayUserRepository;
import com.iezview.service.IezSwayUserService;

@Service
public class IezSwayUserServiceImpl implements IezSwayUserService {

	@Autowired
	private IezSwayUserRepository userRepository;

	@Override
	public IezSwayUser findByNameAndPassword(IezSwayUser user) {
		return userRepository.findByNameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public IezSwayUser getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

}
