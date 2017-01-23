package com.pong.ca3;
import com.badlogic.gdx.math.*;

public class Paddle {
	public Vector2 position;
	public float speed;
	public Rectangle body;
	
	public Paddle(int x, int y, float sp, int width, int height)
	{
		position = new Vector2(x, y);
		speed = sp;
		body = new Rectangle(x, y, width, height);
	}
}
