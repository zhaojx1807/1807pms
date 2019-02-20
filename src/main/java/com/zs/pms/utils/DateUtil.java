package com.zs.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author Administrator
 *
 */
public class DateUtil {
	/**
	 * 获得当前时间的方法（字符串）
	 * @return 返回当前时间字符串
	 */
	public static String getStrDate() {
		//获得日历对象  Calendar静态方法可直接调用
		Calendar cal=Calendar.getInstance();
		//获得当前的年
		int year=cal.get(Calendar.YEAR);
		//获得当前的月(月份从0开始)
		int month=cal.get(Calendar.MONTH)+1;
		//获得当前的日
		int date=cal.get(Calendar.DAY_OF_MONTH);
		//获得当前的小时(24小时)
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		/*//获得当前的分
		int minute=cal.get(Calendar.MINUTE);
		//获得当前的秒
		int second=cal.get(Calendar.SECOND);*/
		
		//通过获得当前小时来判断时上午，中午，还是下午
		String str="";
		if(hour>=6&&hour<=11) {
			str="上午好";
		}else if(hour>11&&hour<=14){
			str="中午好";
		}else if(hour>14&&hour<=18){
			str="下午好";
		}else if(hour>18&&hour<24){
			str="晚上好";
		}else {
			str="玩什么玩，快去睡";
		}
		//System.out.println(year+"-"+month+"-"+date+" "+hour);
		return year+"-"+month+"-"+date+"  "+str;
		
		
		
	}
	
	
	/**
	 * 字符串转date的方法
	 * @param time 字符串
	 * @param parrten 输入字符串的时间格式
	 * return date的时间
	 * @throws ParseException
	 */
	public static Date getStrToDate(String time,String parrten) throws ParseException {
		
		//获得格式化对象
		SimpleDateFormat sdf= new SimpleDateFormat(parrten);
		//返回格式化好的时间
		return sdf.parse(time);
		
	}
	
	/**
	 * Date转String时间的方法
	 * @param time Date类的时间
	 * @param parrten 把时间格式化成什么样
	 * @return 字符串类的时间
	 */
	public static String getDateToStr(Date time,String parrten) {
		//获得格式化对象
		SimpleDateFormat sdf= new SimpleDateFormat(parrten);
		//返回格式化好的时间
		return sdf.format(time);
		
	}
	
	/*public static void main(String[] args) {
		System.out.println(DateUtil.getStrDate());
		try {
			System.out.println(DateUtil.getStrToDate(getStrDate(), "yyyy-MM-dd hh:mm:ss"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	Date time=new Date();
		System.out.println(DateUtil.getDateToStr(time, "yyyy-MM-dd hh:mm:ss"));
		
	}*/
		
	
	
	
	
	
	
	
	
}