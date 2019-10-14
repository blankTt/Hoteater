package eater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class GameController extends JPanel implements ActionListener {
	
	private JButton exitButton = new JButton("Exit");
	private JButton stopButton = new JButton ("Stop");
	private JButton startButton = new JButton("Start");
	private JButton aboutButton = new JButton ("About");
	private JButton testButton = new JButton ("测试胜利");  //测试用的
	private JRadioButton jButton1 = new JRadioButton("一般");
	private JRadioButton jButton2 = new JRadioButton("困难");
	private JRadioButton jButton3 = new JRadioButton("炼狱");
	private JLabel label = new JLabel ("label");	
	private JLabel label2 = new JLabel ("label2");
	private Background bg;
	public GameController (Background bg){
		super(new FlowLayout(FlowLayout.CENTER));
		this.bg = bg;
		setOpaque(false);
		exitButton.addActionListener(this);
		stopButton.addActionListener(this);
		startButton.addActionListener(this);
		aboutButton.addActionListener(this);
		testButton.addActionListener(this);
		ButtonGroup group = new ButtonGroup();
		jButton1.setSelected(true);
		group.add(jButton1);
		group.add(jButton2);
		group.add(jButton3);
		jButton1.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));		//蚀刻边框
		label2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(label);		
		add(label2);
		add(exitButton);
		add(aboutButton);
		add(stopButton);
		add(startButton);
		add(testButton);
		add(jButton1);
		add(jButton2);
	    add(jButton3);
	}	
	public JLabel getLabel(){
		return label;
	}	
	public JLabel getLabel2(){
		return label2;
	}	
	public void actionPerformed (ActionEvent e){
		if (e.getSource()==exitButton){
			System.exit(0); 					
		}
		else if (e.getSource()==stopButton){
		    bg.stop();
		    bg.requestFocus();
		}
		else if(e.getSource() == startButton) {
		    bg.start();
		    bg.requestFocus();
		}
		else if (e.getSource()==aboutButton){
			JOptionPane.showMessageDialog(getParent(), "Programmed by JAVA TEAM\n" +
		"Rules\n" + 
		"hotDog score +1\n" + "money stop 3 seconds\n" + "boom score -20\n"
					+"sun score +10\n");			
			 bg.requestFocus();
		}
		else if(e.getSource() == testButton) {
			bg.test();
			 bg.requestFocus();
		}
		else if (e.getSource()==jButton1){   //设置游戏难度
			bg.level1();
			 bg.requestFocus();
		}
        else if (e.getSource()==jButton2){   
        	bg.level2();
        	 bg.requestFocus();
		}else if (e.getSource()==jButton3){  
			bg.level3();
			 bg.requestFocus();
		}
		else return;
	}
}