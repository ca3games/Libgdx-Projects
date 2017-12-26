package com.ca3games.superextremefireofdoom;

import screens.GameMap;
import screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	
	public GameMap mapa;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new TitleScreen(this));
	}
	
	@Override
	public void render()
	{
		super.render();
	}

}
