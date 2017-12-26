package gui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

import character.Animation_Hit;
import character.Character;
import character.Character_Atlas;
import character.Frame;
import character.Hitbox;
import character.Hitbox.Type;

public class Chara {

	public Character character;
	Character_Atlas chara_atlas;
	public int animation_index, frame_index, hitbox_index;
	public int center_x, center_y;
	int last_frame_check;
	public boolean EditSprite;
	
	public Chara()
	{
		animation_index = 0;
		frame_index = 0;
		hitbox_index = 0;
		character = new Character();
		chara_atlas = new Character_Atlas(this);
		center_x = Gdx.graphics.getWidth()/2;
		center_y = Gdx.graphics.getHeight()/3;
		last_frame_check = 0;
		EditSprite = true;
	}
	
	public void LoadAtlases()
	{
		List<Animation_Hit> animation = character.animation;
		for (int i = 0; i < animation.size(); i++) {
			Animation_Hit ani = animation.get((animation.size()-1) - i);
			chara_atlas.AddAnimation(ani.name_anim_file);
		}
	}
	
	public void AddAnimation()
	{
		//if (this.animation_index <= character.animation.size()-1)
		{
		character.animation.add(new Animation_Hit(true));	
		chara_atlas.AddAnimation();
		animation_index++;
		}
	}
	
	public void DeleteAnimation()
	{
		if (character.animation.size() > 0 && animation_index > 0)
		{
			character.animation.remove(animation_index);
			animation_index--;
		}
	}
	
	public void UpAnim()
	{
		if (animation_index < character.animation.size()-1)
		{
			animation_index++;
		}
	}
	
	public void DownAnim()
	{
		if (animation_index > 0)
		{
			animation_index--;
		}
	}
	
	
	public void AddAnimation(String s)
	{
		chara_atlas.AddAnimation(s);
		
		int frames = chara_atlas.GetAnimationSize();
		
		frame_index = 0;
		for (int i = 0; i < frames; i++)
		{
			character.animation.get(animation_index).frame.add(new Frame());
			frame_index++;
		}
		frame_index = 0;
		hitbox_index = 0;
		
		character.animation.get(animation_index).name_anim_file = s;
		
		character.animation.get(animation_index).name_animation = "no named";
	}
	
	public void ChangeName(String s)
	{
		character.animation.get(animation_index).name_animation = s;
	}
	
	public void SwitchSprite()
	{
		if (EditSprite)
		{
			EditSprite = false;
		}
		else
		{
			EditSprite = true;
		}
	}
	
	public void AddLayer()
	{
		try
		{
		character.animation.get(animation_index).frame.get(frame_index).hitbox.add(new Hitbox());
		hitbox_index = character.animation.get(animation_index).frame.get(frame_index).hitbox.size()-1;
		character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle = 
				new Rectangle(0, 0, 100, 100);
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void DeleteLayer()
	{
		int layers = character.animation.get(animation_index).frame.get(frame_index).hitbox.size() - 1;
		if (layers > 0)
		{
		character.animation.get(animation_index).frame.get(frame_index).hitbox.remove(hitbox_index);
		}
	}
	
	public void Click(int x, int y)
	{
		if (EditSprite)
		{
			try
			{
			character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.x = x - center_x;
			character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.y = y - center_y;
			}
			catch (IndexOutOfBoundsException e)
			{
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(0).rectangle.x = x - center_x;
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(0).rectangle.y = y - center_y;
			}
		}
		else
		{
			character.animation.get(animation_index).frame.get(frame_index).offset_x = x - center_x;
			character.animation.get(animation_index).frame.get(frame_index).offset_y = y - center_y;
		}
	}
	
	public void SetPosition(int x, int y)
	{
		if (EditSprite)
		{
			try
			{
			character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.x = x;
			character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.y = y;
			}
			catch (IndexOutOfBoundsException e)
			{
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(0).rectangle.x = x;
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(0).rectangle.y = y;
			}
		}
		else
		{
			character.animation.get(animation_index).frame.get(frame_index).offset_x = x - center_x;
			character.animation.get(animation_index).frame.get(frame_index).offset_y = y - center_y;
		}
	}
	
	public void SetSpeed(int x, int y)
	{
		try
		{
		character.animation.get(animation_index).frame.get(frame_index).speed_x = x;
		character.animation.get(animation_index).frame.get(frame_index).speed_y = y;
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void SetSize(int width, int height)
	{
		try
		{
		character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.width = width;
		character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).rectangle.height = height;
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void NextFrame()
	{
		int frames = chara_atlas.GetAnimationSize();
		if (frames != 0)
		{
			if (frame_index < frames)
			{
				hitbox_index = 0;
				frame_index++;
			}
		}
	}
	
	public void PrevFrame()
	{
		if (frame_index > 0)
		{
			hitbox_index = 0;
			frame_index--;
		}
	}
	
	public void LayerUp()
	{
		int number = character.animation.get(animation_index).frame.get(frame_index).hitbox.size() - 1; 
		if (hitbox_index < number)
		{
			hitbox_index++;
		}
	}
	
	public void LayerDown()
	{
		if (hitbox_index > 0)
		{
			hitbox_index--;
		}
	}
	
	public void Dispose()
	{
		chara_atlas.Dispose();
	}
	
	public void Draw(SpriteBatch batch)
	{
		chara_atlas.Draw(batch);
	}
	
	public void ResetType()
	{
		character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.normal;
	}
	
	public void ChangeType()
	{
			switch (character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type)
			{
			case air_attack:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.air_grab;
				break;
			case air_grab:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.nulo;
				break;
			case block:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.normal_attack;
				break;
			case crouch:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.block;
				break;
			case grab:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.air;
				break;
			case low_block:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.low_block;
				break;
			case normal:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.crouch;
				break;
			case nulo:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.normal;
				break;
			case air:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.air_fall;
				break;
			case air_fall:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.air_attack;
				break;
			case low_attack:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.grab;
				break;
			case normal_attack:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.low_attack;
				break;
			case overhead_attack:
				character.animation.get(animation_index).frame.get(frame_index).hitbox.get(hitbox_index).type = Type.overhead_attack;
				break;
			default:
				break;
			
			}
	}
	
	public void SaveFile(String filepath, String name)
	{
		Json json = new Json();
		String character_string = json.toJson(this.character);
		FileHandle file = Gdx.files.absolute(filepath + name);
		
		file.writeString(character_string, false);
	}
	
	public void LoadFile(String filepath, String name)
	{
		FileHandle file = Gdx.files.absolute(filepath + name);
		
		if (file.exists())
		{
			String character_string = file.readString();
			Json json = new Json();
		   	character = json.fromJson(Character.class, character_string);
		   	LoadAtlases();
		}
	}
}
