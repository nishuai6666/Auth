package com.imooc.auth.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imooc.auth.common.Whether;
import com.imooc.auth.context.LoginUserCache;
import com.imooc.auth.dto.Accordion;
import com.imooc.auth.entity.Function;
import com.imooc.auth.entity.Role;
import com.imooc.auth.entity.RoleFunction;
import com.imooc.auth.entity.User;
import com.imooc.auth.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	 public String index(){
		 return "/sercurity/login";
	 }
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(Model model,String name,String pwd){
		User user = userService.getUser(name, pwd);
		if (null == user) {
			return "/security/login";
		}
		
		LoginUserCache.put(user, 30*60);
		
		if (Objects.equals("admin", user.getName())) {
			model.addAttribute("accordions");
		}else{
			
		}
		
		return"";
	}
	
	private List<Accordion> getAccordions(boolean isAdmin){
		Set<String> permissionUrls = new HashSet<>();
		Set<Long> rootFunctionIdSet = new HashSet<>();
		
		if (!isAdmin) {
			for (Role role : nativeCache) {
				List<RoleFunction> roleFunction = roleService
				for (RoleFunction rf : roleFunction) {
					Function func = nativeCache
					if (Objects.equals(func.getAccordion(), Whether.YES.getValue())) {
						rootFunctionIdSet.add(func.getId());
					}else{
						permissionUrls.add(func.getUrl());
					}
				}
			}
		}
		
		Map<Long,Accordion> accordionMap = new HashMap<>();
		List<Accordion> permissionAccordionSet = new LinkedList<>();
		
		List<Function> functions = nativeCache.getFunctions();
		
		
		List<Accordion> rootAccordionSet = new LinkedList<>();
		for (Function function : Functions) {
			Accordion accordion = new Accordion(function.getId().fu)
			accordionMap.
		}
		
	}
	
	
	
	
	
	
	
	
}
