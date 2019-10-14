package eater;

import java.util.Date;
import java.util.List;



public class UserService implements ImplUserService {

	private ImplUserDao dao = new UserDao();
	@Override
	public boolean insertUser(User u) {
		List<User> list = dao.readAll();
		for (User user : list) {
			if (user.getNickName().equals(u.getNickName())) {
				return false;
			}
		}
		u.setUserId(dao.getNewID());
		u.setRegistTime(new Date());
		u.setCreateShoreRecordTime(new Date());
		u.setShore(0);
		list.add(u);
		return dao.writeAll(list);
	}

	@Override
	public boolean updatePassword(String nickName, String password) {
		List<User> list = dao.readAll();
		User user = selectUserByNick(nickName);
		if (user == null) {
			return false;
		} else {
			for (User u : list) {
				if (u.getNickName().equals(nickName)) {
					u.setPassword(password);
					break;
				}
			}
		}
		return dao.writeAll(list);
	}

	@Override
	public List<User> selectAll() {
		return dao.readAll();
	}

	@Override
	public boolean updateScore(String nickName, int score) {
		List<User> list = dao.readAll();
		for (User u : list) {
			if (u.getNickName().equals(nickName)) {
				u.setShore(score);
				u.setCreateShoreRecordTime(new Date());
	         	break;
			}
		}
		return dao.writeAll(list);
	}

	@Override
	public User selectUser(String nickName, String password) {
		User loginUser = null;
		List<User> list = dao.readAll();
		for (User user : list) {
			if (user.getNickName().equals(nickName) 
					&& user.getPassword().equals(password)) {
				loginUser = user;
				break;
			}
		}
		return loginUser;
	}

	@Override
	public User selectUserByNick(String nickName) {
		User user = null;
		List<User> list = dao.readAll();
		for (User u : list) {
			if (u.getNickName().equals(nickName)) {
				user = u;
				break;
			}
		}
		return user;
	}

}
