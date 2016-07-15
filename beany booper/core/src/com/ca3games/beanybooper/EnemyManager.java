package com.ca3games.beanybooper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EnemyManager {
	
	public int Level;
	public List<EnemyBall> enemies;
	public int score;
	public Random random;
	public Map mapa;
	
	public EnemyManager(Map mapita)
	{
		enemies = new ArrayList<EnemyBall>();
		random = new Random();
		mapa = mapita;
		Level = 1;
	}
	
	public void Update()
	{
		if (enemies.isEmpty())
		{
			for (int i = 0; i < Level; i++)
			{
				int x = random.nextInt(Gdx.graphics.getWidth());
				enemies.add(new EnemyBall(x, Gdx.graphics.getHeight()));
			}
		}
		
		for (EnemyBall i : enemies)
		{
			if (mapa.Collides(i.rect.x, i.rect.y))
			{
				if (i.right)
				{
					i.right = false;
				}
				else
				{
					i.right = true;
				}
				if (i.rect.y < Gdx.graphics.getHeight()/4)
				{
					i.up = true;
				}
				if (i.rect.y > Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/4)
				{
					i.up = false;
				}
			}
			i.Update();
			
			if (!i.right && i.rect.x < 0)
			{
				i.right = true;
			}
			if (i.right && i.rect.x > Gdx.graphics.getWidth())
			{
				i.right = false;
			}
			if (!i.up && i.rect.y < 32)
			{
				i.up = true;
			}
			if (i.up && i.rect.y > Gdx.graphics.getHeight())
			{
				i.up = false;
			}
		}
	}

	
	public void Draw(ShapeRenderer shape)
	{
		for (EnemyBall i : enemies)
		{
			i.Draw(shape);
		}
	}
	
}
