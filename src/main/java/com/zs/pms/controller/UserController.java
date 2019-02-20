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

	/**�б�
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
		// ���ط�ҳ����
		map.addAttribute("LIST", us.queryByPage(Integer.parseInt(page), query));
		// ������ҳ��
		map.addAttribute("PAGECONT", us.queryPageCont(query));
		// ��ǰҳ��
		map.addAttribute("PAGE", page);
		// ����
		map.addAttribute("QUERY", query);
		return "user/list";

	}

	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap map) {
		// ����һ������
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}

	@RequestMapping("/user/add.do")
	public String add(TUser user, HttpSession session, ModelMap map) {
		TUser cu = (TUser) session.getAttribute("CUSER");
		try {
			// װ������
			user.setIsenabled(1);// ����
			user.setCreator(cu.getId());// ������
			us.insertUser(user);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			return this.toAdd(map);
		}
	}

	// ɾ��һ��
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		us.deleteUserById(id);
		// ��תurl
		return "redirect:list.do";
	}

	// ����ɾ��
	@RequestMapping("/user/deletes.do")
	public String deletes(int[] ids) {
		us.deleteUserByIds(ids);
		// ��תurl
		return "redirect:list.do";
	}

	// ����ɾ��
	@RequestMapping("/user/get.do")
	public String get(int id, ModelMap map) {
		// �����û���Ϣ
		map.addAttribute("USER", us.queryById(id));
		// ����һ��������Ϣ
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/update";
	}
	
	@RequestMapping("/user/update.do")
	public String update(TUser user, HttpSession session, ModelMap map) {
		try {
			TUser cu = (TUser) session.getAttribute("CUSER");
			//user.setUpdator();
			
			//�ɹ���תurl
			us.updateUser(user);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			//�쳣
			return this.get(user.getId(), map);
		}
		
	}

	
	
	
	
}
