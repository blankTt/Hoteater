package eater;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class UpdatePassPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//�����Դ 
		ImageIcon iIcon = new ImageIcon("images/bg4.jpg") ;
		//��һ������ͼ
		g.drawImage(iIcon.getImage(), 0, 0, null) ;
	}

	
}