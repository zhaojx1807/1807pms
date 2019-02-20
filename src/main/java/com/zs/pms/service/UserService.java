package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
//测试方法
	public void hello();

	/**
	 * 根据用户ID获得获得权限列表
	 * 
	 * @param id
	 * @return
	 */
	public List<TPermission> queryByUid(int id);

	/**
	 * 根据原有权限整理菜单,根据一级二级菜单整理划分
	 * 
	 * @param pers
	 * @return
	 */
	public List<TPermission> genMenu(List<TPermission> pers);

	/**
	 * 按照条件查询
	 * 
	 * @param query
	 * @return
	 */
	public List<TUser> queryByCon(QueryUser query);

	// 批量删除
	public void deleteUserByIds(int[] ids);

	// 删除一条
	public void deleteUserById(int id);

	// 修改
	public void updateUser(TUser user) throws AppException;

	// 新增
	public int insertUser(TUser user) throws AppException;

	// 根据主键查询一条信息
	public TUser queryById(int id);

	// 分页查询记录
	public List<TUser> queryByPage(int page, QueryUser query);

	// 计算总条数
	public int queryPageCont(QueryUser query);

}
