package com.zs.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zs.pms.po.TDep;
import com.zs.pms.service.DepService;

@Controller
public class DepController {
	@Autowired
	DepService ds;
	/**ajax��ö�������
	 * 
	 * �������صĶ�������ļ���
	 * �Զ���json����ʽд��respinse��
	 * @param pid
	 * @return
	 */
	public List<TDep> gerDeps(int pid) {
		return ds.queryByPid(pid);
		
	}
}
