package eater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;



public class UpdatePassView extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField nickNameField = null;
	private JPasswordField passwordField = null;
	private JPasswordField confirmPasswordField = null;
    private JPanel jp;
	
	public UpdatePassView() {
		UIManager.put("TextField.font", Tookit.getFont1()) ;
		UIManager.put("Label.font", Tookit.getFont1()) ;
		UIManager.put("Button.font", Tookit.getFont1()) ;
		this.init();
	}

	private void init() {
		JPanel buttom = new JPanel(new BorderLayout()) ;
		buttom.add(this.UpdatePassPanel()) ;
		this.add(buttom);
		this.setSize(340, 320) ;
		this.setLocationRelativeTo(null) ;
		this.setResizable(false) ;
		this.setIconImage(new ImageIcon("images/wsc_0_1.png").getImage()) ;
		this.setTitle("Hoteater") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
	}
	/**
	 * �����޸��������
	 * @return
	 */
	public JPanel UpdatePassPanel(){
		JPanel jPanel = new JPanel(new BorderLayout()) ;
		UpdatePassPanel myPanel = new UpdatePassPanel();
		myPanel.setLayout(null);
		final JLabel nickName = new JLabel("��    �ƣ�", JLabel.CENTER);
		nickName.setBounds(39, 60, 65, 15);
		nickName.setFont(Tookit.getFont1());
		myPanel.add(nickName);
		
		//��һ�����   ���� ������jlabel
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(130, 80, 140, 200);
		myPanel.add(jp) ;
		nickNameField = new JTextField(12);
		nickNameField.setBounds(130, 60, 140, 21);
		myPanel.add(nickNameField);
		
		JLabel password = new JLabel("��    �룺", JLabel.CENTER);
		password.setFont(Tookit.getFont1());
		password.setBounds(39, 110, 65, 15);
		passwordField = new JPasswordField(12);
		passwordField.setBounds(130, 110, 140, 21);
		myPanel.add(password);
		myPanel.add(passwordField);
		
		JLabel confrimPassword = new JLabel("ȷ�����룺", JLabel.CENTER);
		confrimPassword.setFont(Tookit.getFont1());
		confrimPassword.setBounds(30, 160, 85, 15);
		confirmPasswordField = new JPasswordField(12);
		confirmPasswordField.setBounds(130, 160, 140, 21);
		myPanel.add(confrimPassword);
		myPanel.add(confirmPasswordField);

		JButton updPass = new JButton("ȷ��");
		updPass.setBounds(38, 190, 81, 30);
		myPanel.add(updPass) ;
		updPass.addActionListener(new ActionListener() {
			
			// �߼�����
						@Override
						public void actionPerformed(ActionEvent e) {
							// �ı������ǳƵ�ֵ
							String nickName = nickNameField.getText().trim();
							// �ı����������ֵ
							String password = String.valueOf(passwordField.getPassword()).trim();
							// �ı�����ȷ�������ֵ
							String confirmPassword = String.valueOf(confirmPasswordField.getPassword()).trim();
							if (nickName.equals("")) {
								JOptionPane.showMessageDialog(UpdatePassView.this, "�������ǳƣ�");
							    return;
							}
							if (password.equals("")) {
								JOptionPane.showMessageDialog(UpdatePassView.this, "���������룡");
								return;
							}
							if (confirmPassword.equals("")) {
								JOptionPane.showMessageDialog(UpdatePassView.this, "������ȷ�����룡");
								return;
							}
							if (!(password.equals(confirmPassword))) {
								JOptionPane.showMessageDialog(UpdatePassView.this, "�������������ͬ��");
								return;
							}
							
							// ��֤�߼��ж�
							ImplUserService service = new UserService();
							boolean success = 
									service.updatePassword(nickName, password);
							if (success) {
								JOptionPane.showConfirmDialog(null, "�����޸ĳɹ������μǣ�");
								new LoginView().setVisible(true);
								UpdatePassView.this.dispose();
							} else {
								JOptionPane.showMessageDialog(UpdatePassView.this, "���ǳƲ����ڣ�");
							    return;
							}
						}
					});
					updPass.setPreferredSize(new Dimension(80, 30));
					updPass.setBackground(new Color(0x71B8EC));
					
					JButton reset = new JButton("����");
					reset.setBounds(130, 190, 81, 30);
					myPanel.add(reset);
					reset.addActionListener(new ActionListener() {
						
						// �����ı������ÿ�
						@Override
						public void actionPerformed(ActionEvent e) {
							nickNameField.setText("") ;
							passwordField.setText("") ;
							confirmPasswordField.setText("") ;
						}
					});
					
					reset.setPreferredSize(new Dimension(80, 30));
					reset.setBackground(new Color(75, 158, 217));
					
					JButton reverse = new JButton("����");
					reverse.setBounds(222, 190, 81, 30) ;
					myPanel.add(reverse) ;
					reverse.addActionListener(new ActionListener() {
						
						// ��ת����¼ҳ�棬�رոô���
						@Override
						public void actionPerformed(ActionEvent e) {
							new LoginView().setVisible(true);
							UpdatePassView.this.dispose();
						}
					});
					
					reverse.setPreferredSize(new Dimension(80, 30));
					reverse.setBackground(new Color(90, 177, 234));
					
					jPanel.add(myPanel);
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