package com.zs.pms.vo;

public class QueryUser {
	//�û���
private String loginname;
//����
private String password;
//�Ա�
private String sex;
//�Ƿ����
private int isenabled;
//�û�����ʼ��
private int start;
//��ֹ��
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
