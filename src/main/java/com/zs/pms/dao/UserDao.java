package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {
	// �����û���id���Ȩ���б�
	public List<TPermission> queryByUid(int id);

//���ݵ�¼����ѯ
	public List<TUser> queryByCon(QueryUser query);

// ����������ѯһ����Ϣ ������ȡ
	public TUser queryById(int id);

//�û�������  �������ص�������id��������������Ϣ
	public int insertUser(TUser user);

//ɾ��һ������
	public void deleteUserById(int id);

//����ɾ��
	public void deleteUserByIds(int[] ids);

//�û��޸�
	public void updateUser(TUser user);

// ��ҳ��ѯ
	public List<TUser> queryByPage(QueryUser query);

// ���������
	public int queryCont(QueryUser query);

}
