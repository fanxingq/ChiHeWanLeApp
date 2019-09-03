package com.qust.chihewanleapp.entity;

/**
 * ”√ªß
 * @author zheng
 *
 */
public class User {

	public int userId;
	public String userame;
	public String userLoginPwd;
	public String userPayPwd;
	public String userTel;
	
	public User(int userId,String userame,String userLoginPwd,
			String userPayPwd,String userTel){
		this.userame=userame;
		this.userId=userId;
		this.userLoginPwd=userLoginPwd;
		this.userPayPwd=userPayPwd;
		this.userTel=userTel;
		
	}
	
	public String getUserame() {
		return userame;
	}
	public void setUserame(String userame) {
		this.userame = userame;
	}
	public String getUserLoginPwd() {
		return userLoginPwd;
	}
	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}
	public String getUserPayPwd() {
		return userPayPwd;
	}
	public void setUserPayPwd(String userPayPwd) {
		this.userPayPwd = userPayPwd;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userame=" + userame + ", userLoginPwd=" + userLoginPwd + ", userPayPwd="
				+ userPayPwd + ", userTel=" + userTel + "]";
	}
	
	
	
	
	
}
