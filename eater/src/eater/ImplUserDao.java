package eater;

import java.util.List;

public interface ImplUserDao {
	 List<User> readAll();    // ��ȡ���е���Ϣ
	    boolean writeAll(List<User> list);   // ����Ϣд��
	    String getNewID();   // ����������������
}
