package com.taycaldwell.game;

import java.awt.Color;
import java.awt.Graphics;



public class ChessBoard extends Board{	
	
	// Constructor
	public ChessBoard()
	{	
		
		for(int i = 0; i < 8; i++)
		{
			for( int j = 0; j < 8; j++)
			{
				if((j > 1) && (j < 6))
				{
					board[i][j] = null;
				}
				else if(j == 6)
				{
					board[i][j] = new ChessPiece(i, j, 0);
				}
				else if(j == 1)
				{
					board[i][j] = new ChessPiece(i, j, 1);
				}
				else
				{
					board[i][j] = null;
				}
			}
		}
		//rooks
		board[0][7] = new ChessPiece(0, 7, 0, 1);
		board[7][7] = new ChessPiece(7, 7, 0, 1);
		board[0][0] = new ChessPiece(0, 0, 1, 1);
		board[7][0] = new ChessPiece(7, 0, 1, 1);
		//knights
		board[1][7] = new ChessPiece(1, 7, 0, 2);
		board[6][7] = new ChessPiece(6, 7, 0, 2);
		board[1][0] = new ChessPiece(1, 0, 1, 2);
		board[6][0] = new ChessPiece(6, 0, 1, 2);
		//bishops
		board[2][7] = new ChessPiece(2, 7, 0, 3);
		board[5][7] = new ChessPiece(5, 7, 0, 3);
		board[2][0] = new ChessPiece(2, 0, 1, 3);
		board[5][0] = new ChessPiece(5, 0, 1, 3);
		//queens
		board[3][7] = new ChessPiece(3, 7, 0, 4);
		board[3][0] = new ChessPiece(3, 0, 1, 4);
		//kings
		board[4][7] = new ChessPiece(4, 7, 0, 5);
		board[4][0] = new ChessPiece(4, 0, 1, 5);
		
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
						g.drawImage(Art.whiteSquare, i*100, j*100, 100, 100, Color.PINK, null);
						//g.setColor(Color.PINK);
					}
					else
					{	
						g.drawImage(Art.blackSquare, i*100, j*100, 100, 100, Color.PINK, null);
						//g.setColor(Color.GRAY);
					}
				}
				else
				{
					if (j % 2 == 0)
					{	
						g.drawImage(Art.blackSquare, i*100, j*100, 100, 100, Color.PINK, null);
						//g.setColor(Color.GRAY);
					}
					else
					{	
						g.drawImage(Art.whiteSquare, i*100, j*100, 100, 100, Color.PINK, null);
						//g.setColor(Color.PINK);
					}
				}

				//g.fillRect(i*100, j*100, 100, 100);
			}
		}
		g.setColor(Color.BLACK);
		g.fillRect(800, 0, 300, 800);
	}
	
}
