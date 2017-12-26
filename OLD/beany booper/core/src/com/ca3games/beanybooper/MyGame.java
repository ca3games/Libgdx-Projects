package com.ca3games.beanybooper;

import com.badlogic.gdx.Game;

public class MyGame extends Game {

	
	@Override
	public void create() {
		this.setScreen(new TitleScreen(this));
	}

	
	@Override
	public void render()
	{
		super.render();
	}
}
