package eater;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel  extends JPanel{
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//获得资源 
		ImageIcon iIcon = new ImageIcon("images/bg7.jpeg") ;
		//画一个背景图
		g.drawImage(iIcon.getImage(), 0, 0, null) ;
	}
}
