package com.imooc.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.auth.dao.FunctionDao;
import com.imooc.auth.entity.Function;
import com.imooc.auth.service.impl.BaseFunctionService;

@Service
public class FunctionService implements BaseFunctionService {
	
	@Autowired private FunctionDao functionDao;

	@Override
	public void addFunction(Function function) {
		functionDao.saveFunctions(function);
	}

	@Override
	public void updateUrl(Long id, String url) {
		functionDao.updateUrl(id, url);
	}

	@Override
	public List<Function> getFunctions(int page, int size, Long parentId) {
		return functionDao.findFunctions(page, size, parentId);
	}

	@Override
	public void deleteFunctionById(Long id) {
		functionDao.deleteById(id);
	}

	@Override
	public List<Function> getALLFunctions() {
		return functionDao.findALLFunctions();
	}
	
	
	
	
	
}
