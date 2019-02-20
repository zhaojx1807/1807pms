package com.zs.pms.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

//
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {
	// �Զ�ע��ҵ��
	@Autowired
	UserService us;

	/**
	 * hello����
	 */
	public void testHello() {
		us.hello();
	}
	
	/**
	 * �鿴id=����Ȩ��Ϊ
	 */
	public void testLogin() {
		// �����º��ӣ�����װ����ID��Ȩ��
		List<TPermission> list1 = us.queryByUid(3084);
		for (TPermission per : list1) {
			System.out.println(per.getId() + ": " + per.getPname());
		}
		System.out.println("------------------������Ȩ�޻���-----------------");
		// ����per1��һ��Ȩ��
		for (TPermission per1 : us.genMenu(list1)) {
			System.out.println(per1.getId() + ":" + per1.getPname());
			for (TPermission per2 : per1.getChildren()) {
				System.out.println("----------" + per2.getId() + ":" + per2.getPname());
			}
		}
	}

	/**
	 * ������������
	 */
	
	public void testQuery() {
		// ������ѯ����
		QueryUser query = new QueryUser();
		
		for(TUser user:us.queryByPage(1, query)) {
			System.out.println(user.getId()+""+user.getLoginname());
		}
		//System.out.println("��"+us.queryPageCont(query)+"ҳ");
	}

	
	/**
	 * ����ɾ��
	 */
	
	public void testDeleteUserByIds() {
		int[] ids = { 3103, 3099 };
		us.deleteUserByIds(ids);
	}
	/**
	 * ɾ��һ��
	 */
	
	public void testDeleteUserById() {
		int id=3098;
		us.deleteUserById(id);
	}
	
	
	/**
	 *�޸� 
	 * @throws AppException 
	 */
	
	public void testUpdate() throws AppException {
		TUser user=new TUser();
		user.setId(3097);
		TDep dep=new TDep();
		dep.setId(6);
		user.setDept(dep);
		user.setLoginname("update123");
		user.setRealname("update123123");
		us.updateUser(user);
		
	}
	/**
	 * ����
	 * @throws AppException 
	 */
	@Test
	public void testInsert() throws AppException {
		TUser user=new TUser();
		TDep dept=new TDep();
		user.setLoginname("insertSpringSiWu");
		user.setPassword("1234");
		user.setSex("��");
		user.setBirthday(new Date());
		user.setEmail("insert222@123.com");
		dept.setId(7);
		user.setDept(dept);
		user.setRealname("��������9");
		user.setCreator(3084);
		user.setIsenabled(1);
		System.out.println(us.insertUser(user));
	}

}
