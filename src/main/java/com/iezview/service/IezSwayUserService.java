package com.iezview.service;

import com.iezview.domain.IezSwayUser;

public interface IezSwayUserService {

	IezSwayUser findByNameAndPassword(IezSwayUser user);

	IezSwayUser getByUsername(String username);

}
