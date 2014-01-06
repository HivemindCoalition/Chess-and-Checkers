package com.taycaldwell.game;

import java.awt.Color;
import java.awt.Graphics;



public class CheckersBoard extends Board{	
	
	public CheckersBoard()
	{	
		for(int i = 0; i < 8; i++)
		{
			for( int j = 0; j < 8; j++)
			{
				if (i % 2 == 0)
				{
					if (j % 2 == 0)
					{
						board[i][j] = null;
					}
					else
					{
						if((5 > j) && (j > 2))
						{
							board[i][j] = null;
						}
						else if(j > 4)
						{
							board[i][j] = new CheckersPiece(i, j, 0);
						}
						else
						{
							board[i][j] = new CheckersPiece(i, j, 1);
						}
					}
				}
				else
				{
					if (j % 2 == 0)
					{
						if((5 > j) && (j > 2))
						{
							board[i][j] = null;
						}
						else if(j > 4)
						{
							board[i][j] = new CheckersPiece(i, j, 0);
						}
						else
						{
							board[i][j] = new CheckersPiece(i, j, 1);
						}
					}
					else
					{
						board[i][j] = null;
					}
				}
			}
		} 
		
	}
	
	public void draw(Graphics g, Art art)
	{
		for(int i = 0; i < 8; i++)
		{
			for( int j = 0; j < 8; j++)
			{

				if (i % 2 == 0)
				{
					if (j % 2 == 0)
					{
						g.drawImage(Art.lightSquare, i*100, j*100, 100, 100, Color.PINK, null);
					}
					else
					{	
						g.drawImage(Art.darkSquare, i*100, j*100, 100, 100, Color.PINK, null);
					}
				}
				else
				{
					if (j % 2 == 0)
					{	
						g.drawImage(Art.darkSquare, i*100, j*100, 100, 100, Color.PINK, null);
					}
					else
					{	
						g.drawImage(Art.lightSquare, i*100, j*100, 100, 100, Color.PINK, null);
					}
				}
			}
		}
		g.setColor(Color.BLACK);
		g.fillRect(800, 0, 300, 800);
	}
	
}
