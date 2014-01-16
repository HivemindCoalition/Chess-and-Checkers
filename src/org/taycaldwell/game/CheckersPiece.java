package org.taycaldwell.game;

import java.awt.*;


public class CheckersPiece extends Piece
{
	
		public CheckersPiece(int x, int y, int p)
		{
			super(x, y, p);
		}
		
		public CheckersPiece(int x, int y, int p, int t)
		{
			super(x, y, p, t);
		}
	
	public void draw(Graphics g, Art art)
	{			
		if(selected)
		{
			g.drawImage(Art.checkersHighlight, (x*100)+4, (y*100)+5, 85, 85, null);
		}
		switch(player)
		{
			case 0:
				if(type == 0) //pawn
				{
					g.drawImage(Art.checkersDarkPawn, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 1) //king
				{
					g.drawImage(Art.checkersDarkKing, (x*100)+10, (y*100)+10, 75, 75, null);
				}
			
			break;
			case 1:
				if(type == 0) //pawn
				{
					g.drawImage(Art.checkersLightPawn, (x*100)+10, (y*100)+10, 75, 75, null);
				}
				else if(type == 1) //king
				{
					g.drawImage(Art.checkersLightKing, (x*100)+10, (y*100)+10, 75, 75, null);
				}
			break;
		}
	}
	

}
