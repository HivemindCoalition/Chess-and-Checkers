package com.taycaldwell.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import com.taycaldwell.game.RuleSet;

/**
 * =============Developers=============
 * Taylor Caldwell - tcaldwel@nmsu.edu
 * William Kluegel -
 * Carl Williams -
 * ====================================
 * Do not reuse this code in any projects.
 * 
 */
 

public class GameComponent extends Canvas implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	
	private static final long serialVersionUID = 1L;

	public Art art = new Art();
	
	private static GameComponent game;
	private static JFrame window;
	
	private boolean running = false;
	private int width, height, cursorX, cursorY, pieceX, pieceY;;
	private Thread thread;
	private BufferedImage frame;
	private Board board;
	private Board board2;
	
	private StartMenu startMenu;
	private PauseMenu pauseMenu;
	private OptionsMenu optionsMenu;
	
	private int turnCounter;
	private int winner;
	private boolean pieceSelected = false;
	private String gameMode = "";
	private boolean checkCheck = false; 
	private boolean jumpCheck = false; 
	private boolean jumpM = false; 
	
	private int epX = -1; 
	
	public enum Scene 
	{
		CHECKERS, CHESS, START, PAUSE, OPTIONS
	}
	
	Scene scene;

	/**
	 * Constructor
	 */
	public GameComponent(int width, int height)
	{
		this.width = width;
		this.height = height;
		addMouseListener(this); 
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	/**
	 * Start Method
	 * This method starts the game by creating a new thread, and 
	 * running it.
	 */
	public void start()
	{
		if(!running) 
		{
			running = true; 
			thread = new Thread(this); 
			thread.start(); 
		}
	}
	
	/**
	 * Initialize starting game objects and entities.
	 */
	public void init()
	{	
		turnCounter = 0;
		winner = -1;
		try{
			Art.loadArt();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		board = new Board();
		startMenu = new StartMenu(game.width, game.height);
		pauseMenu = new PauseMenu(game.width, game.height);
		optionsMenu = new OptionsMenu(game.width, game.height);
		scene = Scene.START;
		
	}
	
	
	/**
	 * Run Method
	 * This method runs the game loop. It first initializes everything
	 * needed, and then continuously draws frames, which are updated when
	 * the user interacts with the game.
	 * This method is called when the thread is started, and stops when the
	 * thread dies.
	 */
	@Override
	public void run()
	{
		init();
		
		while(running)
		{
			drawFrame(); 
			
			try
			{
				Thread.sleep(33);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} 
	} 
	
	
	
	/**
	 * Draw Frame Method
	 * This method creates an image object to be used as a frame.
	 * All game graphics are then drawn to a single frame object,
	 * which is then draws itself to the game.
	 */
	public void drawFrame()
	{
		if(frame == null){
			frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}
		
		
		Graphics g = frame.getGraphics();
		drawGame(g, art);
		g.dispose(); 	
		
		Graphics g1= getGraphics();
		g1.drawImage(frame, 0, 0, width, height, null);
		g1.dispose(); 
		
	}
	
	
	/**
	 * Draw Game Method
	 * This method calls all game objects to draw themselves.
	 * @param g
	 */
	public void drawGame(Graphics g, Art art)
	{	
		if(scene == Scene.CHECKERS)
		{
			board.draw(g, art);
			
			for(int i = 0; i < 8; i++)
			{
				for( int j = 0; j < 8; j++)
				{
					if(board.board[i][j] != null)
					{
						board.board[i][j].draw(g, art);
					}
				}
			}
			
			winCheck(g);
		}
		
		else if(scene == Scene.CHESS)
		{
			board.draw(g, art); 
			
			for(int i = 0; i < 8; i++)
			{
				for( int j = 0; j < 8; j++)
				{
					if(board.board[i][j] != null)
					{
						board.board[i][j].draw(g, art);
					}
				}
			}
			winCheck(g);
		}
		
		else if(scene == Scene.START)
		{
			startMenu.draw(g);
		}
		
		else if(scene == Scene.PAUSE)
		{
			pauseMenu.draw(g);
		}
		
		else if(scene == Scene.OPTIONS)
		{
			optionsMenu.draw(g);
		}
		
		else{}
		
		if(optionsMenu.debugMenu){drawDebugMenu(g);}
		
	}
	
	public void drawDebugMenu(Graphics g)
	{
		int coordX, coordY;
		if((cursorX >= 0 && cursorX <= width) && (cursorY >= 0 && cursorY <= height)){
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.PLAIN, 18));
			coordX = cursorX/100;
			coordY = cursorY/100;
			g.drawString("CursorX: " + cursorX, 820, 30);
			g.drawString("CursorY: " + cursorY, 820, 55);
			g.drawString("CoordX: " + coordX, 820, 80);
			g.drawString("CoordY: " + coordY, 820, 105);
		}
	}
	
	public void winCheck(Graphics g)
	{
		if(winner != -1)
		{	
			g.setColor(Color.BLACK);
			g.fillRect(0, 345, 800, 110);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 350, 800, 100);
			
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			if(winner == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString("PLAYER BLACK IS THE WINNER!", 65, 410);
			}
			else if(winner == 1)
			{	
				if(gameMode == "Checkers"){
					g.setColor(Color.RED);
					g.drawString("PLAYER RED IS THE WINNER!", 95, 410);
				}
				else{
					g.setColor(Color.WHITE);
					g.drawString("PLAYER WHITE IS THE WINNER!", 95, 410);
				}
			}
		}
	}
	
	/**
	 * Mouse Pressed Method
	 * This method listens for clicks from the user, and translates
	 * these clicks into appropriate actions based off the current Scene
	 * that is being displayed to the user.
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(winner != -1)
		{
			winner = -1;
			startMenu.choice = StartMenu.Choice.INITIAL;
			scene = Scene.START;
			gameMode = "";
			init();
		}
		
		else if(scene == Scene.CHECKERS) 
		{
			int i = e.getX() / 100;
			int j = e.getY() / 100; 
			
			if(!jumpCheck && !pieceSelected && board.board[i][j] != null && board.board[i][j].player == currentPlayer())
			{
				pieceX = i;
				pieceY = j;
				pieceSelected = true; 
				board.board[i][j].selected = true;
			}
			else if(pieceSelected && !jumpCheck)
			{
				if(!(pieceX == i && pieceY == j))
				{
					if(RuleSet.check(i, j, pieceX, pieceY, currentPlayer(), board, gameMode))
					{
						if(RuleSet.jumpMove(i, j, pieceX, pieceY, board))
						{
							RuleSet.removePiece(i, j, pieceX, pieceY, board);
							jumpM = true;
						}
						
						board.movePiece(pieceX, pieceY, i, j);
						if(j == 0 && !RuleSet.isKing(i, j, board))
						{
							board.board[i][j].type = 1;
						}
							
						if(jumpM && RuleSet.captureBool(i, j, board))
						{
							jumpCheck = true;
							pieceX = i;
							pieceY = j;
						}
						
						jumpM = false;
						
						if(!jumpCheck)
						{
							pieceSelected = false;
							board.board[i][j].selected = false;
							endTurn();
						}
					}
					else
					{
						pieceSelected = false;
						board.board[pieceX][pieceY].selected = false;
					}
				}
				else
				{
					pieceSelected = false;
					if(board.board[i][j] != null){board.board[i][j].selected = false;}
				}
			}
			else if(jumpCheck)
			{
				if(RuleSet.captureBool(pieceX, pieceY, board))
				{
					if(RuleSet.check(i, j, pieceX, pieceY, currentPlayer(), board, gameMode) && RuleSet.jumpMove(i, j, pieceX, pieceY, board))
					{
						board.movePiece(pieceX, pieceY, i, j); 
						
						if(j == 0 && !RuleSet.isKing(i, j, board))
						{
							board.board[i][j].type = 1;
						}
						
						RuleSet.removePiece(i, j, pieceX, pieceY, board);
						
						pieceX = i;
						pieceY = j; 
						if(!RuleSet.captureBool(pieceX, pieceY, board))
						{
							jumpCheck = false;
							pieceSelected = false;
							board.board[pieceX][pieceY].selected = false;
							endTurn();
						}
					}
					
				}
				else
				{
					jumpCheck = false;
					pieceSelected = false;
					board.board[pieceX][pieceY].selected = false;
					endTurn();
				}
			}
		}
		
		else if(scene == Scene.CHESS) 
		{
			int i = e.getX() / 100;
			int j = e.getY() / 100;
			
			if(!pieceSelected && board.board[i][j] != null && board.board[i][j].player == currentPlayer())
			{
				pieceX = i;
				pieceY = j;
				pieceSelected = true;
				board.board[i][j].selected = true;
			}
			else if(pieceSelected)
			{
				if(!(pieceX == i && pieceY == j))
				{
					if(checkCheck && !RuleSet.inCheckStill(i, j, pieceX, pieceY, board, currentPlayer(), gameMode)) 
					{
						checkCheck = false;
						board.removePiece(i, j); 
						board.movePiece(pieceX, pieceY, i, j);
						if(j == 0 && board.board[i][j].type == 0){
                            board.board[i][j].type = 4;
                        }
						pieceSelected = false;
						board.board[i][j].selected = false;

						endTurn();
					}
					else if(!checkCheck && ((epX == (7 - i) && RuleSet.enPassant(i, j, pieceX, pieceY, board)) || (RuleSet.check(i, j, pieceX, pieceY, currentPlayer(), board, gameMode)))) //else do this if rules are followed
					{
						epX = -1;
						if(RuleSet.pawnMoveTwo(i, j, pieceX, pieceY, board))
						{
							epX = i;
						}
						
						board.removePiece(i, j);
						board.movePiece(pieceX, pieceY, i, j);
						
						if(j == 0 && board.board[i][j].type == 0){
                            board.board[i][j].type = 4;
                        }
						pieceSelected = false;
						board.board[i][j].selected = false;
						
						board2 = board;
						board2.rotateBoard();
						
						if(RuleSet.inCheck(board2, otherPlayer(), gameMode))
						{
							checkCheck = true;
							if(RuleSet.inCheckmate(board2, otherPlayer(), gameMode))
							{
								winner = currentPlayer();
							}
						}
						
						board2.rotateBoard();
						
						endTurn();
					}
					else
					{
						pieceSelected = false;
						board.board[pieceX][pieceY].selected = false;
					}
				}
				else
				{
					pieceSelected = false;
					board.board[pieceX][pieceY].selected = false;
				}
			}
		}
		
		else if(scene == Scene.START)
		{
			int x = e.getX();
			int y = e.getY();
			
			if(startMenu.choice == StartMenu.Choice.INITIAL)
			{	
				startMenu.choice = StartMenu.Choice.CHOOSE_GAME;
			}
			else if(startMenu.choice == StartMenu.Choice.CHOOSE_GAME)
			{
				if((x > 305 && x <= 455) && (y > 570 && y <= 610))
				{
					board = new ChessBoard();
					scene = Scene.CHESS;
					gameMode = "Chess";
				    startMenu.choice = StartMenu.Choice.INITIAL;
				}
				
				else if((x > 305 && x <= 455) && (y > 520 && y <= 560))
				{
					board = new CheckersBoard();
					scene = Scene.CHECKERS;
					gameMode = "Checkers";
				    startMenu.choice = StartMenu.Choice.INITIAL;
				}
			}
			else{}
			
			}
		
		else if(scene == Scene.PAUSE) 
		{
			int x = e.getX();
			int y = e.getY();
			
			if(pauseMenu.choice == PauseMenu.Choice.INITIAL)
			{
				if((x > 305 && x <= 505) && (y > 520 && y <= 560))
				{
					optionsMenu.choice = OptionsMenu.Choice.INITIAL;
					scene = Scene.OPTIONS;
				}
				
				else if((x > 305 && x <= 505) && (y > 570 && y <= 610))
				{
					if(gameMode == "Checkers"){scene = Scene.CHECKERS;}
					if(gameMode == "Chess"){scene = Scene.CHESS;}
				}			
				
				else if((x > 305 && x <= 505) && (y > 620 && y <= 660))
				{
					pauseMenu.choice = PauseMenu.Choice.QUIT;
				}		
				
				else if((x > 305 && x <= 505) && (y > 670 && y <= 710))
				{
					pauseMenu.choice = PauseMenu.Choice.EXIT;
				}		
			}
			
			else if(pauseMenu.choice == PauseMenu.Choice.QUIT)
			{
				if((x > 305 && x <= 505) && (y > 520 && y <= 560))
				{ 
					scene = Scene.START;
					turnCounter = 0;
					jumpCheck = false;
					pieceSelected = false;
					board.board[pieceX][pieceY].selected = false;
				}
				
				else if((x > 305 && x <= 505) && (y > 570 && y <= 610))
				{ 
					pauseMenu.choice = PauseMenu.Choice.INITIAL;
				}			
			}
			
			else if(pauseMenu.choice == PauseMenu.Choice.EXIT) // Exit client - are you sure?
			{
				if((x > 305 && x <= 505) && (y > 520 && y <= 560))
				{
					System.exit(0);
				}
				
				else if((x > 305 && x <= 505) && (y > 570 && y <= 610))
				{
					pauseMenu.choice = PauseMenu.Choice.INITIAL;
				}			
			}
			else{}
		}
		
		else if(scene == Scene.OPTIONS)
		{
			int x = e.getX();
			int y = e.getY();
			
			if(optionsMenu.choice == OptionsMenu.Choice.INITIAL)
			{
				if((x > 250 && x <= 555) && (y > 520 && y <= 560))
				{
					
					if(optionsMenu.debugMenu)
					{
						optionsMenu.debugMenu = false;
						window.setSize(game.width - 300, game.height + 30);
					}
					else
					{
						optionsMenu.debugMenu = true;
						window.setSize(game.width, game.height + 30);
					}
				}	
				
				if((x > 250 && x <= 555) && (y > 570 && y <= 610))
				{
					pauseMenu.choice = PauseMenu.Choice.INITIAL;
					scene = Scene.PAUSE;
				}	
			}
			else{}
		}
		else{}
	}
	
	public int currentPlayer()
	{
		int currP = turnCounter % 2;
		return currP;
	}
	
	public int otherPlayer()
	{
		int otherP = (currentPlayer() + 1) % 2;
		return otherP;
	}
	
	public void endTurn()
	{
		try
		{
			Thread.sleep(750);
		}
		catch (InterruptedException x)
		{
			x.printStackTrace();
		}
		turnCounter++;
		board.rotateBoard();
	}
	
	/**
	 * More Mouse Action Methods
	 * @param e
	 */
	
	@Override
	public void mouseEntered(MouseEvent e){}
	
	@Override
	public void mouseReleased(MouseEvent e){}
	
	@Override
	public void mouseClicked(MouseEvent e){}
	
	@Override
	public void mouseExited(MouseEvent e){}
	
	/**
	 * Mouse Motion Listener Methods
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e){}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		cursorX = e.getX();
		cursorY = e.getY();
	}

	
	/**
	 * Key Listener Methods
	 * @param e
	 */
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(scene == Scene.PAUSE){
			if(gameMode == "Checkers"){scene = Scene.CHECKERS;}
			if(gameMode == "Chess"){scene = Scene.CHESS;}
		}
		else if(scene == Scene.CHESS || scene == Scene.CHECKERS)
			{
				if(e.getKeyCode() == 27 || e.getKeyCode() == 80)
				{
					pauseMenu.choice = PauseMenu.Choice.INITIAL;
					scene = Scene.PAUSE;
				}
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e){}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
	
	/**
	 * Main Method
	 * This method creates a game space and adds that game space to a frame.
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		game = new GameComponent(1100, 800);
		
		window = new JFrame("Chess/Checkers Game");
		window.setSize(game.width - 300, game.height + 30);
		window.add(game);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
		window.setLocationRelativeTo(null); 
		window.setResizable(false);
		window.setVisible(true);
	
		game.start();
		
	} 
	
}
