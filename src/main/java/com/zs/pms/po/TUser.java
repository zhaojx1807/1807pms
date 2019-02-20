package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.zs.pms.utils.DateUtil;

import oracle.sql.DATE;

public class TUser implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -313144314121911927L;
	private int id;
	private String loginname;
	private String password;
	private String sex;
	private Date birthday;
	private String email;
	//private int dep;
	//关联部门
	private TDep dept;
	private String realname;
	private int creator;
	private Date createtime;
	private int updator;
	private Date updatetime;
	private String pic;
	private int isenabled;
	private String isenabledTxt;
	private String birthdayTxt;
	
	public String getIsenabledTxt() {
		if (isenabled==1) {
			
		return "可用";
		}else {
			return "不可用";	
		}
	}
	
	public String getBirthdayTxt() {
		return DateUtil.getStrDate();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TDep getDept() {
		return dept;
	}
	public void setDept(TDep dept) {
		this.dept = dept;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
	@Override
	public String toString() {
		return "TUser [id=" + id + ", loginname=" + loginname + ", password=" + password + ", sex=" + sex
				+ ", birthday=" + birthday + ", email=" + email + ", dept=" + dept + ", realname=" + realname
				+ ", creator=" + creator + ", createtime=" + createtime + ", updator=" + updator + ", updatetime="
				+ updatetime + ", pic=" + pic + ", isenabled=" + isenabled + "]";
	}

	
	
	
	
	
}
