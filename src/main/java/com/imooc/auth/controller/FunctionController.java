package com.imooc.auth.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.auth.common.AjaxResult;
import com.imooc.auth.common.tree.Node;
import com.imooc.auth.common.tree.Tree;
import com.imooc.auth.context.NativeCache;
import com.imooc.auth.entity.Function;
import com.imooc.auth.service.FunctionService;

@Controller
@RequestMapping("/function")
public class FunctionController {
	@Autowired
	private FunctionService functionService;
	@Autowired
	private NativeCache nativeCache;
	
	/**
	 * 菜单首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String menuList() {
		return "/security/function/function_list";
	}
	
	/**
	 * 新建，修改功能
	 * @param function 功能
	 * @return 操作结果
	 */
	@RequestMapping(value = "/addEditFunction", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditFunction(Function function) {
		if (null == function.getId()) {
			function.setSerialNum(nativeCache.getFunctions().size());
			functionService.addFunction(function);
			nativeCache.addFunction(function);
		} else {
			functionService.updateUrl(function.getId(), function.getUrl());
			nativeCache.replaceFunction(function);
		}
		return AjaxResult.success();
	}
	
	/**
	 * 根据ID删除功能信息
	 * @param 功能id
	 * @return操作结果
	 */
	
	@RequestMapping(value = "/deleteFunction", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteFunctionById(Long id) {
		functionService.deleteFunctionById(id);
		nativeCache.removeFunction(id);
		return AjaxResult.success();
	}
	
	/**
	 * 查询子功能信息
	 * @param page 当前页码
	 * @param rows 每页记录数
	 * @param parentId 父节点ID
	 * @return 子功能信息集合
	 */
	@RequestMapping(value = "/getSubFunction", method = RequestMethod.POST)
	@ResponseBody
	public List<Function> getSubFunction(Integer page, Integer rows,
			Long parentId) {
		if (Objects.equals(null, parentId)) {
			parentId = 0L;
		}
		return functionService.getFunctions(page, rows, parentId);

	}
	
	
	/**
	 * 构建用于新建，修改使用的菜单(功能)树
	 * @return 包含福字关系的树型节点集合
	 */
	@RequestMapping(value = "buildFunctionTreeForEdit", method = RequestMethod.POST)
	@ResponseBody
	public List<Node> buildMenuTreeForEdit() {
		List<Function> list = nativeCache.getFunctions();
		Tree tree = new Tree(list);
		return tree.build();
	}

}
