package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import characters.Player;

public class Main implements Screen {

	MyGdxGame game;
	OrthographicCamera camera;
	SpriteBatch batch;
	Player player;
	
	public Main(MyGdxGame g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		player = new Player();
		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		player.sprite.draw(batch);
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

	@Override
	public void dispose() {

		batch.dispose();
		
	}

}
