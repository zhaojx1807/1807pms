package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**�ļ��ϴ�������
 * @author Administrator
 *
 */
public class UploadController {
/**��ͨ�ļ��ϴ�
 * MultipartFile file �ϴ����ļ�
 * @return�ļ�·��
 */
@RequestMapping("/upload/common.do")
@ResponseBody
	public String commonUpload(MultipartFile file,HttpServletRequest req) {
	//UUID����IDMac��ַ��ʱ����������ɵ������ظ���32λ��
	//����UUID�㷨����ǰ׺
	String pfix=UUID.randomUUID().toString();
	//����ļ������κ������������������UUID�㷨�����ļ���
	//�����ļ��� ǰ׺+ԭʼ�ļ���
	String filename=pfix+file.getOriginalFilename();
	//���upload�ļ��е������ַ
	String path=req.getRealPath("/upload");
	//����Ŀ���ļ�
	File dfile=new File(path+File.separator+filename);
	try {
		//���ϴ����ļ�д�뵽Ŀ���ļ���
		file.transferTo(dfile);
		return "filename";
	} catch (IllegalStateException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	}
}
