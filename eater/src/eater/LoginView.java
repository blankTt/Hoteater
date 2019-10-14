package eater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class LoginView extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField nickNameField = null;
	private JPasswordField passwordField = null ;
    private JPanel jp ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LoginView().setVisible(true);
	}
	
	public LoginView() {
		UIManager.put("TextField.font", Tookit.getFont1()) ;
		UIManager.put("Label.font", Tookit.getFont1()) ;
		UIManager.put("Button.font", Tookit.getFont1()) ;
		this.init();
	}

	private void init() {
		JPanel buttom = new JPanel(new BorderLayout()) ;
		buttom.add(this.loginPanel()) ;
		this.add(buttom) ;
		this.setSize(340, 320) ;
		this.setLocationRelativeTo(null) ;
		this.setResizable(false) ;
		this.setIconImage(new ImageIcon("images/wsc_0_1.png").getImage()) ;
		this.setTitle("Hoteater") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 处理登录面板
	 * @return
	 */
	public JPanel loginPanel(){
		JPanel jPanel = new JPanel(new BorderLayout()) ;
		LoginPanel myPanel = new LoginPanel();
		myPanel.setLayout(null);
		final JLabel nickName = new JLabel("昵    称：", JLabel.CENTER);
		nickName.setBounds(60, 80, 65, 15);
		nickName.setFont(Tookit.getFont1()) ;
		myPanel.add(nickName) ;
		//加一个面板   可以 存入多个JLabel
		jp = new JPanel() ;
		jp.setOpaque(false) ;
		jp.setBounds(130, 100, 140, 200);
		myPanel.add(jp);
		nickNameField = new JTextField(12) ;
		nickNameField.setBounds(130, 80, 140, 21) ;
		myPanel.add(nickNameField);
		
		JLabel password = new JLabel("密    码：", JLabel.CENTER) ;
		password.setFont(Tookit.getFont1()) ;
		password.setBounds(60, 140, 65, 15) ;
		passwordField = new JPasswordField(12) ;
		passwordField.setBounds(130, 140, 140, 21) ;
		myPanel.add(password) ;
		myPanel.add(passwordField);

		JButton login = new JButton("登录");
		login.setBounds(33, 180, 81, 30) ;
		myPanel.add(login) ;
		login.addActionListener(new ActionListener() {
			
			// 登录逻辑处理
			@Override
			public void actionPerformed(ActionEvent e) {
				// 文本框中昵称的值
				String nickName = nickNameField.getText().trim();
				// 文本框中密码的值
				String password = String.valueOf(passwordField.getPassword()).trim();
				
				if (nickName.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "请输入昵称！");
				    return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "请输入密码！");
				    return;
				}
				
				// 进行登录验证逻辑判断
				ImplUserService service = new UserService();
				User login = service.selectUser(nickName, password);
				if (login != null) { //验证成功
					JOptionPane.showMessageDialog(LoginView.this, "欢迎" + login.getNickName() + "进入游戏！鼠标点击任意处即可开始！");
					new GameView(login).setVisible(true);
					LoginView.this.dispose();  // 关闭当前窗口
				} else {  //验证不成功
					JOptionPane.showMessageDialog(LoginView.this, "用户名或密码输入错误！") ;
				    return;
				}
				
			}
		});
		login.setPreferredSize(new Dimension(80, 30));
		login.setBackground(new Color(0x71B8EC));
		
		JButton regist = new JButton("注册");
		regist.setBounds(125, 180, 81, 30);
		myPanel.add(regist);
		regist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegistView().setVisible(true);
				LoginView.this.dispose();  
			}
		});
		regist.setPreferredSize(new Dimension(80, 30));
		regist.setBackground(new Color(46, 116, 188));
		
		JButton updatePass = new JButton("找回密码");
		updatePass.setBounds(217, 180, 90, 30);
		myPanel.add(updatePass);
		updatePass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdatePassView().setVisible(true);
				LoginView.this.dispose();  
			}
		});
		updatePass.setPreferredSize(new Dimension(80, 30));
		updatePass.setBackground(new Color(90, 177, 234));
		
		JButton scores = new JButton("查看排行榜");
		scores.setBounds(110, 220, 120, 30) ;
		myPanel.add(scores);
		scores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ScoreListView().setVisible(true);
				LoginView.this.dispose();  
			}
		});
		scores.setPreferredSize(new Dimension(80, 30));
		scores.setBackground(new Color(127, 197, 245));
		
		jPanel.add(myPanel) ;
		return jPanel;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		nickNameField.setText(((JLabel)e.getSource()).getText()) ;
		//设置面板属性	
		jp.removeAll() ;
		this.remove(jp) ;
		this.repaint() ;
		this.validate() ;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jLabel = (JLabel)e.getSource() ;
		jLabel.setForeground(Tookit.getColor()) ;
		jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jLabel = (JLabel)e.getSource() ;
		jLabel.setForeground(Color.black) ;
		jLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)) ;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
