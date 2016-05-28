package com.Engine;

import com.badlogic.gdx.Input.TextInputListener;

public class MyLoadInput implements TextInputListener{

public AnimationManager_GUI_HitboxEditor animation;
	
	public MyLoadInput(AnimationManager_GUI_HitboxEditor a)
	{
		animation = a;
	}
	
	@Override
	public void input(String text) {
		
		animation.LoadFileJSON(text);
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
	
}
