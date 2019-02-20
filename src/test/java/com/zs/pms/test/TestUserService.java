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
	// 自动注入业务
	@Autowired
	UserService us;

	/**
	 * hello测试
	 */
	public void testHello() {
		us.hello();
	}
	
	/**
	 * 查看id=？的权限为
	 */
	public void testLogin() {
		// 创建新盒子，用来装所属ID的权限
		List<TPermission> list1 = us.queryByUid(3084);
		for (TPermission per : list1) {
			System.out.println(per.getId() + ": " + per.getPname());
		}
		System.out.println("------------------整理后的权限划分-----------------");
		// 遍历per1：一级权限
		for (TPermission per1 : us.genMenu(list1)) {
			System.out.println(per1.getId() + ":" + per1.getPname());
			for (TPermission per2 : per1.getChildren()) {
				System.out.println("----------" + per2.getId() + ":" + per2.getPname());
			}
		}
	}

	/**
	 * 根据条件查找
	 */
	
	public void testQuery() {
		// 创建查询对象
		QueryUser query = new QueryUser();
		
		for(TUser user:us.queryByPage(1, query)) {
			System.out.println(user.getId()+""+user.getLoginname());
		}
		//System.out.println("共"+us.queryPageCont(query)+"页");
	}

	
	/**
	 * 批量删除
	 */
	
	public void testDeleteUserByIds() {
		int[] ids = { 3103, 3099 };
		us.deleteUserByIds(ids);
	}
	/**
	 * 删除一条
	 */
	
	public void testDeleteUserById() {
		int id=3098;
		us.deleteUserById(id);
	}
	
	
	/**
	 *修改 
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
	 * 新增
	 * @throws AppException 
	 */
	@Test
	public void testInsert() throws AppException {
		TUser user=new TUser();
		TDep dept=new TDep();
		user.setLoginname("insertSpringSiWu");
		user.setPassword("1234");
		user.setSex("男");
		user.setBirthday(new Date());
		user.setEmail("insert222@123.com");
		dept.setId(7);
		user.setDept(dept);
		user.setRealname("新增测试9");
		user.setCreator(3084);
		user.setIsenabled(1);
		System.out.println(us.insertUser(user));
	}

}
