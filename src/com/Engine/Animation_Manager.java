package com.Engine;

import java.awt.Point;

public class Animation_Manager extends AnimationManager_GUI_HitboxEditor {

	Point center_copy;
	
	
	public void ChangeHitboxType(int type)
	{
		switch(type)
		{
		case 1: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.normal;
			break;
		case 2: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.crouched;
			break;
		case 3: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.nulo;
			break;
		case 4: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.attack_mid;
			break;
		case 5: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.attack_low;
			break;
		case 6: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.attack_high;
			break;
		case 7: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.block_mid;
			break;
		case 8:
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.block_low;
			break;
		case 9: 
			this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Type = com.Engine.Hitbox.type.grab;
			break;
		}
	}
	
	public void CopyCenter()
	{
		this.center_copy = 
				this.Character.animation_list.get(current_animation).
				frame.get(index).hitbox.get(layer).Center;
	}
	
	public void PasteCenter()
	{
		this.Character.animation_list.get(current_animation).
		frame.get(index).hitbox.get(layer).Center = this.center_copy;
	}
	
}
