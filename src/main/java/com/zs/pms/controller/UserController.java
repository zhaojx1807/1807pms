package com.zs.pms.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UserController {
	@Autowired
	UserService us;
	DepService ds;

	/**列表
	 * @param page
	 * @param query
	 * @param map
	 * @return
	 */
	@RequestMapping("/user/list.do")
	public String list(String page, QueryUser query, ModelMap map) {

		if (page == null) {
			page = "1";
		}
		// 返回分页数据
		map.addAttribute("LIST", us.queryByPage(Integer.parseInt(page), query));
		// 返回总页数
		map.addAttribute("PAGECONT", us.queryPageCont(query));
		// 当前页数
		map.addAttribute("PAGE", page);
		// 条件
		map.addAttribute("QUERY", query);
		return "user/list";

	}

	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap map) {
		// 返回一级部门
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}

	@RequestMapping("/user/add.do")
	public String add(TUser user, HttpSession session, ModelMap map) {
		TUser cu = (TUser) session.getAttribute("CUSER");
		try {
			// 装载数据
			user.setIsenabled(1);// 可用
			user.setCreator(cu.getId());// 新增人
			us.insertUser(user);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			return this.toAdd(map);
		}
	}

	// 删除一条
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		us.deleteUserById(id);
		// 跳转url
		return "redirect:list.do";
	}

	// 批量删除
	@RequestMapping("/user/deletes.do")
	public String deletes(int[] ids) {
		us.deleteUserByIds(ids);
		// 跳转url
		return "redirect:list.do";
	}

	// 批量删除
	@RequestMapping("/user/get.do")
	public String get(int id, ModelMap map) {
		// 返回用户信息
		map.addAttribute("USER", us.queryById(id));
		// 返回一级部门信息
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/update";
	}
	
	@RequestMapping("/user/update.do")
	public String update(TUser user, HttpSession session, ModelMap map) {
		try {
			TUser cu = (TUser) session.getAttribute("CUSER");
			//user.setUpdator();
			
			//成功跳转url
			us.updateUser(user);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			//异常
			return this.get(user.getId(), map);
		}
		
	}

	
	
	
	
}
