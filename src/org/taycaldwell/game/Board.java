package org.taycaldwell.game;

import java.awt.Graphics;



public class Board extends Entity{	
	
	Piece[][] board = new Piece[8][8];
	
	public Board()
	{	
	}
	
	public void draw(Graphics g, Art art)
	{
	}
	
	
	public void movePiece(int i1, int j1, int i2, int j2)
	{	
		// Swap Pieces
		Piece temp = board[i1][j1];
		board[i1][j1] = board[i2][j2];
		board[i2][j2] = temp;
		
		// Update Piece Values
		if(board[i1][j1] != null)
		{
			board[i1][j1].x = i1;
			board[i1][j1].y = j1;
		}
		if(board[i2][j2] != null)
		{
			board[i2][j2].x = i2;
			board[i2][j2].y = j2;
		}
	}
	
	public void removePiece(int x, int y)//removes piece in the coords
	{
		if(board[x][y] != null)
		{
			board[x][y] = null;
		}
	}
	
	public void rotateBoard()
	{	
		Piece[][] tempBoard = new Piece[8][8];
		
        for(int i = 7; i >= 0; i--) {
            for(int j = 7; j >= 0; j--) {
            	if(board[i][j] != null){
            		tempBoard[7-i][7-j] = board[i][j];
            	}
            	else{
            		tempBoard[7-i][7-j] = null;
            	}
            }
        }
        
        board = tempBoard;
        updatePieceValues();
	}
	
	public void updatePieceValues()
	{
		for(int i = 0; i < 8; i++)
		{
			for( int j = 0; j < 8; j++)
			{
				if(board[i][j] != null){
					board[i][j].x = i;
					board[i][j].y = j;
				}
			}
		}
		
			
	}
	
}
