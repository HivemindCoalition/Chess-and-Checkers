package com.taycaldwell.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class PauseMenu{	
	
	int width, height;

	
	public enum Choice
	{
		INITIAL, EXIT, QUIT
	}
	
	Choice choice;
	
	public PauseMenu(int width, int height)
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
		g.drawString("PAUSE MENU", 220, 200);
		
		
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		
		if(choice == Choice.INITIAL)
		{	
			
			g.drawRect(305, 520, 200, 40);
			g.drawString("Options", 355, 550);
			
			g.drawRect(305, 570, 200, 40);
			g.drawString("Resume Game", 315, 600);
			
			g.drawRect(305, 620, 200, 40);
			g.drawString("Quit Game", 345, 650);
			
			g.drawRect(305, 670, 200, 40);
			g.drawString("Exit", 375, 700);
			
		}
		
		if(choice == Choice.QUIT)
		{	
			g.setFont(new Font("Arial", Font.PLAIN, 25)); 
			g.drawString("Are you sure you want to quit the current game?", 75, 420);
			

			g.drawRect(305, 520, 200, 40);
			g.drawString("Yes", 375, 550);
			
			g.drawRect(305, 570, 200, 40);
			g.drawString("No", 380, 600);
		}
		
		if(choice == Choice.EXIT)
		{	
			g.setFont(new Font("Arial", Font.PLAIN, 25)); 
			g.drawString("You are about to exit the client, are you sure?", 95, 420);
			
			
			g.drawRect(305, 520, 200, 40);
			g.drawString("Yes", 375, 550);
			
			g.drawRect(305, 570, 200, 40);
			g.drawString("No", 380, 600);
			
		}

		else
		{
		}
		g.setColor(Color.BLACK);
		g.fillRect(800, 0, 300, 800);

	}
	

	
}