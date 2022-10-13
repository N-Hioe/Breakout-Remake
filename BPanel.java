/// BPanel - MouseBreakout
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BPanel extends JPanel implements ActionListener{
	// Properties
	Timer timer = new Timer(1000/60, this);
	GetBBall bball = new GetBBall();
	int intCount;
	int intCounter;
	boolean blnStart = false;
	int intPX = 325;
	int intPY = 585;
	int intLives = 3;
	int intScore = 0;
	Font score = new Font("", Font.PLAIN, 25);
	Font end = new Font("", Font.PLAIN, 100);
	BufferedImage cookie = null;
	BufferedImage paddle = null;
	
	// Brick
	int bricks[][] = new int[3][10];
	int intBX = 0;
	int intBY = 0;
	int intW = 78;
	int intH = 50;
	boolean blnSetup = true;
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		}
	}
	
	// Override paintComponent
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		// Lose
		if(intLives == 0){
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(end);
			g.drawString("You Lose.", 200, 200);
			g.drawString("Final Score: "+intScore, 70, 350);
			g.setFont(score);
			g.drawString("Left click to play again.", 290, 450);
			blnStart = false;
		// Win
		}else if(intScore == 30){
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(end);
			g.drawString("You Won!", 200, 200);
			g.setFont(score);
			g.drawString("Left click to play again.", 290, 450);
			blnStart = false;
		}else{
			// Setup initial bricks positions once
			if(blnSetup == true){
				for(intCount = 0; intCount < 3; intCount++){
					for(intCounter = 0; intCounter < 10; intCounter++){
						intBX = intCounter * 78 + 10;
						bricks[intCount][intCounter] = intBX;
					}
				}
				// Default ball and paddle positions
				bball.intX = 385;
				bball.intY = 500;
				intPX = 325;
				intPY = 585;
			}
			// Score and Lives
			g.setColor(new Color(139, 139, 139));
			g.setFont(score);
			g.drawString("Score: "+intScore, 50, 400);
			g.drawString("Lives: "+intLives, 50, 500);
			blnSetup = false;
			
			// Set brick colours
			for(intCount = 0; intCount < 3; intCount++){
				for(intCounter = 0; intCounter < 10; intCounter++){
					if(intCount == 0){
						g.setColor(new Color(231, 205, 28));
					}else if(intCount == 1){
						g.setColor(new Color(14, 149, 83));
					}else if(intCount == 2){
						g.setColor(new Color(34, 62, 146));
					}
					intBY = intCount * 50 + 10;
					
					// Collision with brick affects ball x or y deflection, "removes" brick
					if(bricks[intCount][intCounter] + 78 >= bball.intX && bricks[intCount][intCounter] <= bball.intX){
						if(intBY + 5 >= bball.intY && intBY <= bball.intY + 15){
							bball.intDefY = bball.intDefY * -1;
							bricks[intCount][intCounter] = 9999;
							intScore = intScore + 1;
						}else if(intBY + 50 >= bball.intY && intBY + 45 <= bball.intY + 15){
							bball.intDefY = bball.intDefY * -1;
							intScore = intScore + 1;
							bricks[intCount][intCounter] = 9999;
						}else if(intBY + 45 > bball.intY && intBY + 5 < bball.intY){
							bball.intDefX = bball.intDefX * -1;
							bricks[intCount][intCounter] = 9999;
							intScore = intScore + 1;
						}
					}
					// Draw bricks	
					g.fillRect(bricks[intCount][intCounter], intBY, intW, intH);
				}
			}
			
			// Draw paddle and ball position
			g.setColor(Color.WHITE);
			g.drawImage(paddle, intPX, intPY, null);
			g.drawImage(cookie, bball.intX, bball.intY, null);
			
			// Show prompt before starting ball
			if(blnStart == false){
				g.drawString("Right click to start the ball.", 230, 300);
			}
			// Start ball
			if(blnStart == true){
				bball.drawBBall(g);
			}
			// Side Borders
			g.setColor(new Color(195, 195, 195));
			g.fillRect(0, 0, 10, 600);
			g.fillRect(790, 0, 10, 600);
			g.fillRect(0, 0, 800, 10);
			
			// Paddle interaction with ball
			if(intPY + 15 > bball.intY && intPY < bball.intY + 15){
				// Hits middle portion of paddle
				if(intPX + 90 > bball.intX && intPX + 60 < bball.intX + 15){
					bball.intDefY = bball.intDefY * -1;
					bball.intDefX = 0;
				// Hits middle left portion of paddle
				}else if(intPX + 60 > bball.intX && intPX + 30 < bball.intX + 15){
					bball.intDefY = bball.intDefY * -1;
					bball.intDefX = -5;
				// Hits left portion of paddle
				}else if(intPX + 30 > bball.intX && intPX < bball.intX + 15){
					bball.intDefY = bball.intDefY * -1;
					bball.intDefX = -10;
				// Hits middle right portion of paddle
				}else if(intPX + 120 > bball.intX && intPX + 90 < bball.intX + 15){
					bball.intDefY = bball.intDefY * -1; 
					bball.intDefX = 5;
				// Hits right portion of paddle
				}else if(intPX + 150 > bball.intX && intPX + 120 < bball.intX + 15){
					bball.intDefY = bball.intDefY * -1;
					bball.intDefX = 10;
				}
			}

			// Ball interaction with borders
			if(bball.intY <= 10){
				bball.intDefY = bball.intDefY * -1;
			}
			if(bball.intX <= 10 || bball.intX >= 780){
				bball.intDefX = bball.intDefX * -1;
			}
			
			// Ball goes out
			if(bball.intY > 600){
				bball.intX = 385;
				bball.intY = 500;
				bball.intDefY = 10;
				bball.intDefX = 0;
				blnStart = false;
				intLives = intLives - 1;
			}
		}
	}
	
	// Constructor
	public BPanel(){
		super();
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		timer.start();
		// Load ball and paddle images
		try{
			cookie = ImageIO.read(new File("cookie.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
		try{
			paddle = ImageIO.read(new File("paddle.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
	}
}
