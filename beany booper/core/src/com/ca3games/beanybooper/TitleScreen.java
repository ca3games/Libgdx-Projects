package com.ca3games.beanybooper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen implements Screen {

	public BitmapFont font;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public MyGame game;
	
	public TitleScreen(MyGame g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		font = new BitmapFont();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl20.glClearColor(0, 0, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			game.setScreen(new MyGdxGame(game));
		}
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		font.draw(batch, "WELCOME TO BEANY BOOPER", 100, Gdx.graphics.getHeight()/2);
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
		// TODO Auto-generated method stub
		
	}

}
