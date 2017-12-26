package com.ca3games.charactereditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import gui.Box_Renderer;
import gui.Chara;
import gui.GUI;
import input.MyInputProcessor;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	GUI gui;
	Chara character;
	Box_Renderer box;
	Texture t_center;
	Image i_center;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		character = new Chara();
		box = new Box_Renderer(character);
		gui = new GUI(character);
		font = new BitmapFont();
		
		MyInputProcessor myinput = new MyInputProcessor(character);
		
		InputMultiplexer multi = new InputMultiplexer();
		multi.addProcessor(myinput);
		multi.addProcessor(gui.stage);
		Gdx.input.setInputProcessor(multi);
		
		t_center = new Texture(Gdx.files.internal("center.png"));
		i_center = new Image(t_center);
		i_center.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3, i_center.getWidth(), i_center.getHeight());
		
	}

	@Override
	public void dispose()
	{
		gui.Dispose();
		character.Dispose();
		t_center.dispose();
		font.dispose();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		character.Draw(batch);
		i_center.draw(batch, 1f);
		box.DrawBoxesLabel(batch, font);
		batch.end();
		
		box.Draw();
		gui.Draw();
	}
}
