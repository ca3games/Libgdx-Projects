package com.Engine;

import com.badlogic.gdx.Input.TextInputListener;

public class RenameInput implements TextInputListener {

	public AnimationManager_GUI_HitboxEditor animation;
	
	public RenameInput(AnimationManager_GUI_HitboxEditor a)
	{
		animation = a;
	}
	
	@Override
	public void input(String text) {
		
		animation.Rename_Animation(text);
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}

}
