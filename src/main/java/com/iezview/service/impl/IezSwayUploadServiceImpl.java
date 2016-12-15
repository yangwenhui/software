package com.iezview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iezview.domain.IezSwayUpload;
import com.iezview.repository.IezSwayUploadRepository;
import com.iezview.service.IezSwayUploadService;

@Service
public class IezSwayUploadServiceImpl implements IezSwayUploadService {

	@Autowired
	private IezSwayUploadRepository iezSwayUploadRepository;

	@Override
	public IezSwayUpload findById(Long id) {
		// TODO Auto-generated method stub
		return iezSwayUploadRepository.findById(id);
	}

}
