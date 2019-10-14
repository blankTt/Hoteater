package eater;

import java.util.List;


public interface ImplUserService {
	boolean insertUser(User u);  // ע���û�
	boolean updatePassword(String nickName, String password);  // �޸��û�����
	boolean updateScore(String nickName, int score);  // �޸��û��÷�
	List<User> selectAll();  // ��ѯ�����û�
	User selectUser(String nickName, String password);     // ��¼��֤
	User selectUserByNick(String nickName); // �����ǳƲ�ѯ�û�
}