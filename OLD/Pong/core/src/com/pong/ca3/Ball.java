package com.pong.ca3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Ball extends Paddle {

	boolean Up, Right;
	
	public Ball(int x, int y, float sp, int width, int height) {
		super(x, y, sp, width, height);
		Up = true;
		Right = true;
	}
	
	public void Update()
	{
		if (Up)
		{
			this.body.y -= this.speed;
		}
		else
		{
			this.body.y += this.speed;
		}
		if (Right)
		{
			this.body.x -= this.speed;
		}
		else
		{
			this.body.x += this.speed;
		}
		
		if (this.body.y < 0)
		{
			Up = false;
		}
		if (this.body.y > Gdx.graphics.getHeight() - this.body.height)
		{
			Up = true;
		}
	}
	
	public void Collision (Rectangle player, Rectangle ai)
	{
		if (player.overlaps(body))
		{
			Right = false;
		}
		if (ai.overlaps(body))
		{
			Right = true;
		}
	}

}
