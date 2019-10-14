package eater;
//游戏窗口
/**
 * @author Blank_Tt
 * @author JAVA Team
 * 任务
 * 1、图片更换
 * 2、stop，start，level等功能的实现
 * 3、增加背景音乐,吃热狗的音乐也添加一下吧
 * 4、增加主界面 重新画一个，还是网上找？
 * 5、 emmmm。。再增加鼠标停止和statrt吧 = =
 * 6、..
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
	public static final int WIDTH = 464; // 窗口的宽
	public static final int HEIGHT = 720; // 窗口的高
	
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
	private static User login = null;  // 当前登录用户
	
	public GameView (User login) {
		super(null);
		this.login = login;
		this.init();
		JFrame window = new JFrame("Hoteater");
		window.setIconImage(new ImageIcon("images/wsc_0_1.png").getImage()) ;
		Container container = window .getContentPane();
		container.add(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack(); //设置size
		window.setVisible(true);
		window.setLocation(500,160);
	}
	public void initilalizeEater() {
		ImageIcon [] eaterIcons = new ImageIcon[EATER_NAMES.length];
		for (int i=0; i<eaterIcons.length;i++){
			eaterIcons[i] = new ImageIcon("images/" + EATER_NAMES[i]);
		eater = new Animation (eaterIcons, EATER, EAST); //朝东
		
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
