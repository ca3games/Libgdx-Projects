package com.Engine;

import com.badlogic.gdx.Input.TextInputListener;

public class SetLastFrame implements TextInputListener {
	
	public AnimationManager_GUI_HitboxEditor animation;

	public SetLastFrame(AnimationManager_GUI_HitboxEditor a)
	{
		animation = a;
	}
	
	@Override
	public void input(String text) {
		
		animation.SetLastFrameNumber(Integer.parseInt(text));
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
	
}
