package com.taycaldwell.game;


public class RuleSet{	
	
	private RuleSet(){}
	
	public static boolean check(int x, int y, int pieceX, int pieceY, int currentPlayer, Board board, String gameMode)
	{
		boolean valid = true;
		
		if(gameMode == "Chess"){
			switch(board.board[pieceX][pieceY].type)
			{
				case 0: valid = pawnMove(x, y, pieceX, pieceY, board);//is a pawn
				break;
				case 1: valid = rookMove(x, y, pieceX, pieceY, board);//is a rook / castle
				break;
				case 2: valid = knightMove(x, y, pieceX, pieceY, board);//is a knight / horse
				break;
				case 3: valid = bishopMove(x, y, pieceX, pieceY, board);//is a bishop
				break;
				case 4: valid = queenMove(x, y, pieceX, pieceY, board);//is a queen
				break;
				case 5: valid = kingMove(x, y, pieceX, pieceY, board);//is a king
				break;
			}
			if(teamPiece(x, y, pieceX, pieceY, board)){valid = false;}
		}
		
		if(gameMode == "Checkers"){
			if(!spotEmpty(x, y, board)){valid = false;}
			
			if(!diagonalMove(x, y, pieceX, pieceY, board))
			{
				if(!jumpMove(x, y, pieceX, pieceY, board)){valid = false;}
			}
			else
			{
				if(possibleCapture(currentPlayer, board)){valid = false;}
			}
		}
		
		return valid;
		
	}
	
	public static boolean spotEmpty(int x, int y, Board board)
	{
		if(x < 0 || x > 7 || y < 0 || y > 7)
		{
			return false;
		}
		if(board.board[x][y] == null)
		{
			return true;
		}
		return false;
	}
	
