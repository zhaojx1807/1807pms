package com.zs.pms.vo;
//装在登录验证数据
public class QueryLogin {
private String username;
private String password;
private String chkcode;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getChkcode() {
	return chkcode;
}
public void setChkcode(String chkcode) {
	this.chkcode = chkcode;
}
@Override
public String toString() {
	return "QueryLogin [username=" + username + ", password=" + password + ", chkcode=" + chkcode + "]";
}


}
