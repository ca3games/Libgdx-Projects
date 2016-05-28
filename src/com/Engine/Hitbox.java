package com.Engine;

import java.awt.Point;

import com.badlogic.gdx.math.Rectangle;

public class Hitbox {

	public Rectangle hitbox;
	public Point Center;
	public int Frame, layer;
	
	enum type
	{
		attack_mid, attack_low, attack_high, normal, crouched, block_mid, block_low, nulo, grab
	}
	
	type Type;
	
	public Hitbox(int x, int y, int width, int height, int fra, int lay)
	{
		hitbox = new Rectangle(x, y, width, height);
		Frame = fra;
		layer = lay;
		Center = new Point(100, 100);
		Type = type.nulo;
	}
	
	
	public void SetHitbox(Rectangle hit)
	{
		hitbox = hit;
	}
	
	public void SetHitbox(int x, int y, int width, int height)
	{
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = width;
		hitbox.height = height;
	}
}
