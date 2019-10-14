package eater;
//��Ϸ����
/**
 * @author Blank_Tt
 * @author JAVA Team
 * ����
 * 1��ͼƬ����
 * 2��stop��start��level�ȹ��ܵ�ʵ��
 * 3�����ӱ�������,���ȹ�������Ҳ���һ�°�
 * 4������������ ���»�һ�������������ң�
 * 5�� emmmm�������������ֹͣ��statrt�� = =
 * 6��..
 * 7 .....
 * 8 .......
 * .....
 * ....
 */
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameView extends JPanel implements GameConstants {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 464; // ���ڵĿ�
	public static final int HEIGHT = 720; // ���ڵĸ�
	
	private final String[] EATER_NAMES={"wsc_0_1.png", "wsc_0_3.png", "wsc_0_3.png",
										"wsc_0_4.png", "wsc_3_6.png", "wsc_3_6.png",
										"wsc_1_1.png", "wsc_1_3.png", "wsc_1_3.png",
										"wsc_1_6.png", "wsc_1_6.png", "wsc_1_6.png",
										"wsc_2_1.png", "wsc_2_3.png", "wsc_2_3.png",
										"wsc_1_6.png", "wsc_2_6.png", "wsc_2_6.png",
										"wsc_3_1.png", "wsc_3_3.png", "wsc_3_3.png",
									    "wsc_3_4.png", "wsc_3_5.png", "wsc_3_6.png",
									    "wsc_dead.png"};
	
	private final String[] ENEMY_NAMES={"wjl_l.png", "wjl_r.png", "wjl.png",
										"wjl_up.png"};
	private Animation eater;   
	private Animation [] enemies;
	private Background bg;   
	private GameController control; 
	private static User login = null;  // ��ǰ��¼�û�
	
	public GameView (User login) {
		super(null);
		this.login = login;
		this.init();
		JFrame window = new JFrame("Hoteater");
		window.setIconImage(new ImageIcon("images/wsc_0_1.png").getImage()) ;
		Container container = window .getContentPane();
		container.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack(); //����size
		window.setVisible(true);
		window.setLocation(500,160);
	}
	public void initilalizeEater() {
		ImageIcon [] eaterIcons = new ImageIcon[EATER_NAMES.length];
		for (int i=0; i<eaterIcons.length;i++){
			eaterIcons[i] = new ImageIcon("images/" + EATER_NAMES[i]);
		eater = new Animation (eaterIcons, EATER, EAST); //����
		
		}
	}
	public void initializeEnemies(){
		enemies = new Animation [8];
		ImageIcon [] enemyIcons = new ImageIcon [ENEMY_NAMES.length];
				
		for (int i=0; i<enemyIcons.length; i++) {
				enemyIcons [i] = new ImageIcon ("images/" + ENEMY_NAMES[i]);
		}
		
		for (int i=0; i<enemies.length; i++) {
			enemies[i] = new Animation (enemyIcons, ENEMY, SOUTH);
		}
	} 
	public void init() {
		initilalizeEater();
		initializeEnemies();
		bg = new Background(login,eater,enemies);
		add(bg);
		setSize(new Dimension (bg.getWidth(), bg.getHeight()));
		setPreferredSize(getSize());
	}
}