	public static boolean pawnMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(x == pieceX)
		{
			if(pieceY - y == 1 && spotEmpty(x, y, board)) 
			{
				return true;
			}
			else if(pieceY == 6 && pieceY - y == 2 && spotEmpty(pieceX, pieceY-1, board) && spotEmpty(x, y, board))
			{
				return true;
			}
		}
		else if(Math.abs(pieceX - x) == 1 && !spotEmpty(x, y, board))
		{
			if(pieceY - y == 1)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean pawnMoveTwo(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(board.board[pieceX][pieceY].type == 0 && pieceY - y == 2)
		{
			return true;
		}
		return false;
	}
	
	public static boolean enPassant(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(pieceY - y == 1 && spotEmpty(x, y, board) && !spotEmpty(x, pieceY, board) && board.board[x][pieceY].player != board.board[pieceX][pieceY].player)
		{
			if(Math.abs(pieceX - x) == 1)
			{
				board.removePiece(x, pieceY);
				return true;
			}
		}
		return false;
	}
	
	public static boolean rookMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		int i;

		if(pieceY == y)
		{
			if(pieceX < x)
			{
				for(i = pieceX + 1; i < x; i++)
				{
					if(!spotEmpty(i, y, board)){return false;}
				}
				return true;
			}
			else if(pieceX > x)
			{
				for(i = pieceX - 1; i > x; i--)
				{
					if(!spotEmpty(i, y, board)){return false;}
				}
				return true;
			}
			
		}
		else if(pieceX == x)
		{
			if(pieceY < y)
			{
				for(i = pieceY + 1; i < y; i++)
				{
					if(!spotEmpty(x, i, board)){return false;}
				}
				return true;
			}
			else if(pieceY > y)
			{
				for(i = pieceY - 1; i > y; i--)
				{
					if(!spotEmpty(x, i, board)){return false;}
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean bishopMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		int i, j;
		
		if(Math.abs(pieceX - x) == Math.abs(pieceY - y))
		{
			if(pieceX - x > 0 && pieceY - y > 0)
			{
				for(i = pieceX - 1, j = pieceY - 1; i > x || j > y; i--, j--)
				{
					if(!spotEmpty(i, j, board)){return false;}
				}
			}
			else if(pieceX - x > 0 && pieceY - y < 0)
			{
				for(i = pieceX - 1, j = pieceY + 1; i > x || j < y; i--, j++)
				{
					if(!spotEmpty(i, j, board)){return false;}
				}
			}
			else if(pieceX - x < 0 && pieceY - y > 0)
			{
				for(i = pieceX + 1, j = pieceY - 1; i < x || j > y; i++, j--)
				{
					if(!spotEmpty(i, j, board)){return false;}
				}
			}
			else
			{
				for(i = pieceX + 1, j = pieceY + 1; i < x || j < y; i++, j++)
				{
					if(!spotEmpty(i, j, board)){return false;}
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean knightMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		
		if(Math.abs(pieceX - x) == 2)
		{
			if(Math.abs(pieceY - y) == 1){return true;}
		}
		else if(Math.abs(pieceX - x) == 1)
		{
			if(Math.abs(pieceY - y) == 2){return true;}
		}
		return false;
	}
	
	public static boolean queenMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(rookMove(x, y, pieceX, pieceY, board) || bishopMove(x, y, pieceX, pieceY, board))
		{
			return true;
		}
		return false;
	}
	
	public static boolean kingMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		if((Math.abs(pieceX - x) == 1))
		{
			if(pieceY == y){return true;}
		}
		else if((Math.abs(pieceY - y) == 1))
		{
			if(pieceX == x){return true;}
		}
		return false;
	}
	
	public static boolean teamPiece(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(!spotEmpty(x, y, board) && board.board[pieceX][pieceY].player == board.board[x][y].player)
		{
			return true;
		}
		return false;
	}
	
	public static boolean inCheck(Board board, int player, String gameMode)
	{
		int kingX = 0, kingY = 0;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(!spotEmpty(i, j, board) && board.board[i][j].type == 5 && player == board.board[i][j].player)
				{
					kingX = i;
					kingY = j;
					break;
				}
			}
		}
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(!spotEmpty(i, j, board) && check(kingX, kingY, i, j, player, board, gameMode))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean inCheckStill(int x, int y, int pieceX, int pieceY, Board board, int player, String gameMode)
	{
		if(check(x, y, pieceX, pieceY, player, board, gameMode))
		{
			Piece temp = board.board[x][y];
			
			board.removePiece(x, y);
			board.movePiece(x, y, pieceX, pieceY);

			if(!inCheck(board, player, gameMode))
			{
				board.movePiece(x, y, pieceX, pieceY);
				board.board[x][y] = temp;
				return false; 
			}
			else
			{
				board.movePiece(x, y, pieceX, pieceY);
				board.board[x][y] = temp;
			}
		}
		return true;
	}
	
	public static boolean inCheckmate(Board board, int player, String gameMode)
	{
		for(int i = 0; i < 8; i++) 
		{
			for(int j = 0; j < 8; j++)
			{
				if(!spotEmpty(i, j, board) && board.board[i][j].player == player)
				{
					for(int n = 0; n < 8; n++)
					{
						for(int m = 0; m < 8; m++)
						{
							if(check(n, m, i, j, player, board, gameMode))
							{
							
								Piece temp = board.board[n][m];
								
								board.removePiece(n, m);
								board.movePiece(n, m, i, j);

								if(!inCheck(board, player, gameMode))
								{
									board.movePiece(n, m, i, j);
									board.board[n][m] = temp;
									return false;
								}
								else
								{
									board.movePiece(n, m, i, j);
									board.board[n][m] = temp;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean isKing(int pieceX, int pieceY, Board board)
	{
		if(board.board[pieceX][pieceY] != null && board.board[pieceX][pieceY].type == 1){return true;}
		return false;
	}
	
	public static void removePiece(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(x - pieceX > 0)
		{
			if(y - pieceY > 1)
			{
				board.board[pieceX + 1][pieceY + 1] = null;
			}
			else if(y - pieceY < 1)
			{
				board.board[pieceX + 1][pieceY - 1] = null;
			}
		}
		else
		{
			if(y - pieceY > 1)
			{
				board.board[pieceX - 1][pieceY + 1] = null;
			}
			else if(y - pieceY < 1)
			{
				board.board[pieceX - 1][pieceY - 1] = null;
			}
		}
	}
	
	public static boolean diagonalMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(y == pieceY-1 && (x == pieceX+1 || x == pieceX-1))
		{
			return true;
		}
		else if(isKing(pieceX, pieceY, board))
		{
			if(y == pieceY+1 && (x == pieceX+1 || x == pieceX-1))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean jumpMove(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(captureAvailableC(pieceX, pieceY, board).substring(0,1).equals("b"))
		{
			if(!((x == pieceX-2 && y == pieceY-2) || (x == pieceX+2 && y == pieceY-2)))
			{
				if(isKing(pieceX, pieceY, board))
				{
					return jumpMoveK(x, y, pieceX, pieceY, board);
				}
				else{return false;}
			}
		}
		else if(captureAvailableC(pieceX, pieceY, board).substring(0,1).equals("l"))
		{
			if(!(x == pieceX-2 && y == pieceY-2))
			{
				if(isKing(pieceX, pieceY, board))
				{
					return jumpMoveK(x, y, pieceX, pieceY, board);
				}
				else{return false;}
			}
		}
		else if(captureAvailableC(pieceX, pieceY, board).substring(0,1).equals("r"))
		{
			if(!(x == pieceX+2 && y == pieceY-2))
			{
				if(isKing(pieceX, pieceY, board))
				{
					return jumpMoveK(x, y, pieceX, pieceY, board);
				}
				else{return false;}
			}
		}
		else
		{
			if(isKing(pieceX, pieceY, board))
			{
				return jumpMoveK(x, y, pieceX, pieceY, board);
			}
			else{return false;}
		}
		return true;
	}
	
	public static boolean jumpMoveK(int x, int y, int pieceX, int pieceY, Board board)
	{
		if(captureAvailableC(pieceX, pieceY, board).substring(1).equals("b"))
		{
			if(!((x == pieceX-2 && y == pieceY+2) || (x == pieceX+2 && y == pieceY+2)))
			{
				return false;
			}
		}
		else if(captureAvailableC(pieceX, pieceY, board).substring(1).equals("l"))
		{
			if(!(x == pieceX-2 && y == pieceY+2))
			{
				return false;
			}
		}
		else if(captureAvailableC(pieceX, pieceY, board).substring(1).equals("r"))
		{
			if(!(x == pieceX+2 && y == pieceY+2))
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return true;
	}
	
	public static boolean moveAvailable(int pieceX, int pieceY, Board board)
	{
		if(spotEmpty(pieceX-1, pieceY-1, board) || spotEmpty(pieceX+1, pieceY-1, board))
		{
			return true;
		}
		else if(isKing(pieceX, pieceY, board))
		{
			if(spotEmpty(pieceX-1, pieceY+1, board) || spotEmpty(pieceX+1, pieceY+1, board))
			{
				return true;
			}
		}
		return false;
	}
	public static boolean possibleMove(int currentPlayer, Board board)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(!spotEmpty(i, j, board) && board.board[i][j].player == currentPlayer)
				{ 	
					if(moveAvailable(i, j, board))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean possibleCapture(int currentPlayer, Board board)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(!spotEmpty(i, j, board) && board.board[i][j].player == currentPlayer)
				{ 	
					if(!captureAvailableC(i, j, board).equals("nn") && !captureAvailableC(i, j, board).equals("np"))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static String captureAvailable(int pieceX, int pieceY, Board board)
	{
		boolean leftEdgePiece = false;
		boolean rightEdgePiece = false;

		if(pieceY-1 < 0){return "n";}
		
		if(pieceX-2 < 0)
		{
			leftEdgePiece = true;
		}
		else if(pieceX+2 > 7)
		{
			rightEdgePiece = true;
		}
			
		if(rightEdgePiece)
		{
			if(!spotEmpty(pieceX-1, pieceY-1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY-1].player))
			{
				if((pieceY-2 >= 0) && spotEmpty(pieceX-2, pieceY-2, board))
				{
					return "l";
				}
			}
		}
		else if(leftEdgePiece)
		{
			if(!spotEmpty(pieceX+1, pieceY-1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX+1][pieceY-1].player))
			{
				if((pieceY-2 >= 0) && spotEmpty(pieceX+2, pieceY-2, board))
				{
					return "r";
				}
			}
		}
		else
		{
			if(!spotEmpty(pieceX+1, pieceY-1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX+1][pieceY-1].player))
			{
				if((pieceY-2 >= 0) && spotEmpty(pieceX+2, pieceY-2, board))
				{
					if(!spotEmpty(pieceX-1, pieceY-1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY-1].player))
					{
						if((pieceY-2 >= 0) && spotEmpty(pieceX-2, pieceY-2, board))
						{
							return "b"; 
						}
					}
					return "r";
				}
			}
			if(!spotEmpty(pieceX-1, pieceY-1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY-1].player))
			{
				if((pieceY-2 >= 0) && spotEmpty(pieceX-2, pieceY-2, board))
				{
					return "l";
				}
			}
		}
		return "n";
	}
	
	public static String captureAvailableR(int pieceX, int pieceY, Board board)
	{
		boolean leftEdgePiece = false;
		boolean rightEdgePiece = false;

		if(pieceY+1 > 7){return "n";}
		
		if(pieceX-2 < 0)
		{
			leftEdgePiece = true;
		}
		else if(pieceX+2 > 7)
		{
			rightEdgePiece = true;
		}
			
		if(rightEdgePiece)
		{
			if(!spotEmpty(pieceX-1, pieceY+1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY+1].player))
			{
				if((pieceY+2 <= 7) && spotEmpty(pieceX-2, pieceY+2, board))
				{
					return "l";
				}
			}
		}
		else if(leftEdgePiece)
		{
			
			if(!spotEmpty(pieceX+1, pieceY+1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX+1][pieceY+1].player))
			{
				if((pieceY+2 <= 7) && spotEmpty(pieceX+2, pieceY+2, board))
				{
					return "r";
				}
			}
		}
		else
		{
			if(!spotEmpty(pieceX+1, pieceY+1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX+1][pieceY+1].player))
			{
				if((pieceY+2 <= 7) && spotEmpty(pieceX+2, pieceY+2, board))
				{
					if(!spotEmpty(pieceX-1, pieceY+1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY+1].player))
					{
						if((pieceY+2 <= 7) && spotEmpty(pieceX-2, pieceY+2, board))
						{
							return "b";
						}
					}
					return "r";
				}
			}
			if(!spotEmpty(pieceX-1, pieceY+1, board) && (board.board[pieceX][pieceY].player != board.board[pieceX-1][pieceY+1].player))
			{
				if((pieceY+2 <= 7) && spotEmpty(pieceX-2, pieceY+2, board))
				{
					return "l";
				}
			}
		}
		return "n";
	}
	public static String captureAvailableC(int pieceX, int pieceY, Board board)
	{
		String possibleMove = captureAvailable(pieceX, pieceY, board);
		if(board.board[pieceX][pieceY] != null && board.board[pieceX][pieceY].type == 1)
		{
			possibleMove = possibleMove + captureAvailableR(pieceX, pieceY, board);
		}
		else
		{
			possibleMove = possibleMove + "p";
		}
		
		return possibleMove;
	}
	
	public static boolean captureBool(int pieceX, int pieceY, Board board)
	{
		if(!captureAvailableC(pieceX, pieceY, board).equals("nn") && !captureAvailableC(pieceX, pieceY, board).equals("np"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}

	
