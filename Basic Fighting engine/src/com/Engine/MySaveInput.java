package com.Engine;

import com.badlogic.gdx.Input.TextInputListener;

public class MySaveInput implements TextInputListener {

	public AnimationManager_GUI_HitboxEditor animation;
	
	public MySaveInput(AnimationManager_GUI_HitboxEditor a)
	{
		animation = a;
	}
	
	@Override
	public void input(String text) {
		
		animation.SaveFileJSON(text);
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}

}
