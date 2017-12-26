package com.Engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HitBox_Editor implements ApplicationListener {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	Player_HitBoxEditor player;
	BitmapFont font;
	MyInputProcessor_HitboxEditor input_editor;
	ShapeRenderer Box;
		
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		camera.position.set(w/2, h/2, 0);
		camera.update();
		
		player = new Player_HitBoxEditor();
		input_editor = new MyInputProcessor_HitboxEditor();
		input_editor.animation = player.animation_editor;
		
		Gdx.input.setInputProcessor(input_editor);
		
		font = new BitmapFont();
		Box = new ShapeRenderer();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.Dispose();
		font.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		player.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.Draw(batch);
		player.RenderGUI(batch);
		batch.end();
		
		Box.begin(ShapeType.Filled);
		player.DrawShapes(Box);
		Box.end();
		
		Box.begin(ShapeType.Line);
		Box.setColor(Color.ORANGE);
		player.DrawShapesCurrent(Box);
		Box.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
}
