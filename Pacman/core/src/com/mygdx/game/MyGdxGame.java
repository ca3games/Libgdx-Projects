package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	
	Main main;
	
	@Override
	public void create () {
		main = new Main(this);
		this.setScreen(main);
	}

	@Override
	public void render () {
		super.render();
	}
}
