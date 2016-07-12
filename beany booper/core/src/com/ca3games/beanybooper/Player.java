package com.ca3games.beanybooper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	public Texture left, right, up, down;
	public Rectangle rect;
	public int direction;
	public Vector2 speed;
	public float spd;
	public Map mapita;
	
	public Player()
	{
		direction = 8;
		rect = new Rectangle(Gdx.graphics.getWidth()/2-16, Gdx.graphics.getHeight()/2-16, 32, 32);
		left = new Texture(Gdx.files.internal("left.png"));
		right = new Texture(Gdx.files.internal("right.png"));
		up = new Texture(Gdx.files.internal("up.png"));
		down = new Texture(Gdx.files.internal("down.png"));
		spd = 1;
	}
	
	public void Update()
	{
		speed = new Vector2(0,0);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Colision(rect.x-speed.x, rect.y))  { direction = 4;  speed.x -= spd; }
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Colision(rect.x+speed.x, rect.y)) { direction = 6; speed.x += spd; }
		if (Gdx.input.isKeyPressed(Input.Keys.UP) && !Colision(rect.x, (rect.y+rect.height)+speed.y)
				)  { direction = 8; speed.y += spd; }
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Colision(rect.x, rect.y-speed.y))  { direction = 2; speed.y -= spd; }
		
		rect.x += speed.x;
		rect.y += speed.y;
	}
	
	boolean Colision(float f, float y)
	{
		for (Block i : mapita.mapita)
		{
			if (i.rect.contains(f, y))
			{
				return true;
			}
		}
		return false;
	}
	
	public void Draw(SpriteBatch batch)
	{
		switch(direction)
		{
			case 8: batch.draw(up, rect.x, rect.y);
				break;
			case 6: batch.draw(right, rect.x, rect.y);
				break;
			case 2: batch.draw(down, rect.x, rect.y);
				break;
			case 4: batch.draw(left, rect.x, rect.y);
		}
	}
	
	
	public void Dispose()
	{
		right.dispose();
		left.dispose();
		up.dispose();
		down.dispose();
	}
	
}
