package eater;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable, Comparable<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId; // �û����
	private String nickName; // �û��ǳ�
	private String password; // �û�����
	private Date registTime; // �û�ע��ʱ��
	private Date createShoreRecordTime; // �û���߷ּ�¼����ʱ��
	private Integer shore; // �û��÷�

	@Override
	public String toString() {
		return "\n�û���Ϣ�� [�û����=" + userId + ", " + "�û��ǳ�=" + nickName + ", " + "����=" + password + "\n, " + "ע��ʱ��="
				+ registTime + ", " + "����ʱ��=" + createShoreRecordTime + ", " + "�÷�=" + shore + "]";
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

