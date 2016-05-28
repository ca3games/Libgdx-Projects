package com.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PhysicsManager {

	int x, y;
	float Speed_x, Speed_y, walk_speed, y_jump_speed;
	float slow_walk_speed;
	int ground, right_corner;
	public boolean OnGround;
	float DeltaTime;
	float fast_walk_speed;
	public Rectangle groundRect;
	public float x_force, y_force;
		
	public PhysicsManager(int xpos, int ypos)
	{
		x = xpos;
		y = ypos;
		
		fast_walk_speed = 5;
		walk_speed = fast_walk_speed;
		slow_walk_speed = 4;
		y_jump_speed = 17;
		
		ground = y / 4;
		DeltaTime = 0.6f;
		
		OnGround = false;
		
		right_corner = (int) (Gdx.graphics.getWidth() - 50);
		
		groundRect = new Rectangle(0, ground, Gdx.graphics.getWidth(), -ground);
		
		x_force = 0;
		y_force = 0;
		
	}
	
	public void Update()
	{	
		if (Speed_x < 0 && SpaceLeft()) { x += Speed_x + x_force;	}
		if (Speed_x > 0 && SpaceRight()) { x += Speed_x + x_force; }
		
		y += Speed_y + y_force;
	}
	
	public void CheckGround()
	{
		Speed_y = 0;
		OnGround = true;
		y = ground;
	}
	
	public void GravityFall()
	{
		Speed_y -= DeltaTime;
		OnGround = false;
	}
	
	
	public void Jump()
	{
		Speed_y = y_jump_speed;
	}
	
	public boolean SpaceRight()
	{
		if (x < right_corner)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean SpaceLeft()
	{
		if (x > -100)
		{
			return true;
		}
		
		return false;
	}
	
	public void RenderHitBoxes(ShapeRenderer Box)
	{
		Box.setColor(Color.GREEN);
		Box.rect(groundRect.x, groundRect.y, groundRect.width, groundRect.height);
	}
}
