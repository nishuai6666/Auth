package com.imooc.auth.service.impl;

import java.util.List;

import com.imooc.auth.entity.Function;

public interface BaseFunctionService {
	
	/**
	 * 添加功能(菜单)
	 * @param function
	 */
	
	void addFunction(Function function);
	
	
	/**
	 * 根据功能ID更新URL信息
	 * @param id,url
	 */
	
	void updateUrl(Long id, String url);
	
	/**
	 * 分页查询功能（指定父节点的子节点）
	 * @param page,size,parentId（父节点ID）
	 */
	List<Function> getFunctions(int page,int size, Long parentId);
	
	/**
	 * 删除功能
	 * @param id
	 */
	
	void deleteFunctionById(Long id);
	
	/**
	 * 查询全部功能信息（列表形式）
	 * @return 全部功能信息
	 */
	List<Function> getALLFunctions();
	
}
