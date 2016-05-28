package com.Engine;

import java.util.ArrayList;
import java.util.List;

public class Frame {

	public List<Hitbox> hitbox;
	
	public Frame()
	{
		hitbox = new ArrayList<Hitbox>();
	}
	
	public void AddFrame(int x, int y, int width, int height, int frame, int layer)
	{
		if (hitbox == null)
		{
			hitbox = new ArrayList<Hitbox>();
		}
		hitbox.add(new Hitbox(x, y, width, height, frame, layer));
	}
}
