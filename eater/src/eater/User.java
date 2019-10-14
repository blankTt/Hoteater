package eater;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable, Comparable<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId; // 用户编号
	private String nickName; // 用户昵称
	private String password; // 用户密码
	private Date registTime; // 用户注册时间
	private Date createShoreRecordTime; // 用户最高分纪录创建时间
	private Integer shore; // 用户得分

	@Override
	public String toString() {
		return "\n用户信息： [用户编号=" + userId + ", " + "用户昵称=" + nickName + ", " + "密码=" + password + "\n, " + "注册时间="
				+ registTime + ", " + "创建时间=" + createShoreRecordTime + ", " + "得分=" + shore + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String nickName, String password, Date registTime, Date createShoreRecordTime,
			Integer shore) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.registTime = registTime;
		this.createShoreRecordTime = createShoreRecordTime;
		this.shore = shore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getCreateShoreRecordTime() {
		return createShoreRecordTime;
	}

	public void setCreateShoreRecordTime(Date createShoreRecordTime) {
		this.createShoreRecordTime = createShoreRecordTime;
	}

	public Integer getShore() {
		return shore;
	}

	public void setShore(Integer shore) {
		this.shore = shore;
	}

	@Override
	public int compareTo(User u) {
		if (u.shore > this.shore) {
			return 1;
		} else if (u.shore == this.shore) {
			return 0;
		} else {
			return -1;
		}
	}

}

