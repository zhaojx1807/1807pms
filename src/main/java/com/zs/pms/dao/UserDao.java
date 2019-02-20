package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {
	// 根据用户的id获得权限列表
	public List<TPermission> queryByUid(int id);

//根据登录名查询
	public List<TUser> queryByCon(QueryUser query);

// 根据主键查询一条信息 主键读取
	public TUser queryById(int id);

//用户新增：  新增返回的是主键id，不反悔整条信息
	public int insertUser(TUser user);

//删除一条数据
	public void deleteUserById(int id);

//批量删除
	public void deleteUserByIds(int[] ids);

//用户修改
	public void updateUser(TUser user);

// 分页查询
	public List<TUser> queryByPage(QueryUser query);

// 获得总条数
	public int queryCont(QueryUser query);

}
