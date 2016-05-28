package com.Engine;

import com.Engine.InputReceiver.Input_keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor implements InputProcessor {

	InputReceiver P1;
	int keys_pressed;
	public AnimationManager_GUI_HitboxEditor animation;
	
	public MyInputProcessor(InputReceiver P1input, AnimationManager_GUI_HitboxEditor a)
	{
		P1 = P1input;
		keys_pressed = 0;
		animation = a;
	}
	
	public void Update()
	{
		if (keys_pressed == 0)
		{
			P1.ReceiveInput(Input_keys.idle);
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		keys_pressed++;
		
		switch(keycode)
		{
		case Keys.LEFT: 
			P1.ReceiveInput(Input_keys.left);
			break;
		case Keys.RIGHT:
			P1.ReceiveInput(Input_keys.right);
			break;
		case Keys.DOWN:
			P1.ReceiveInput(Input_keys.down);
			break;
		case Keys.UP:
			P1.ReceiveInput(Input_keys.up);
			break;
		case Keys.F1:
			animation.SetLoadFile();
			break;
		}
		
		// Diagonal Jump
		if (keycode == Keys.UP && Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			P1.ReceiveInput(Input_keys.jump_b);
		}
		if (keycode == Keys.UP && Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			P1.ReceiveInput(Input_keys.jump_f);
		}
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		keys_pressed--;
		
		switch(keycode)
		{
		case Keys.LEFT: 
			P1.ReceiveInput(Input_keys.left_r);
			break;
		case Keys.RIGHT:
			P1.ReceiveInput(Input_keys.right_r);
			break;
		case Keys.DOWN:
			P1.ReceiveInput(Input_keys.down_r);
			break;
		case Keys.UP:
			P1.ReceiveInput(Input_keys.up_r);
			break;
		}

		// Diagonal Jump
		if (keycode == Keys.UP && Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			P1.ReceiveInput(Input_keys.jump_b_r);
		}
		if (keycode == Keys.UP && Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			P1.ReceiveInput(Input_keys.jump_f_r);
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
