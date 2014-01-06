package com.taycaldwell.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class OptionsMenu{	
	
	int width, height;
	boolean debugMenu = false;
	boolean colorTable = false;
	
	public enum Choice
	{
		INITIAL
	}
	
	Choice choice;
	
	// Constructor
	public OptionsMenu(int width, int height)
	{
		choice = Choice.INITIAL;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g)
	{	
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 50)); 
		g.drawString("OPTIONS MENU", 200, 200);
		
		if(choice == Choice.INITIAL)
		{	
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.setColor(Color.WHITE);
			
			g.drawRect(250, 520, 305, 40);
			if(!debugMenu){g.drawString("Enable Debug Menu", 275, 550);}
			else{g.drawString("Disable Debug Menu", 275, 550);}
			
			
			
			
			g.drawRect(250, 570, 305, 40);
			g.drawString("Back", 375, 600);
			
			g.setColor(Color.BLACK);
			g.fillRect(800, 0, 300, 800);
		}
		
		else
		{
		}

	}
	

	
}