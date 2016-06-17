package com.ca3games.defender;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new Player();
		
		Gdx.input.setInputProcessor(new Input(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.Update();
		
		batch.begin();
		player.Draw(batch);
		batch.end();
	}
}
