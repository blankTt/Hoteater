package eater;

import java.util.List;


public interface ImplUserService {
	boolean insertUser(User u);  // 注册用户
	boolean updatePassword(String nickName, String password);  // 修改用户密码
	boolean updateScore(String nickName, int score);  // 修改用户得分
	List<User> selectAll();  // 查询所有用户
	User selectUser(String nickName, String password);     // 登录验证
	User selectUserByNick(String nickName); // 根据昵称查询用户
}