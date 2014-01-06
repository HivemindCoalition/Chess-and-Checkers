package com.taycaldwell.game;

import java.awt.*;


public class Piece extends Entity
{

	int player;
	int active;
	boolean selected = false;
	int type = 0;
	boolean hasCaptured = false;
	
	public Piece(int x, int y, int p)
	{
		this.x = x;
		this.y = y;
		player = p;
		active = 1;
	}
	
	public Piece(int x, int y, int p, int t)
	{
		this.x = x;
		this.y = y;
		player = p;
		active = 1;
		type = t;
	}
	
	public void draw(Graphics g, Art art)
	{			
	}
	

}