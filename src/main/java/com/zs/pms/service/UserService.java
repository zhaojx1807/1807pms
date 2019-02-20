package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
//���Է���
	public void hello();

	/**
	 * �����û�ID��û��Ȩ���б�
	 * 
	 * @param id
	 * @return
	 */
	public List<TPermission> queryByUid(int id);

	/**
	 * ����ԭ��Ȩ������˵�,����һ�������˵�������
	 * 
	 * @param pers
	 * @return
	 */
	public List<TPermission> genMenu(List<TPermission> pers);

	/**
	 * ����������ѯ
	 * 
	 * @param query
	 * @return
	 */
	public List<TUser> queryByCon(QueryUser query);

	// ����ɾ��
	public void deleteUserByIds(int[] ids);

	// ɾ��һ��
	public void deleteUserById(int id);

	// �޸�
	public void updateUser(TUser user) throws AppException;

	// ����
	public int insertUser(TUser user) throws AppException;

	// ����������ѯһ����Ϣ
	public TUser queryById(int id);

	// ��ҳ��ѯ��¼
	public List<TUser> queryByPage(int page, QueryUser query);

	// ����������
	public int queryPageCont(QueryUser query);

}
