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
	 * �����¼���
	 * @return
	 */
	public JPanel loginPanel(){
		JPanel jPanel = new JPanel(new BorderLayout()) ;
		LoginPanel myPanel = new LoginPanel();
		myPanel.setLayout(null);
		final JLabel nickName = new JLabel("��    �ƣ�", JLabel.CENTER);
		nickName.setBounds(60, 80, 65, 15);
		nickName.setFont(Tookit.getFont1()) ;
		myPanel.add(nickName) ;
		//��һ�����   ���� ������JLabel
		jp = new JPanel() ;
		jp.setOpaque(false) ;
		jp.setBounds(130, 100, 140, 200);
		myPanel.add(jp);
		nickNameField = new JTextField(12) ;
		nickNameField.setBounds(130, 80, 140, 21) ;
		myPanel.add(nickNameField);
		
		JLabel password = new JLabel("��    �룺", JLabel.CENTER) ;
		password.setFont(Tookit.getFont1()) ;
		password.setBounds(60, 140, 65, 15) ;
		passwordField = new JPasswordField(12) ;
		passwordField.setBounds(130, 140, 140, 21) ;
		myPanel.add(password) ;
		myPanel.add(passwordField);

		JButton login = new JButton("��¼");
		login.setBounds(33, 180, 81, 30) ;
		myPanel.add(login) ;
		login.addActionListener(new ActionListener() {
			
			// ��¼�߼�����
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ı������ǳƵ�ֵ
				String nickName = nickNameField.getText().trim();
				// �ı����������ֵ
				String password = String.valueOf(passwordField.getPassword()).trim();
				
				if (nickName.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "�������ǳƣ�");
				    return;
				}
				if (password.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this, "���������룡");
				    return;
				}
				
				// ���е�¼��֤�߼��ж�
				ImplUserService service = new UserService();
				User login = service.selectUser(nickName, password);
				if (login != null) { //��֤�ɹ�
					JOptionPane.showMessageDialog(LoginView.this, "��ӭ" + login.getNickName() + "������Ϸ����������⴦���ɿ�ʼ��");
					new GameView(login).setVisible(true);
					LoginView.this.dispose();  // �رյ�ǰ����
				} else {  //��֤���ɹ�
					JOptionPane.showMessageDialog(LoginView.this, "�û����������������") ;
				    return;
				}
				
			}
		});
		login.setPreferredSize(new Dimension(80, 30));
		login.setBackground(new Color(0x71B8EC));
		
		JButton regist = new JButton("ע��");
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
		
		JButton updatePass = new JButton("�һ�����");
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
		
		JButton scores = new JButton("�鿴���а�");
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
		//�����������	
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
