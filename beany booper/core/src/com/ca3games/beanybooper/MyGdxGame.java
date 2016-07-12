package com.ca3games.beanybooper;

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
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.Update();
		mapita.update();
		
		if (player.rect.y < player.rect.height)
		{
			game.setScreen(new GameOver(game));
		}
		
		shape.begin(ShapeType.Filled);
		mapita.draw(shape);
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
