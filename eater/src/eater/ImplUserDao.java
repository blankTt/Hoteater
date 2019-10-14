package eater;

import java.util.List;

public interface ImplUserDao {
	 List<User> readAll();    // 读取所有的信息
	    boolean writeAll(List<User> list);   // 将信息写回
	    String getNewID();   // 返回最新主键数据
}
