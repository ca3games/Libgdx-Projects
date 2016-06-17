package com.ca3games.defender;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	public Sprite texture;
	public Rectangle position;
	public boolean left, right, up, down;
	public Vector2 speed;
	float inertia;
	
	public Player()
	{
		texture = new Sprite(new Texture(Gdx.files.internal("ship.png")));
		position = new Rectangle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 
				texture.getWidth(), texture.getHeight());
		texture.setPosition(position.x, position.y);
		left = false;
		right = false;
		up = false;
		down = false;
		inertia = 0.1f;
		speed = new Vector2(0,0);
	}
	
	public void Update()
	{
		if (speed.x != 0)
		{
			if (speed.x < 0 && !left)
			{
				speed.x += inertia;
			}
			if (speed.x > 0 && !right)
			{
				speed.x -= inertia;
			}
		}
		if (speed.y != 0)
		{
			if (speed.y < 0 && !up)
			{
				speed.y += inertia;
			}
			if (speed.y > 0 && !down)
			{
				speed.y -= inertia;
			}
		}
		
		
		texture.translate(speed.x, speed.y);
		position.setPosition(texture.getX(), texture.getY());
	}
	
	public void Flip(boolean right)
	{
		if (right)
		{
			texture.setFlip(false, false);
		}
		else
		{
			texture.setFlip(true, false);
		}
	}
	
	public void ChangeSpeed(Vector2 new_speed)
	{
		speed = new_speed;
	}
	
	public void Draw(SpriteBatch batch)
	{
		texture.draw(batch, 1);
	}
	
	public void Dispose()
	{
		
	}
}
