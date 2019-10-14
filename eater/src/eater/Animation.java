package eater;


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Animation extends JPanel implements GameConstants {
	
	private int direction;
	private int id;
	private int velocity;
	private int oldDirection;
	private boolean changeImage = true;
	private ImageIcon icons [];
		
	private int [] imageSequence;
	private int currentImage =0;	
	public Animation (ImageIcon [] ic, int id, int d){
		super(null);
		setOpaque(false);
		icons = ic;
		this.id = id;
		direction = d;
		setSize(25, 25);
					
	}
	
	public int getID (){
		return id;
	}
		
	public void setVelocity (int v){
		if (v>=0 && v<=MAX_VELOCITY)
			velocity = v;
	}
	public int getVelocity (){
		return velocity;
	}
			
	public void setSequence (int [] newSequence){
		imageSequence = newSequence;
	
	}
	
	public int [] getSequence (){
		return imageSequence;
	}
	

	public void setIcons (ImageIcon [] ic){
		icons = ic;
	}
	
	public ImageIcon [] getIcons (){
		return icons;
	}
	
	public void setDirection (int d){
		if (direction!=d){
		oldDirection = direction;
		direction =d;
		}		
		
	}
	
	public int getOldDirection (){
		return oldDirection;
	}
	
	public int getDirection() {
		return direction;
	}
					
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		if (currentImage>=imageSequence.length){
			currentImage=0;	
		}
		
		icons[imageSequence[currentImage]].paintIcon(this, g, 0, 0);
		if (changeImage)
		++currentImage;
		changeImage = !changeImage;					
	}			
	}//end class Animation..