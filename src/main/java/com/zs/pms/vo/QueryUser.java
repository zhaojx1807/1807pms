package com.zs.pms.vo;

public class QueryUser {
	//用户名
private String loginname;
//密码
private String password;
//性别
private String sex;
//是否可用
private int isenabled;
//用户名起始数
private int start;
//截止数
private int end;



public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public int getEnd() {
	return end;
}
public void setEnd(int end) {
	this.end = end;
}

public int getIsenabled() {
	return isenabled;
}
public void setIsenabled(int isenabled) {
	this.isenabled = isenabled;
}
public String getLoginname() {
	return loginname;
}
public void setLoginname(String loginname) {
	this.loginname = loginname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
@Override
public String toString() {
	return "QueryUser [loginname=" + loginname + ", password=" + password + ", sex=" + sex + ", isenabled=" + isenabled
			+ ", start=" + start + ", end=" + end + "]";
}

}
