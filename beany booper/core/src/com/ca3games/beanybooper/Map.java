package com.ca3games.beanybooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Map {

	public List<Block> mapita;
	boolean empty;
	Random random;
	int top;
	public Player player;
	
	public Map()
	{
		mapita = new ArrayList<Block>();
		empty = true;
		random = new Random();
		top = Gdx.graphics.getHeight() - 400;
	}
	
	public void update()
	{
		if (empty && mapita.size() < 5)
		{
			for (int x = 1; x < 5; x++)
			{
				int height = random.nextInt(150) + 32;
				mapita.add(new Block(random.nextInt(Gdx.graphics.getWidth()), top + 400, 32, height));
			}
			
			empty = false;
		}
		
		for (int j = 0; j < mapita.size(); j++) {
			Block i = mapita.get(j);
			if (i.rect.y > top && i.rect.y < top+1)
			{
				empty = true;
			}
			if (i.rect.overlaps(player.rect))
			{
				player.rect.y -= 0.6f;
				if (player.rect.x <= i.rect.x)
				{
					player.rect.x -= 1;
				}
				else
				{
					player.rect.x += 1;
				}
			}
			
			i.rect.y -= 0.5;
			
			if (i.rect.y < -200)
			{
				mapita.remove(j);
			}
		}
		
	}
	
	public boolean Collides(float x, float y)
	{
		for (Block i : mapita)
		{
			if (i.rect.contains(x, y))
			{
				return true;
			}
		}
			
		return false;
	}
	
	public void draw(ShapeRenderer shape)
	{
		shape.setColor(Color.GREEN);
		for (Block i : mapita)
		{
			i.Draw(shape);
		}
	}
}
