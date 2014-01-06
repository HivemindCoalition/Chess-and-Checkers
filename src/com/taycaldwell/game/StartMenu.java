package com.taycaldwell.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class StartMenu{	
	
	int width, height;
	int count = 0;
	
	public enum Choice
	{
		INITIAL, CHOOSE_GAME, CHOOSE_MULTIPLAYER
	}
	
	Choice choice;
	
	public StartMenu(int width, int height)
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
		g.drawString("CHECKERS AND CHESS", 100, 200);
		
		if(choice == Choice.INITIAL)
		{	
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			
			
			if(count == 0)
			{
				g.setColor(Color.WHITE);
				g.drawString("Click To Start", 325, 600);
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				count++;
			}
			else{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				count--;
			}
		}
		else if(choice == Choice.CHOOSE_GAME)
		{
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.setColor(Color.WHITE);
			g.drawString("Select a Game:", 300, 500);	
			g.drawRect(305, 520, 150, 40);
			g.drawString("Checkers", 325, 550);
			g.drawRect(305, 570, 150, 40);
			g.drawString("Chess", 340, 600);
		}
		else if(choice == Choice.CHOOSE_MULTIPLAYER)
		{
			
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.setColor(Color.WHITE);
			g.drawString("Select a Game Type:", 275, 500);	
			g.setColor(Color.DARK_GRAY);
			g.drawRect(295, 520, 200, 40);
			g.drawString("Single Player", 320, 550);
			g.setColor(Color.WHITE);
			g.drawRect(295, 570, 200, 40);
			g.drawString("Local Multiplayer", 300, 600);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(295, 620, 200, 40);
			g.drawString("LAN Multiplayer", 305, 650);
			
		}
		else{}
	}
	

	
}