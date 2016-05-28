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

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	Player player, player_2;
	MyInputProcessor myInput;
	InputReceiver P1Input;
	BitmapFont font;
	ShapeRenderer Box;
		
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		player = new Player(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2, true);
		player_2 = new Player(Gdx.graphics.getWidth()/5*3, Gdx.graphics.getHeight()/2, false);
		
		camera.position.set(w/2, h/2, 0);
		camera.update();
		
		//This receives the keys being pressed from myInput
		//It then calls the player status to change it
		P1Input = new InputReceiver(player);
		player.state.inputreceiver = P1Input;
		
		myInput = new MyInputProcessor(P1Input, player.animation);
		Gdx.input.setInputProcessor(myInput);
		
		//player.animation.LoadFileJSON("/home/cristian/Escritorio/ATHENA/athena.json");
		//player_2.animation.LoadFileJSON("/home/cristian/Escritorio/ATHENA/athena.json");
		player.animation.LoadFileJSON("C:/Users/cristian/Desktop/athena.json");
		player_2.animation.LoadFileJSON("C:/Users/cristian/Desktop/athena.json");
		
		font = new BitmapFont();
		Box = new ShapeRenderer();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.Dispose();
		font.dispose();
		player_2.Dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		P1Input.Update();
		myInput.Update();
		player.update(player_2);
		player_2.update(player);
		
		Box.begin(ShapeType.Line);
		Box.setColor(Color.ORANGE);
		player.DrawHitboxes(Box);
		player_2.DrawHitboxes(Box);
		Box.end();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.Draw(batch);
		player_2.Draw(batch);
		player.DrawInfoHitbox(batch);
		P1Input.Draw(batch, font);
		
		batch.end();
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
