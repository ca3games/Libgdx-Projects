package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import character.Chara;

public class Level01 implements Screen{

	Blocks level;
	SpriteBatch batch;
	OrthographicCamera camera;
	ShapeRenderer shape;
	Chara chara;
		
	@Override
	public void show() {
		level = new Blocks();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		shape.setColor(Color.GREEN);
		chara = new Chara();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		chara.Update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			level.Draw(batch);
		batch.end();
		
		shape.begin(ShapeType.Filled);
			level.Draw_Rect(shape);
		shape.end();
		
		batch.begin();
			chara.Draw(batch);
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
		
		level.dispose();
		chara.Dispose();
	}

	
	
	
}
