package com.ca3games.defender;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class Input implements InputProcessor {

	MyGdxGame game;
	
	public Input(MyGdxGame g)
	{
		game = g;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch (keycode)
		{
		case Keys.LEFT: game.player.ChangeSpeed(new Vector2(-5,0));
						game.player.Flip(false);
						game.player.left = true;
			break;
		case Keys.RIGHT: game.player.ChangeSpeed(new Vector2(5,0));
						game.player.Flip(true);
						game.player.right = true;
			break;
		case Keys.UP: 	game.player.ChangeSpeed(new Vector2(0,2.5f));
						game.player.up = true;
			break;
		case Keys.DOWN: game.player.ChangeSpeed(new Vector2(0,-2.5f));
						game.player.down = true;
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		switch (keycode)
		{
		case Keys.LEFT: game.player.left = false;
			break;
		case Keys.RIGHT: game.player.right = false;
			break;
		case Keys.UP: game.player.up = false;
			break;
		case Keys.DOWN: game.player.down = false;
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
