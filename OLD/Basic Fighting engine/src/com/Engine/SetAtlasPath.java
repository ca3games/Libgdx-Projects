package com.Engine;

import com.badlogic.gdx.Input.TextInputListener;

public class SetAtlasPath implements TextInputListener {

	public AnimationManager_GUI_HitboxEditor animation;
	
	public SetAtlasPath(AnimationManager_GUI_HitboxEditor a)
	{
		animation = a;
	}
	
	@Override
	public void input(String text) {
		
		animation.SetPath(text);
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
