package eater;
import javax.swing.*;
import javax.swing.Timer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * 
 * 音乐 背景音乐，游戏胜利，游戏失败，吃热狗，吃到奖励，吃到炸弹
 */
public class Background extends JPanel implements GameConstants,KeyListener, ActionListener {
	
	//存储地图
	private int [][] vState = { {0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0},
								{0,0,1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,1,0,0},
								{0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,0,0,0},
								{0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,0,0,1,1,0},
								{0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0},
								{0,0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0},
								{0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,0},
								{0,1,0,0,0,1,0,1,1,1,1,1,1,1,1,0,1,1,0,0},
								{0,1,1,1,1,0,0,0,1,0,0,0,1,1,1,0,0,1,0,0},
								{0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0},
								{0,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,0},
								{0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1,1,0,1,0},
								{0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,1,1,1,1,0},
								{0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0}
								
							};

	private int [][] hState = { {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,1,0,0,1,0,0,0,1,1,1,0,0,0,1,0,1,1},
								{1,0,0,1,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1},
								{0,0,0,0,0,0,0,1,0,1,0,0,0,1,1,1,0,1,1},
								{1,0,1,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1},
								{1,1,1,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1},
								{1,1,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1},
								{1,0,1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,1},
								{0,1,1,1,1,1,1,0,0,1,1,0,0,0,1,1,0,1,1},
								{1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1},
								{1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
								{1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,1,1,0},
								{1,1,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,0,1},
								{1,1,0,0,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1},
								{1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
							};


	private int gateWidth = 6;
	private int pathWidth = 40;
	private int cellWidth = gateWidth+pathWidth;
	private int score = -1;
	private final int DELAY = 30;
	private static User login = null;  // 当前登录用户
	private Gate [][] vGates = new Gate [vState.length][vState[0].length];
	private Gate [][] hGates = new Gate [hState.length][hState[0].length];
	private Animation eater;
	private Animation [] enemies; 
	private GameController control;
	private Dot dots  [][] = new Dot [19][15];
	private boolean index[][] = new boolean[19][15]; //放置
	private boolean index2[][] = new boolean[19][15];//记录放置完了没
	private int numOfHotDog = 0;  //判断胜利条件 热狗数量
	private int indexOfHotDog = 0;
	private int [] eaterEastUpSequence 		= {0, 1, 2};
	private int [] eaterEastDownSequence	= {3, 4, 5};
	private int [] eaterWestUpSequence		= {6, 7, 8};
	private int [] eaterWestDownSequence	= {9, 10, 11};
	private int [] eaterNorthUpSequence		= {12, 13, 14};
	private int [] eaterNorthDownSequence	= {15, 16, 17};
	private int [] eaterSouthUpSequence		= {18, 19, 20};
	private int [] eaterSouthDownSequence	= {21, 22, 23};
	private int [] eaterDeadSequence	= {24};
	private int [] enemyEastSequence	= {0};
	private int [] enemyWestSequence	= {1};
	private int [] enemyNorthSequence	= {2};
	private int [] enemySouthSequence	= {3};
	public static AudioClip backgroundMusic;  // 背景音乐
	public static AudioClip eaterMusic;       //吃热狗音乐
	public static AudioClip gameOverMusic;     //游戏结束音乐
	public static AudioClip winMusic;     //游戏胜利音乐
	public static AudioClip awardMusic;     //奖励音乐
	public static AudioClip boomMusic;     //炸弹音乐
	static {
		try {
			backgroundMusic = Applet.newAudioClip(new File("music/bgmusic.wav").toURI().toURL());
			eaterMusic =  Applet.newAudioClip(new File("music/eatHotDog.wav").toURI().toURL());
			gameOverMusic = Applet.newAudioClip(new File("music/game over.wav").toURI().toURL());
			winMusic = Applet.newAudioClip(new File("music/victory.wav").toURI().toURL());
			awardMusic = Applet.newAudioClip(new File("music/award.wav").toURI().toURL());
			boomMusic = Applet.newAudioClip(new File("music/firecracker.wav").toURI().toURL());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Timer animationTimer;  
	private long time;
	
	
	public Background (User login,Animation eater, Animation [] e) {
		super(null);
		this.login = login;
		backgroundMusic.play();
		backgroundMusic.loop();
		this.eater = eater;
		enemies = e;		
		eater.setLocation(gateWidth, (vState.length/2)*(pathWidth+gateWidth)+((pathWidth-eater.getWidth())/2));
		eater.setSequence(eaterEastUpSequence);
		add(eater);
		placeEnemies();
		setuphGates();
		setupvGates();
		setupDots();
		setupIndexs();
		setLocation(0, 0);
		control = new GameController(this);
		control.setSize(hState[0].length*cellWidth+gateWidth, 40);
		add(control);		
		setSize(control.getWidth(),vState.length*cellWidth+gateWidth+control.getHeight());
		control.setLocation(0, getHeight()-control.getHeight());
		animationTimer = new Timer (DELAY, this);
		animationTimer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	public void setupIndexs() {
		for(int i = 0; i < index.length; i++) {
			for(int j = 0; j < index[0].length; j++) {
				index[i][j] = false;
				index2[i][j] = false;
			}
		}
	}
	public boolean judge() {
		return indexOfHotDog == numOfHotDog;
	}
	public void setupvGates() {
		for (int i=0; i<vGates.length; i++){
			for (int j=0; j<vGates[0].length; j++){
				vGates[i][j] = new Gate (vState[i][j]);
				vGates[i][j].setSize(gateWidth, pathWidth+gateWidth*2);
				vGates[i][j].setLocation(j*(pathWidth+gateWidth), i*(pathWidth+gateWidth));
				add(vGates[i][j]);			
			}
		}
	}
	
	public void setuphGates () {
		for (int i=0; i<hGates.length; i++){
			for (int j=0; j<hGates[0].length; j++){
				hGates[i][j] = new Gate (hState[i][j]);
				hGates[i][j].setSize(pathWidth+gateWidth*2, gateWidth);
				hGates[i][j].setLocation(j*(pathWidth+gateWidth), i*(pathWidth+gateWidth));
				add(hGates[i][j]);
			}
		}

	}
	
	public void setupDots () {
		for(int i = 0; i < 10; i++) {   //放置十个小太阳
			int x = (int)(Math.random()*19);
			int y = (int)(Math.random()*15);
			if(index2[x][y] == false) {
				index2[x][y] = true;
				dots[x][y] = new Dot(1);
				dots[x][y].setLocation (20+ x*(gateWidth+pathWidth), 20+y*(gateWidth+pathWidth));
				add(dots[x][y]);
			}
		}
		for(int i = 0; i < 5; i++) {   //放置5张毛爷爷
			int x = (int)(Math.random()*19);
			int y = (int)(Math.random()*15);
			if(index2[x][y] == false) {
				index2[x][y] = true;
				dots[x][y] = new Dot(2);
				dots[x][y].setLocation (20+ x*(gateWidth+pathWidth), 20+y*(gateWidth+pathWidth));
				add(dots[x][y]);
			}
		}
		for(int i = 0; i < 5; i++) {   //放置5个炸弹
			int x = (int)(Math.random()*19);
			int y = (int)(Math.random()*15);
			if(index2[x][y] == false) {
				index2[x][y] = true;
				dots[x][y] = new Dot(3);
				dots[x][y].setLocation (20+ x*(gateWidth+pathWidth), 20+y*(gateWidth+pathWidth));
				add(dots[x][y]);
			}
		}
		for (int i=0; i<dots.length; i++){   //放置热狗
			for (int j=0;j<dots[0].length; j++){
			    if(index2[i][j] == false) {
			    	numOfHotDog++;
					dots[i][j] = new Dot (0);
					dots[i][j].setLocation (20+ i*(gateWidth+pathWidth), 20+j*(gateWidth+pathWidth));
					add(dots[i][j]);
				}
			}
		}
	}
	public void reinstateDots(){
		for (int i=0; i<dots.length; i++){
			for (int j=0;j<dots[0].length; j++){
				dots[i][j].state = 0;
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//获得资源 
		ImageIcon iIcon = new ImageIcon("images/bg5.jpg") ;
		//画一个背景图
		g.drawImage(iIcon.getImage(), 0, 0, null) ;
	}
public void placeEnemies() {
		
		enemies[0].setDirection(EAST);
		enemies[0].setLocation(70, 72);
		enemies[0].setSequence(enemyEastSequence);
		enemies[0].setVelocity(3);
		add(enemies[0]);
		
		enemies[1].setDirection(SOUTH);
		enemies[1].setLocation(6, 104);
		enemies[1].setSequence(enemySouthSequence);
		enemies[1].setVelocity(2);
		add(enemies[1]);
		
		enemies[2].setDirection(NORTH);
		enemies[2].setLocation(302, 172);
		enemies[2].setSequence(enemyNorthSequence);
		enemies[2].setVelocity(4);
		add(enemies[2]);
		
		enemies[3].setDirection(SOUTH);
		enemies[3].setLocation(369, 336	);
		enemies[3].setSequence(enemySouthSequence);
		enemies[3].setVelocity(5);
		add(enemies[3]);
		
		enemies[4].setDirection(NORTH);
		enemies[4].setLocation(70, 336);
		enemies[4].setSequence(enemyNorthSequence);
		enemies[4].setVelocity(3);
		add(enemies[4]);
		
		enemies[5].setDirection(EAST);
		enemies[5].setLocation(170, 303);
		enemies[5].setSequence(enemyEastSequence);
		enemies[5].setVelocity(5);
		add(enemies[5]);
		
		enemies[6].setDirection(SOUTH);
		enemies[6].setLocation(402, 172);
		enemies[6].setSequence(enemySouthSequence);
		enemies[6].setVelocity(4);
		add(enemies[6]);
		
		enemies[7].setDirection(EAST);
		enemies[7].setLocation(369, 6);
		enemies[7].setSequence(enemyEastSequence);
		enemies[7].setVelocity(5);
		add(enemies[7]);
		
	}
	public void setStoned()  { //静止敌人
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setVelocity(0);
		}
		time = System.currentTimeMillis();
	}
	public void getStart() {
		enemies[0].setVelocity(3);
		enemies[1].setVelocity(2);
		enemies[2].setVelocity(4);
		enemies[3].setVelocity(5);
		enemies[4].setVelocity(3);
		enemies[5].setVelocity(5);
		enemies[6].setVelocity(4);
		enemies[7].setVelocity(3);
	}
	public void actionPerformed (ActionEvent e) {
	    
		moveAnimation(eater);
		checkNextMove(eater);
		
		for (int i=0; i<enemies.length; i++){
			moveAnimation(enemies[i]);
			checkNextMove(enemies[i]);
		}	
		
		score();
		decideGameState();
		repaint();			
	}
	
	public void checkNextMove(Animation a) {
		switch (a.getDirection()){
			case EAST:
				if (a.getX()>hState[0].length*cellWidth){
					a.setLocation(gateWidth-a.getWidth(), a.getY());
				}break;
			case WEST:
				if (a.getX()<gateWidth-a.getWidth()){
					a.setLocation(hState[0].length*cellWidth, a.getY());
				}break;
		}
	}	
	//测试胜利的
	public void test() {
		indexOfHotDog = numOfHotDog;
	}
	public void score() {
		Rectangle eaterRect = eater.getBounds();
		Rectangle rect;
		for (int i=0; i<dots.length; i++){
			for (int j=0; j<dots[0].length; j++){
				rect = dots[i][j].getBounds();
				if (rect.intersects(eaterRect)) {
					dots[i][j].eliminate();	  
				    index[i][j] = true;
				    
				}
			}
		}
	}	
	public void decideGameState(){
		Rectangle eaterRect = eater.getBounds();
		Rectangle rect;
		for (int i=0; i<enemies.length; i++){
			rect = enemies[i].getBounds();
			if (rect.intersects(eaterRect)){
				gameOverMusic.play();
				eater.setVelocity(0);
				eater.setSequence(eaterDeadSequence);
				animationTimer.stop();
				repaint();
			int selected = JOptionPane.showConfirmDialog(this,"Sorry You Lose!" +
									"\nYour Score = " + score + "\nPlay again?",
                                    "Oooooooooooops",
                                    JOptionPane.YES_NO_OPTION);
			if (selected==JOptionPane.OK_OPTION){
				restart();
				}
			else {
				JOptionPane.showMessageDialog(this, "Thanks for playing!");
				ImplUserService service = new UserService();
				service.updateScore(login.getNickName(),score);
			    // 跳转至排行榜页面
				new ScoreListView().setVisible(true);
				};
			}
		}
		if (judge()){
			winMusic.play();  //游戏胜利的音乐
			animationTimer.stop();
			indexOfHotDog = 0;
			repaint();
			int selected  = JOptionPane.showConfirmDialog(this,"Congratulations!!\n"+
								"You Win! \nYour Score = " + score +" \nPlay again?",
                                    "Woooooooooowww",
                                    JOptionPane.YES_NO_OPTION);

			if (selected==JOptionPane.OK_OPTION){
				restart();
				}
			else {
				JOptionPane.showMessageDialog(this, "Thanks for playing!");
				ImplUserService service = new UserService();
				service.updateScore(login.getNickName(),score);
			    // 跳转至排行榜页面
				new ScoreListView().setVisible(true);
				};
						
		}
	}
	
	public void restart(){
		score = -1;
		eater.setLocation(gateWidth, (vState.length/2)*(pathWidth+gateWidth)+
									  gateWidth+((pathWidth-eater.getWidth())/2));
		
		eater.setSequence(eaterEastUpSequence);
		placeEnemies();
		reinstateDots();
		animationTimer.start();
	} 
	public void stop() {   //游戏暂停
		animationTimer.stop();
	}
	public void start() {   //游戏继续
		animationTimer.start();
	}
	public void level1() {  //设置难度等级1
		enemies[0].setVelocity(3);
		enemies[1].setVelocity(2);
		enemies[2].setVelocity(4);
		enemies[3].setVelocity(5);
		enemies[4].setVelocity(3);
		enemies[5].setVelocity(5);
		enemies[6].setVelocity(4);
		enemies[7].setVelocity(3);
	}
	public void setLevel2() {
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setVelocity(5);
		}
	}
	public void level2() {  //设置难度等级2
		setLevel2();
	}
	public void setLevel3() {
		for(int i = 0; i < enemies.length; i++) {
			enemies[i].setVelocity(6);
		}
	}
	public void level3() {  //设置难度等级3
		setLevel3();
	}
	public void moveAnimation (Animation a){
		Point p1;
		Point p2;
		int aWidth = a.getWidth();
		int aHeight= a.getHeight();
		
		int v = a.getVelocity();
		int cushion, row1, row2, col1, col2;
		
		switch (a.getDirection()){
			case EAST:
				p1 	= new Point (a.getX()+aWidth, a.getY());
				p2 	= new Point (a.getX()+aWidth, a.getY()+aHeight);
				row1 = p1.y/cellWidth;
				row2 = (p2.y-1)/cellWidth;
				col1 = (p1.x)/cellWidth;
				cushion = cellWidth-(p1.x%cellWidth);
				if (cushion<v && cushion>0){
					a.setLocation(a.getX()+cushion, a.getY());
					return;									
				}
			 if (p1.x%cellWidth==0){
				if(vState[row1][col1]==0||vState[row2][col1]==0){
					if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
				return;						
				}
			  }
				if ((p1.y-gateWidth)%cellWidth>cellWidth-aHeight||
					p1.y%(cellWidth)>cellWidth-aHeight){
					if (col1<hState[0].length){
						if (hState[row2][col1]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
						return;
					}	
					}
				}
				a.setLocation(a.getX()+v, a.getY());
			
			break;
			case WEST:
				p1 	= new Point (a.getX(), a.getY());
				p2 	= new Point (a.getX(), a.getY()+aHeight);
				row1 = p1.y/cellWidth;
				row2 = (p2.y-1)/cellWidth;
				col1 = p1.x/cellWidth;
				cushion = (p1.x%cellWidth)-gateWidth;
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX()-cushion, a.getY());
					return;									
				}
				
			  if ((p1.x-gateWidth)%cellWidth==0){
					
				if(vState[row1][col1]==0||vState[row2][col1]==0){
					if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
						return;
					}	
				}
				
				if ((p1.y-gateWidth)%cellWidth>cellWidth-aHeight||
					 p1.y%cellWidth>cellWidth-aHeight){
					col1=(p1.x-gateWidth*2)/cellWidth;
					if (hState[row2][col1]==0){
						if (a.getID()==ENEMY){
							double rand = 2*Math.random();
							a.setDirection(rand<1?NORTH:SOUTH);
							a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
						}	
						return;
					}	
				}
				
				a.setLocation(a.getX()-v, a.getY());
				
			break;
			case NORTH:
				p1 	= new Point (a.getX(), a.getY());
				p2 	= new Point (a.getX()+aWidth, a.getY());
				row1 = p1.y/cellWidth;
				col1 = p1.x/cellWidth;
				col2 = (p2.x-1)/cellWidth;
				if (col2>=hState[0].length){
					col2= hState[0].length-1;
				}//end if
				
				cushion = p1.y%cellWidth-gateWidth;
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX(), a.getY()-cushion);
					return;									
				}//end if
				
			  if ((p1.y-gateWidth)%cellWidth==0){
					
				if(hState[row1][col1]==0||hState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}
						return;
					}//end inner if	
				}//end outer if
				
				if (!(p1.x%cellWidth>=gateWidth&&p1.x%cellWidth<=cellWidth-aWidth)){
					row1= (p1.y-gateWidth*2)/cellWidth;
					if (vState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}
						return;
					}
				}
				a.setLocation(a.getX(), a.getY()-v);
				
			break;
				
			case SOUTH:
				p1 	= new Point (a.getX(), a.getY()+a.getHeight());
				p2 	= new Point (a.getX()+aWidth, a.getY()+a.getHeight());
				row1 = p1.y/cellWidth;
				col1 = p1.x/cellWidth;
				col2 = (p2.x-1)/cellWidth;
				if (col2>=hState[0].length){
					col2= hState[0].length-1;
				}//end if
					
				
				cushion = cellWidth-(p1.y%cellWidth);
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX(), a.getY()+cushion);
					return;									
				}//end if
				
			  if (p1.y%cellWidth==0){
					
				if(hState[row1][col1]==0||hState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}
						return;
					}//end inner if	
				}//end outer if
				
				if (p1.x%cellWidth>cellWidth-aWidth||
					(p1.x-gateWidth)%cellWidth>cellWidth-aWidth){
					if (vState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}//end inner if
						return;
					}//end middle if	
				}//end outer if
				a.setLocation(a.getX(), a.getY()+v);
				
			break;
		}//end switch..
				
	}
	
	
	public void keyPressed (KeyEvent e){ 
	  int keyCode = e.getKeyCode();
   		if (keyCode==e.VK_LEFT){
   		   	eater.setDirection(WEST);
			eater.setSequence(eaterWestUpSequence);	   		   	
   			eater.setVelocity(4);	 		  	
   		}else if (keyCode==e.VK_RIGHT){
   		  	eater.setDirection(EAST);	
   			eater.setSequence(eaterEastUpSequence);
   			eater.setVelocity(4);
   		}else if (keyCode==e.VK_UP){
   			eater.setDirection(NORTH);	
   			eater.setSequence(eaterNorthUpSequence);
   			eater.setVelocity(4);
   		     			
   		}else if (keyCode==e.VK_DOWN){
   			eater.setDirection(SOUTH);	
   			eater.setSequence(eaterSouthUpSequence);
   			eater.setVelocity(4); 			
   		}else return;
	}
	public void keyReleased (KeyEvent e){
		int keyCode = e.getKeyCode();
    	if (keyCode==e.VK_LEFT||keyCode==e.VK_RIGHT||keyCode==e.VK_UP||keyCode==e.VK_DOWN)
    	{	    	
    		eater.setVelocity(0);
    	}else{
    		return;	
    	} 			
	}
	public void keyTyped (KeyEvent e){}	
	

	class Dot extends JPanel {  //得分点
		ImageIcon icon = new ImageIcon ("images/hotdog.png"); //普通加分
		ImageIcon icon1 = new ImageIcon ("images/sun.png");   //加10分小太阳
		ImageIcon icon2 = new ImageIcon ("images/money.jpg");  //毛爷爷静止3秒
		ImageIcon icon3 = new ImageIcon ("images/boom.png");  //炸弹扣20分
		int state;
		int choose;
		public Dot (int choose) {
			super (null);
			setOpaque(false);
			setSize(20, 20);
			state=0;
			this.choose = choose;
		}
		
		public void paintComponent (Graphics g){  //把图片设为0 0就看不见了
			super.paintComponent(g);
			if (state==0) {
				if(choose == 0) {
				   icon.paintIcon(this, g, 0, 0);
				}
				else if(choose == 1) {
				   icon1.paintIcon(this, g, 0, 0);
				}
				else if(choose == 2) {
				   icon2.paintIcon(this, g, 0, 0);
				}
				else if(choose == 3) {
				   icon3.paintIcon(this, g, 0, 0);
				}
			}
		}
		
		public void eliminate() {  //分数增加
			long time1 = System.currentTimeMillis();
			if((time1 - time) > 3000) {
				getStart();
			}
			if (state==0){
				eaterMusic.play();
				if(choose == 0) {
					control.getLabel().setText("Score  =  " + ++score);
					control.getLabel2().setText("hotDog!!");
					state=1;
					indexOfHotDog++;
					repaint();
				}
				else if(choose == 1){
					score += 10;
					awardMusic.play();
					control.getLabel().setText("Score  =  " + score);
					control.getLabel2().setText("小太阳!!");
					state=1;
					repaint();
				}
				else if(choose == 2) {
					state=1;
					awardMusic.play();
					control.getLabel2().setText("毛爷爷!!");
					repaint();
					setStoned();
				}
				else if(choose == 3) {
					score -= 20;
					boomMusic.play();
					control.getLabel().setText("Score  =  " + score);
					control.getLabel2().setText("炸弹!!");
					state=1;
					repaint();
				}
			}						
		}
	} 
	class Gate extends JPanel {			
		public Gate(int state){	
		setBackground(Color.DARK_GRAY);
			if (state!=0)
				setOpaque(false);		
		}
	}
}