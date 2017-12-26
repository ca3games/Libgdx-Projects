package com.ca3games.beanybooper;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGdxGame implements Screen {
	SpriteBatch batch;
	Player player;
	Map mapita;
	ShapeRenderer shape;
	MyGame game;
	EnemyManager enemies;
	
	public MyGdxGame(MyGame g)
	{
		game = g;
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		player.Dispose();
	}

	@Override
	public void show() {
		
		batch = new SpriteBatch();
		player = new Player();
		mapita = new Map();
		shape = new ShapeRenderer();
		shape.setColor(Color.GREEN);
		player.mapita = mapita;
		mapita.player = player;
		enemies = new EnemyManager(mapita);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.Update();
		mapita.update();
		enemies.Update();
		
		if (player.rect.y < 0)
		{
			game.setScreen(new GameOver(game));
		}
		
		for (EnemyBall i : enemies.enemies)
		{
			if (i.rect.overlaps(player.rect))
			{
				game.setScreen(new GameOver(game));
			}
		}
		
		List<EnemyBall> enemies2 = enemies.enemies;
		for (int l = 0; l < enemies2.size(); l++) {
			EnemyBall i = enemies2.get(l);
			List<PlayerBullet> bullets = player.bullets;
			
			for (int k = 0; k < bullets.size(); k++) {
				PlayerBullet j = bullets.get(k);
				if (i.rect.overlaps(j.rect))
				{
					enemies.enemies.remove(l);
					player.bullets.remove(k);
				}
			}
		}
		
		shape.begin(ShapeType.Filled);
		mapita.draw(shape);
		enemies.Draw(shape);
		player.DrawShapes(shape);
		shape.end();
		
		batch.begin();
		player.Draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
