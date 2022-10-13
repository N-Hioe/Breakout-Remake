/// MouseBreakout Main Program
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MouseBreakout implements MouseListener, MouseMotionListener{
	// Properties
	JFrame frame = new JFrame("Breakout");
	BPanel panel = new BPanel();
	int intX;
	
	// Methods
	public void mouseMoved(MouseEvent evt){
		// Align centre of paddle with cursor as mouse moves
		intX = evt.getX();
		if(intX < 85){
		}else if(intX > 715){
		}else{
			panel.intPX = intX - 75;
		}
	}
	
	public void mouseDragged(MouseEvent evt){
	}
	
	public void mouseExited(MouseEvent evt){
	}
	
	public void mouseEntered(MouseEvent evt){
	}
	
	public void mouseReleased(MouseEvent evt){
	}
	
	public void mousePressed(MouseEvent evt){
		// If right button is pressed, start ball
		if(SwingUtilities.isRightMouseButton(evt)){
			panel.blnStart = true;
		}
		
		if(SwingUtilities.isLeftMouseButton(evt)){
			if(panel.intScore == 30 || panel.intLives == 0){
				if(panel.blnStart == false){
					panel.blnSetup = true;
					panel.intLives = 3;
					panel.intScore = 0;
				}	
			}	
		}
	}
	
	public void mouseClicked(MouseEvent evt){
	}
	
	// Constructor
	public MouseBreakout(){
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.requestFocus();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	// Main Method
	public static void main(String[] args){
		new MouseBreakout();
	}
	
}
