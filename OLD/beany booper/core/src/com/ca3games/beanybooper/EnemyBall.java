package com.ca3games.beanybooper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class EnemyBall {

	public Rectangle rect;
	public boolean right, up;
	
	public EnemyBall(int x, int y)
	{
		rect = new Rectangle (x, y, 32, 32);
	}
	
	public void Update()
	{
		int speed = 3;
		if (right)
		{
			rect.x += speed;
		}
		else
		{
			rect.x -= speed;
		}
		if (up)
		{
			rect.y += speed;
		}
		else
		{
			rect.y -= speed;
		}
	}
	
	public void Draw(ShapeRenderer shape)
	{
		shape.setColor(Color.RED);
		shape.circle(rect.x, rect.y, 16f);
	}
	
}
