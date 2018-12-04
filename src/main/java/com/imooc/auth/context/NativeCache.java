package com.imooc.auth.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.auth.entity.Function;
import com.imooc.auth.entity.Role;
import com.imooc.auth.service.FunctionService;
/**
 * 本地缓存类
 * @author Administrator
 *
 */
@Service
public class NativeCache {
	
	private Map<Long,Function> functionMap = new HashMap<>();
	
	private  Map<Long,List<Role>> userRoleMap = new HashMap<>();
	
	@Autowired
	private FunctionService functionService;
	
	@PostConstruct
	public void init(){
		List<Function> functionList = functionService.getALLFunctions();
		functionList.forEach((function)->functionMap.put(function.getId(),function));
	}
	
	
	
	public List<Function> getFunctions(){
		if (functionMap.isEmpty()) {
			init();
		}
		return new ArrayList<>(functionMap.values());
	}
	
	public void addFunction(Function function){
		functionMap.put(function.getId(), function); 
	}
	
	public void replaceFunction(Function function){
		if (functionMap.containsKey(function.getId())) {
			functionMap.remove(function.getId());
			functionMap.put(function.getId(), function);
		}
	}
	
	public void removeFunction(Long functionId){
		if (functionMap.containsKey(functionId)) {
			functionMap.remove(functionId);
		}
		
	}
	
	public void setRoles(Long userId,List<Role> roles){
		userRoleMap.put(userId, roles);
	}
	
	public List<Role> getRoles(Long userId){
		return userRoleMap.get(userId);
	}
	
	public Function getFunction(Long userId){
		return functionMap.get(userId);
	}
}
