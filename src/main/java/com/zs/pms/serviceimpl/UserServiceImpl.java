package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;

@Service
@Transactional // 开始事物的业务对象
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Override
	public void hello() {

		System.out.println("hello Spring测试成功");
	}

	@Override
	public List<TPermission> queryByUid(int id) {

		return dao.queryByUid(id);
	}

	/**
	 * 通过id查找权限列表菜单
	 * 
	 * @see com.zs.pms.service.UserService#genMenu(java.util.List)
	 */
	@Override
	public List<TPermission> genMenu(List<TPermission> pers) {
		// 创建新容器，用来装一级菜单
		List<TPermission> list = new ArrayList<>();
		// 从原有箱子中遍历权限，寻找一级菜单
		for (TPermission per : pers) {
			// 判断 ， 如果顺序为1，那就是一级菜单
			if (per.getLev() == 1) {
				// 找出一级菜单了，继续从原有箱子中遍历权限，来寻找一级菜单下的二级菜单
				for (TPermission per2 : pers) {
					// 判断， 如果一级菜单的ID=二级菜单的上级ID，
					if (per2.getPid() == per.getId()) {
						// 那就吧找到的二级菜单添加到所属一级权限菜单中
						per.addChlid(per2);
					}
				}
				// 把一级菜单添加到新集合中
				list.add(per);
			}
		}
		return list;
	}

	/**
	 * 查询用户
	 * 
	 * @see com.zs.pms.service.UserService#queryByCon(com.zs.pms.vo.QueryUser)
	 */
	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return dao.queryByCon(query);
	}

	/**
	 * 删除
	 * 
	 * @see com.zs.pms.service.UserService#deleteUserById(int)
	 */
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		dao.deleteUserById(id);
	}

	/**
	 * 批量删除
	 * 
	 * @see com.zs.pms.service.UserService#deleteUserByIds(int[])
	 */
	@Override
	public void deleteUserByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteUserByIds(ids);
	}

	/**
	 * 修改
	 * 
	 * @see com.zs.pms.service.UserService#updateUser(com.zs.pms.po.TUser)
	 */
	@Override
	public void updateUser(TUser user) throws AppException {
		// TODO Auto-generated method stub
		// 模拟业务异常
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "登录名不能是admin");
		}
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));

		dao.updateUser(user);
	}

	/**
	 * 新增
	 * 
	 * @throws AppException
	 * @see com.zs.pms.service.UserService#insertUser(com.zs.pms.po.TUser)
	 */
	@Override
	// 该方法只要有异常就回滚事物
	@Transactional(rollbackFor = Exception.class)
	public int insertUser(TUser user) throws AppException {
		// TODO Auto-generated method stub
		// 模拟业务异常
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "登录名不能是admin");
		}
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));

		// 新增成功返回主键
		dao.insertUser(user);
		/*
		 * int a=1/0; dao.insertUser(user);
		 */
		return user.getId();
	}

	/**
	 * 获得分页记录 page：当前页 query ：查询条件
	 * 
	 * @see com.zs.pms.service.UserService#queryByPage(int, com.zs.pms.vo.QueryUser)
	 */
	@Override
	public List<TUser> queryByPage(int page, QueryUser query) {
		// TODO Auto-generated method stub
		// 通过当期那页数计算起始数和截止数
		// 起始数从1开始
		int start = (page - 1) * Constants.PAGECONT + 1;
		// 截止数
		int end = page * Constants.PAGECONT;
		query.setStart(start);
		query.setEnd(end);
		return dao.queryByPage(query);
	}

	/**
	 * 获得总页数
	 * 
	 * @see com.zs.pms.service.UserService#queryPageCount(com.zs.pms.vo.QueryUser)
	 */
	@Override
	public int queryPageCont(QueryUser query) {
		// TODO Auto-generated method stub
		// 通过总条数来计算总页数
		int cont = dao.queryCont(query);
		// 如果总条数能被常亮（每页条数）整除
		if (cont % Constants.PAGECONT == 0) {
			return cont / Constants.PAGECONT;
		} else {
			return cont / Constants.PAGECONT + 1;
		}

	}

	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}

}
