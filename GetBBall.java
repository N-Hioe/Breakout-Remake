/// GetBBall - MouseBreakout
/// By: Nicholas Hioe
/// ICS4U1
/// Version 1.0
/// 2021-11-19

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GetBBall{
	// Properties
	int intX = 385;
	int intY = 500;
	int intDefY = 10;
	int intDefX = 0;
	BufferedImage cookie = null;
	
	// Methods
	public void nextLocation(){
		intY = intY + intDefY;
		intX = intX + intDefX;
	}
	
	public void drawBBall(Graphics g){
		g.setColor(Color.WHITE);
		g.drawImage(cookie, intX, intY, null);
		this.nextLocation();
	}
	
	// Constructor
	public GetBBall(){
		try{
			cookie = ImageIO.read(new File("cookie.png"));
		}catch(IOException e){
			System.out.println("Unable to load image.");
		}
	}
	
}
