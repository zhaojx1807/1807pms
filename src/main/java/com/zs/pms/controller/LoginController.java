package com.zs.pms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller // ��һ��������
public class LoginController {
	@Autowired
	UserService us;

	//����ӳ��  ��urlƥ��  ȥ��¼ҳ��
	@RequestMapping("/login.do")
	public String tologin() {
		return "login";

	}

	// ��¼�ɹ�����ҳ��
	@RequestMapping("/main.do")
	public String main(QueryLogin login, HttpSession session, ModelMap model) {
		//1����֤��֤��
				String code=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				//���ɵ���֤���ҳ�����֤�벻���
				if (!code.equals(login.getChkcode())) {
					model.addAttribute("MSG", "��֤�������������������");
					return "login";
				}
				//2����֤�˺ź�����
				//װ������
				QueryUser query=new QueryUser();
				query.setLoginname(login.getUsername());//��¼��
				//MD5����
				MD5 md5 =new MD5();
				query.setPassword(md5.getMD5ofStr(login.getPassword()));//����
				query.setIsenabled(1);//���ÿ���
				//���ص�¼���û�
				List<TUser> users = us.queryByCon(query);
				//��½ʧ��
				if (users==null||users.size()!=1) {
					model.addAttribute("MSG", "�û�������������ʧ������������");
					return "login";
				}
				//��¼�ɹ�  װsession
				session.setAttribute("CUSER", users.get(0));
				return "main";
	}

	// ����ҳ��
	@RequestMapping("/top.do")
	public String top(ModelMap model) {
		// ��ǰʱ��
		model.addAttribute("TODAY", DateUtil.getStrDate());
		return "top";

	}

	@RequestMapping("/left.do")
	public String left(HttpSession session, ModelMap model) {
		// ��õ�ǰ��¼�û�
		TUser cu = (TUser) session.getAttribute("CUSER");
		// ��ø��û���Ȩ���б�
		List<TPermission> list1 = us.queryByUid(cu.getId());
		// ���ز˵�
		model.addAttribute("MENU", us.genMenu(list1));
		return "left";
	}

	@RequestMapping("/right.do")
	public String right() {
		return "right";
	}

}
