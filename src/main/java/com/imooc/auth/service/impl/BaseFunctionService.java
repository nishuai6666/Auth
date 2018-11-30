package com.imooc.auth.service.impl;

import java.util.List;

import com.imooc.auth.entity.Function;

public interface BaseFunctionService {
	
	/**
	 * 添加功能
	 * @param function
	 */
	
	void addFunction(Function function);
	
	
	/**
	 * 更改路径
	 * @param id,url
	 */
	
	void updateUrl(Long id, String url);
	
	/**
	 * 查询功能（列表形式）
	 * @param page,size,parentId
	 */
	List<Function> getFunctions(int page,int size, Long parentId);
	
	/**
	 * 删除功能
	 * @param id
	 */
	
	void deleteFunctionById(Long id);
	
	/**
	 * 查询功能（列表形式）
	 * @param 
	 */
	List<Function> getALLFunctions();
	
}
