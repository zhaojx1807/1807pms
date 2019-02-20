package com.zs.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

//S��Դ ��ת��������   
//T:target Ŀ������  ��Ҫת��������
//������  S T �����޸ĺ����ʵ�ֽӿ���ķ���
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setLenient(false);   
		try {
			
			return sf.parse(source);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
