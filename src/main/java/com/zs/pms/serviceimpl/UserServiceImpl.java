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
@Transactional // ��ʼ�����ҵ�����
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	@Override
	public void hello() {

		System.out.println("hello Spring���Գɹ�");
	}

	@Override
	public List<TPermission> queryByUid(int id) {

		return dao.queryByUid(id);
	}

	/**
	 * ͨ��id����Ȩ���б�˵�
	 * 
	 * @see com.zs.pms.service.UserService#genMenu(java.util.List)
	 */
	@Override
	public List<TPermission> genMenu(List<TPermission> pers) {
		// ����������������װһ���˵�
		List<TPermission> list = new ArrayList<>();
		// ��ԭ�������б���Ȩ�ޣ�Ѱ��һ���˵�
		for (TPermission per : pers) {
			// �ж� �� ���˳��Ϊ1���Ǿ���һ���˵�
			if (per.getLev() == 1) {
				// �ҳ�һ���˵��ˣ�������ԭ�������б���Ȩ�ޣ���Ѱ��һ���˵��µĶ����˵�
				for (TPermission per2 : pers) {
					// �жϣ� ���һ���˵���ID=�����˵����ϼ�ID��
					if (per2.getPid() == per.getId()) {
						// �ǾͰ��ҵ��Ķ����˵���ӵ�����һ��Ȩ�޲˵���
						per.addChlid(per2);
					}
				}
				// ��һ���˵���ӵ��¼�����
				list.add(per);
			}
		}
		return list;
	}

	/**
	 * ��ѯ�û�
	 * 
	 * @see com.zs.pms.service.UserService#queryByCon(com.zs.pms.vo.QueryUser)
	 */
	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return dao.queryByCon(query);
	}

	/**
	 * ɾ��
	 * 
	 * @see com.zs.pms.service.UserService#deleteUserById(int)
	 */
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		dao.deleteUserById(id);
	}

	/**
	 * ����ɾ��
	 * 
	 * @see com.zs.pms.service.UserService#deleteUserByIds(int[])
	 */
	@Override
	public void deleteUserByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteUserByIds(ids);
	}

	/**
	 * �޸�
	 * 
	 * @see com.zs.pms.service.UserService#updateUser(com.zs.pms.po.TUser)
	 */
	@Override
	public void updateUser(TUser user) throws AppException {
		// TODO Auto-generated method stub
		// ģ��ҵ���쳣
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "��¼��������admin");
		}
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));

		dao.updateUser(user);
	}

	/**
	 * ����
	 * 
	 * @throws AppException
	 * @see com.zs.pms.service.UserService#insertUser(com.zs.pms.po.TUser)
	 */
	@Override
	// �÷���ֻҪ���쳣�ͻع�����
	@Transactional(rollbackFor = Exception.class)
	public int insertUser(TUser user) throws AppException {
		// TODO Auto-generated method stub
		// ģ��ҵ���쳣
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1003, "��¼��������admin");
		}
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(user.getPassword()));

		// �����ɹ���������
		dao.insertUser(user);
		/*
		 * int a=1/0; dao.insertUser(user);
		 */
		return user.getId();
	}

	/**
	 * ��÷�ҳ��¼ page����ǰҳ query ����ѯ����
	 * 
	 * @see com.zs.pms.service.UserService#queryByPage(int, com.zs.pms.vo.QueryUser)
	 */
	@Override
	public List<TUser> queryByPage(int page, QueryUser query) {
		// TODO Auto-generated method stub
		// ͨ��������ҳ��������ʼ���ͽ�ֹ��
		// ��ʼ����1��ʼ
		int start = (page - 1) * Constants.PAGECONT + 1;
		// ��ֹ��
		int end = page * Constants.PAGECONT;
		query.setStart(start);
		query.setEnd(end);
		return dao.queryByPage(query);
	}

	/**
	 * �����ҳ��
	 * 
	 * @see com.zs.pms.service.UserService#queryPageCount(com.zs.pms.vo.QueryUser)
	 */
	@Override
	public int queryPageCont(QueryUser query) {
		// TODO Auto-generated method stub
		// ͨ����������������ҳ��
		int cont = dao.queryCont(query);
		// ����������ܱ�������ÿҳ����������
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
