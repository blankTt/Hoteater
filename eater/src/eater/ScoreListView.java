package eater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreListView extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private List<User> list;
	private JPanel jp;

	public ScoreListView() {
		this.init();
	}

	private void init() {
		// TODO Auto-generated method stub
		JPanel buttom = new JPanel(new BorderLayout());
		buttom.add(this.ScoreListPanel());
		this.add(buttom);
		this.setSize(464, 720);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("images/wsc_0_1.png").getImage());
		this.setTitle("Hoteater");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 处理排行榜面板
	 * 
	 * @return
	 */
	public JPanel ScoreListPanel() {
		JPanel jPanel = new JPanel(new BorderLayout());

		ScoreListPanel listPanel = new ScoreListPanel();
		listPanel.setLayout(null);

		JLabel title = new JLabel("本周吃豆人排行榜", JLabel.CENTER);
		title.setBounds(42, 120, 380, 40);
		title.setFont(new Font("黑体", Font.BOLD, 40));
		listPanel.add(title);

		// 加一个面板 可以存入多个jlabel
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(0, 0, 500, 800);
		listPanel.add(jp);

		JLabel nick = new JLabel("玩    家", JLabel.CENTER);
		nick.setFont(Tookit.getFont5());
		nick.setBounds(80, 180, 80, 30);
		listPanel.add(nick);

		JLabel score = new JLabel("分    数", JLabel.CENTER);
		score.setFont(Tookit.getFont5());
		score.setBounds(304, 180, 80, 30);
		listPanel.add(score);

		ImplUserService service = new UserService();
		list = service.selectAll();
		Collections.sort(list);
		
		// 删除一周之外的得分纪录
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCreateShoreRecordTime().getTime() < System
					.currentTimeMillis() - 7 * 24 * 3600000) {
                list.remove(i);
			}
		}
		
		// 遍历前十条记录
		for (int i = 0; i < list.size(); i++) {
			if (i < 10) {
				JLabel nickName = new JLabel(list.get(i).getNickName(),
							JLabel.CENTER);
				nickName.setFont(Tookit.getFont4());
				nickName.setBounds(80, 180 + (i + 1) * 40, 80, 30);
				listPanel.add(nickName);

				JLabel userScore = new JLabel(list.get(i).getShore()
							.toString(), JLabel.CENTER);
				userScore.setFont(Tookit.getFont4());
				userScore.setBounds(304, 180 + (i + 1) * 40, 80, 30);
				listPanel.add(userScore);
			}
		}

		JButton reverse = new JButton("返回");
		reverse.setBounds(197, 630, 81, 30);
		listPanel.add(reverse);
		reverse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginView().setVisible(true);
				ScoreListView.this.dispose();
			}
		});
		reverse.setPreferredSize(new Dimension(80, 30));
		reverse.setBackground(new Color(0xFFFFFF));

		jPanel.add(listPanel);
		return jPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

