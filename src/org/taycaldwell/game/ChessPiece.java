package org.taycaldwell.game;

import java.awt.*;


public class ChessPiece extends Piece
{
	
	public ChessPiece(int x, int y, int p)
	{
		super(x, y, p);
	}
	
	public ChessPiece(int x, int y, int p, int t)
	{
		super(x, y, p, t);
	}
	
	
	public void draw(Graphics g, Art art)
	{			
		if(selected)
		{
			if(type == 0) //pawn
			{
				g.drawImage(Art.pawnHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
			else if(type == 1) //rook
			{
				g.drawImage(Art.castleHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
			else if(type == 2) //knights
			{
				g.drawImage(Art.knightHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
			else if(type == 3) //bishops
			{
				g.drawImage(Art.bishopHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
			else if(type == 4) //queens
			{
				g.drawImage(Art.queenHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
			else if(type == 5) //kings
			{
				g.drawImage(Art.kingHighlight, (x*100)+5, (y*100)+5, 85, 85, null);
			}
		}
		
		switch(player)
		{
			case 1: //black
				if(type == 0) //pawn
				{
					g.drawImage(Art.chessDarkPawn, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 1) //rook
				{
					g.drawImage(Art.chessDarkCastle, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 2) //knights
				{
					g.drawImage(Art.chessDarkKnight, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 3) //bishops
				{
					g.drawImage(Art.chessDarkBishop, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 4) //queens
				{
					g.drawImage(Art.chessDarkQueen, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 5) //kings
				{
					g.drawImage(Art.chessDarkKing, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				
			break;
			case 0: //white
				if(type == 0) //pawn
				{
					g.drawImage(Art.chessLightPawn, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 1) //rook
				{
					g.drawImage(Art.chessLightCastle, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 2) //knights
				{
					g.drawImage(Art.chessLightKnight, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 3) //bishops
				{
					g.drawImage(Art.chessLightBishop, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 4) //queens
				{
					g.drawImage(Art.chessLightQueen, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 5) //kings
				{
					g.drawImage(Art.chessLightKing, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				
			break;
		}
	}
	

}