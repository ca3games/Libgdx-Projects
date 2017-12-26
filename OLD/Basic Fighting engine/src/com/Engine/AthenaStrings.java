package com.Engine;


public class AthenaStrings {
	
	public AthenaStrings(AnimationStrings a)
	{
		a.Idle = "null";
		a.walking = "null";
		a.walking_back = "null";
		a.jump = "null";
		a.jump_d = "null";
		a.crouch = "null";
	}
	
	public void SetStrings(AnimationStrings a, Character_Hitboxes chara, int index)
	{
		if (index == 0 && chara.animation_list.get(0).path_to_atlas != "null path") {a.Idle = chara.animation_list.get(0).path_to_atlas;}
		if (index == 1 && chara.animation_list.get(1).path_to_atlas != "null path") {a.walking = chara.animation_list.get(1).path_to_atlas;}
		if (index == 2 && chara.animation_list.get(2).path_to_atlas != "null path") {a.walking_back = chara.animation_list.get(2).path_to_atlas;}
		if (index == 3 && chara.animation_list.get(3).path_to_atlas != "null path") {a.jump = chara.animation_list.get(3).path_to_atlas;}
		if (index == 4 && chara.animation_list.get(4).path_to_atlas != "null path") {a.jump_d = chara.animation_list.get(4).path_to_atlas;}
		if (index == 5 && chara.animation_list.get(5).path_to_atlas != "null path") {a.crouch = chara.animation_list.get(5).path_to_atlas;}
	}
	
	public void LoadStrings (AnimationStrings a, Character_Hitboxes chara)
	{
		if (chara.animation_list.size() > 0 && chara.animation_list.get(0).path_to_atlas != "null path") {a.Idle = chara.animation_list.get(0).path_to_atlas;}
		if (chara.animation_list.size() > 1 && chara.animation_list.get(1).path_to_atlas != "null path") {a.walking = chara.animation_list.get(1).path_to_atlas;}
		if (chara.animation_list.size() > 2 && chara.animation_list.get(2).path_to_atlas != "null path") {a.walking_back = chara.animation_list.get(2).path_to_atlas;}
		if (chara.animation_list.size() > 3 && chara.animation_list.get(3).path_to_atlas != "null path") {a.jump = chara.animation_list.get(3).path_to_atlas;}
		if (chara.animation_list.size() > 4 && chara.animation_list.get(4).path_to_atlas != "null path") {a.jump_d = chara.animation_list.get(4).path_to_atlas;}
		if (chara.animation_list.size() > 5 && chara.animation_list.get(5).path_to_atlas != "null path") {a.crouch = chara.animation_list.get(5).path_to_atlas;}
	}
	
}
