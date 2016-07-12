package com.ca3games.beanybooper;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Block {

	public Rectangle rect;
	
	public Block(int x, int y, int width, int height)
	{
		rect = new Rectangle(x, y, width, height);
	}
	
	public void Draw(ShapeRenderer shape)
	{
		shape.box(rect.x, rect.y, 0, rect.width, rect.height, 1);
	}
	
}
