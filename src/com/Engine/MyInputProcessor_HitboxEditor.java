package com.Engine;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor_HitboxEditor implements InputProcessor {

	public Animation_Manager animation;
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (keycode == Keys.LEFT)
		{
			animation.IndexLess();
		}
		if (keycode == Keys.RIGHT)
		{
			animation.IndexPlus();
		}
		if (keycode == Keys.SPACE)
		{
			animation.Play_Animation();
		}
		if(keycode == Keys.PLUS)
		{
			animation.AddFrame();
		}
		if (keycode == Keys.MINUS)
		{
			animation.DeleteHitbox();
		}
		
		if (keycode == Keys.UP)
		{
			animation.LayerUp();
		}
		if (keycode == Keys.DOWN)
		{
			animation.LayerDown();
		}
		if (keycode == Keys.Q)
		{
			animation.SaveHitbox();
		}
		if (keycode == Keys.W)
		{
			animation.EditWidth();
		}
		if (keycode == Keys.E)
		{
			animation.CopySize();
		}
		if (keycode == Keys.Z)
		{
			animation.SetSaveFile();
		}
		if (keycode == Keys.X)
		{
			animation.SetLoadFile();
		}
		if (keycode == Keys.A)
		{
			animation.CopyLocation();
		}
		if (keycode == Keys.S)
		{
			animation.PasteLocation();
		}
		if (keycode == Keys.N)
		{
			animation.AddAnimation();
		}
		if (keycode == Keys.M)
		{
			animation.RemoveAnimation();
		}
		if(keycode == Keys.J)
		{
			animation.UpAnimation();
		}
		if (keycode == Keys.K)
		{
			animation.DownAnimation();
		}
		if (keycode == Keys.P)
		{
			animation.SetCenterPosition();			
		}
		if (keycode == Keys.O)
		{
			animation.PasteCenter();
		}
		if (keycode == Keys.I)
		{
			animation.CopyCenter();
		}
		
		if (animation.edit_width)
		{
			switch(keycode)
			{
			case Keys.NUM_1: animation.AddWidth(++animation.hitbox_width);
				break;
			case Keys.NUM_3: animation.AddWidth(animation.hitbox_width+2);
				break;
			case Keys.NUM_5: animation.AddWidth(animation.hitbox_width+5);
				break;
			case Keys.NUM_7: animation.AddWidth(animation.hitbox_width+10);
				break;
			case Keys.NUM_9: animation.AddWidth(animation.hitbox_width+50);
				break;
			case Keys.NUM_2: animation.AddWidth(--animation.hitbox_width);
				break;
			case Keys.NUM_4: animation.AddWidth(animation.hitbox_width-2);
				break;
			case Keys.NUM_6: animation.AddWidth(animation.hitbox_width-5);
				break;
			case Keys.NUM_8: animation.AddWidth(animation.hitbox_width-10);
				break;
			case Keys.NUM_0: animation.AddWidth(animation.hitbox_width-50);
				break;
			case Keys.NUMPAD_1: animation.ChangeHitboxType(1);
				break;
			case Keys.NUMPAD_2: animation.ChangeHitboxType(2);
				break;
			case Keys.NUMPAD_3: animation.ChangeHitboxType(3);
				break;
			case Keys.NUMPAD_4: animation.ChangeHitboxType(4);
				break;
			case Keys.NUMPAD_5: animation.ChangeHitboxType(5);
				break;
			case Keys.NUMPAD_6: animation.ChangeHitboxType(6);
				break;
			case Keys.NUMPAD_7: animation.ChangeHitboxType(7);
				break;
			case Keys.NUMPAD_8: animation.ChangeHitboxType(8);
				break;
			case Keys.NUMPAD_9: animation.ChangeHitboxType(9);
				break;
			}
		}
		else
		{
			switch(keycode)
			{
			case Keys.NUM_1: animation.AddHeight(++animation.hitbox_height);
				break;
			case Keys.NUM_3: animation.AddHeight(animation.hitbox_height+2);
				break;
			case Keys.NUM_5: animation.AddHeight(animation.hitbox_height+5);
				break;
			case Keys.NUM_7: animation.AddHeight(animation.hitbox_height+10);
				break;
			case Keys.NUM_9: animation.AddHeight(animation.hitbox_height+50);
				break;
			case Keys.NUM_2: animation.AddHeight(--animation.hitbox_height);
				break;
			case Keys.NUM_4: animation.AddHeight(animation.hitbox_height-2);
				break;
			case Keys.NUM_6: animation.AddHeight(animation.hitbox_height-5);
				break;
			case Keys.NUM_8: animation.AddHeight(animation.hitbox_height-10);
				break;
			case Keys.NUM_0: animation.AddHeight(animation.hitbox_height-50);
				break;
			}
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
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		animation.Touch();
		
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
