package eater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UserDao implements ImplUserDao {
    private String path = "./user.properties";
	@Override
	public List<User> readAll() {
		List<User> list = new ArrayList<User>();
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(path);
			isr = new InputStreamReader(is, "GB2312");
			br = new BufferedReader(isr);
			String context = "";
			try {
				while ((context = br.readLine()) != null) {
					if (context.length() == 0) {
						continue;
					}
					String[] messages = context.split(";");
					User e = new User(
							messages[0], 
							messages[1], 
							messages[2], 
							new SimpleDateFormat("yyyy-MM-dd").parse(messages[3]), 
							new SimpleDateFormat("yyyy-MM-dd").parse(messages[4]),  
							Integer.parseInt(messages[5]));
					list.add(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br!=null) {
					br.close();
				}
				if (isr!=null) {
					isr.close();
				}
				if (is!=null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean writeAll(List<User> list) {
		boolean flag = false;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			os = new FileOutputStream(path);
			osw = new OutputStreamWriter(os, "GB2312");
			bw = new BufferedWriter(osw);
			for (User u : list) {
				StringBuilder sb = new StringBuilder();
				sb.append(u.getUserId() + ";");
				sb.append(u.getNickName() + ";");
				sb.append(u.getPassword() + ";");
				sb.append(new SimpleDateFormat("yyyy-MM-dd")
						.format(u.getRegistTime()) + ";");
				sb.append(new SimpleDateFormat("yyyy-MM-dd")
						.format(u.getCreateShoreRecordTime()) + ";");
				sb.append(u.getShore());
				bw.write(sb.toString());
				bw.newLine();
			}
			bw.flush();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(osw != null) {
					osw.close();
				}
				if(os != null) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public String getNewID() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		char[] numbers = new char[]
				{'0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9', 'a', 'b', 
				'c', 'd', 'e', 'f', 'g', 'h', 
				'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < numbers.length; i++) {
			sb.append(numbers[r.nextInt(numbers.length)]);
		}
		return sb.toString();
	}

}
