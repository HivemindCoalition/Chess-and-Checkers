package org.taycaldwell.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Art
{
	public static BufferedImage lightSquare;
	public static BufferedImage darkSquare;
	public static BufferedImage checkersLightKing;
	public static BufferedImage checkersLightPawn;
	public static BufferedImage checkersDarkKing;
	public static BufferedImage checkersDarkPawn;
	public static BufferedImage checkersHighlight;
	public static BufferedImage whiteSquare;
	public static BufferedImage blackSquare;
	public static BufferedImage chessLightPawn;
	public static BufferedImage chessDarkPawn;
	public static BufferedImage chessLightKnight;
	public static BufferedImage chessDarkKnight;
	public static BufferedImage chessLightQueen;
	public static BufferedImage chessDarkQueen;
	public static BufferedImage chessLightKing;
	public static BufferedImage chessDarkKing;
	public static BufferedImage chessLightBishop;
	public static BufferedImage chessDarkBishop;
	public static BufferedImage chessLightCastle;
	public static BufferedImage chessDarkCastle;
	public static BufferedImage pawnHighlight;
	public static BufferedImage kingHighlight;
	public static BufferedImage queenHighlight;
	public static BufferedImage castleHighlight;
	public static BufferedImage knightHighlight;
	public static BufferedImage bishopHighlight;
	
	public static void loadArt() throws IOException
	{
		//checkers
		lightSquare = ImageIO.read(Art.class.getResource("/square1.gif"));
		darkSquare = ImageIO.read(Art.class.getResource("/square2.gif"));
		checkersLightKing = ImageIO.read(Art.class.getResource("/king1.gif"));
		checkersLightPawn = ImageIO.read(Art.class.getResource("/piece1.gif"));
		checkersDarkKing = ImageIO.read(Art.class.getResource("/king2.gif"));
		checkersDarkPawn = ImageIO.read(Art.class.getResource("/piece2.gif"));
		checkersHighlight = ImageIO.read(Art.class.getResource("/checkershighlight.gif"));
		
		//chess
		whiteSquare = ImageIO.read(Art.class.getResource("/whitesquare.gif"));
		blackSquare = ImageIO.read(Art.class.getResource("/blacksquare.gif"));
		chessLightPawn = ImageIO.read(Art.class.getResource("/whitepawn.gif"));
		chessDarkPawn = ImageIO.read(Art.class.getResource("/blackpawn.gif"));
		chessLightKnight = ImageIO.read(Art.class.getResource("/whiteknight.gif"));
		chessDarkKnight = ImageIO.read(Art.class.getResource("/blackknight.gif"));
		chessLightQueen = ImageIO.read(Art.class.getResource("/whitequeen.gif"));
		chessDarkQueen = ImageIO.read(Art.class.getResource("/blackqueen.gif"));
		chessLightKing = ImageIO.read(Art.class.getResource("/whiteking.gif"));
		chessDarkKing = ImageIO.read(Art.class.getResource("/blackking.gif"));
		chessLightBishop = ImageIO.read(Art.class.getResource("/whitebishop.gif"));
		chessDarkBishop = ImageIO.read(Art.class.getResource("/blackbishop.gif"));
		chessLightCastle = ImageIO.read(Art.class.getResource("/whitecastle.gif"));
		chessDarkCastle = ImageIO.read(Art.class.getResource("/blackcastle.gif"));
		
		pawnHighlight = ImageIO.read(Art.class.getResource("/pawnhighlight.gif"));
		kingHighlight = ImageIO.read(Art.class.getResource("/kinghighlight.gif"));
		queenHighlight = ImageIO.read(Art.class.getResource("/queenhighlight.gif"));
		bishopHighlight = ImageIO.read(Art.class.getResource("/bishophighlight.gif"));
		castleHighlight = ImageIO.read(Art.class.getResource("/castlehighlight.gif"));
		knightHighlight = ImageIO.read(Art.class.getResource("/knighthighlight.gif"));
	}

}
