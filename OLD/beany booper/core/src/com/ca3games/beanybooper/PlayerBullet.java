package com.ca3games.beanybooper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerBullet {

	public Rectangle rect;
	int direction;
	
	public PlayerBullet(int d, float x, float y)
	{
		direction = d;
		switch (direction)
		{
		case 4: rect = new Rectangle(x, y, 32, 16);
			break;
		case 6: rect = new Rectangle (x, y, 32, 16);
			break;
		case 8: rect = new Rectangle (x, y, 16, 32);
			break;
		case 2: rect = new Rectangle (x, y, 16, 32);
			break;
		}
	}
	
	public void update()
	{
		Vector2 speed = Vector2.Zero;
		float spd = 5;
		
		switch(direction)
		{
		case 4: speed.x = -spd; speed.y = 0;
			break;
		case 6: speed.x = spd; speed.y = 0;
			break;
		case 8: speed.y = spd; speed.x = 0;
			break;
		case 2: speed.y = -spd; speed.x = 0;
			break;
		}
		
		rect.x += speed.x;
		rect.y += speed.y;
	}
	
	public void Draw(ShapeRenderer shape)
	{
		shape.setColor(Color.YELLOW);
		shape.box(rect.x, rect.y, 0, rect.width, rect.height, 1);
	}
	
}
