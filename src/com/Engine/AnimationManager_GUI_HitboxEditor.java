package com.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;

public class AnimationManager_GUI_HitboxEditor extends AnimationManager_HitboxEditor{

	Texture button;
	Image i_button;
	BitmapFont font;
	Rectangle r_rename;
	
	public AnimationManager_GUI_HitboxEditor ()
	{
		super();
		button = new Texture(Gdx.files.internal("data/button.png"));
		i_button = new Image(button);
		font = new BitmapFont();
		r_rename = new Rectangle (700, 50, 100, 30);
	}
	
	public void LoadFileJSON(String s)
	{
		FileHandle file = Gdx.files.absolute(s);
		
		if (file.exists())
		{
			String character_string = file.readString();
			Json json = new Json();
		   	Character = json.fromJson(Character_Hitboxes.class, character_string);
			LoadStrings_Valid();
		}
	}
	
	public void Touch()
	{
	if (ContainsRect(r_rename))
		{
			Rename();
		}
	}
	
	public void SetSaveFile()
	{
		MySaveInput mytext = new MySaveInput(this);
		Gdx.input.getTextInput(mytext, "Save where? (Example: C:/Users/myuser/Desktop/character.json)", "Complete path");
	}
	
	public void SetLoadFile()
	{
		MyLoadInput mytext = new MyLoadInput(this);
		Gdx.input.getTextInput(mytext, "Load from? (Example: C:/Users/myuser/Desktop/character.json)", "Complete path");
		LoadStrings_Valid();
	}
	
	public boolean ContainsRect(Rectangle rectangle)
	{
		if (rectangle.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
		{
			return true;
		}
		
		return false;
	}
	
	public void TouchDown()
	{
		if (Gdx.input.isTouched())
		{
			if (ContainsRect(r_rename))
		{
			return;
		}
		
		
		Update_Box();
		}
	}
	
	public void RenderGUIEditor(SpriteBatch batch)
	{
		
		i_button.setPosition(700, 50);
		i_button.setBounds(700, 50, 100, 30);
		i_button.draw(batch, 1f);
		
		font.setColor(Color.WHITE);
		
		font.draw(batch, "Change Animation", 705, 70);
	}
	
	
	public void RenderAnimations(SpriteBatch batch)
	{
		int x = Gdx.graphics.getWidth() - 250;
		int y = 0;
		font.setColor(Color.BLUE);
			for (Animations ani : Character.animation_list)
			{
				if (y == current_animation)
				{
					font.setColor(Color.RED);
				}
				else
				{
					font.setColor(Color.BLUE);
				}
				
				font.draw(batch, ani.name_of_animation, x, Gdx.graphics.getHeight() - y*15);
				font.draw(batch, ani.path_to_atlas, x + 100, Gdx.graphics.getHeight() - y*15);
				font.draw(batch, this.String_Current_state(y), x - 100, Gdx.graphics.getHeight() - y*15);
				font.draw(batch, Integer.toString(ani.number_frames), x - 30, Gdx.graphics.getHeight() - y*15);
				y++;
			}
		
	}
	
	public String String_Current_state()
	{
		String s = "null";
		
		switch (this.status)
		{
		case crouch: s = "crouch";
			break;
		case crouch_b: s = "crouch b";
			break;
		case crouch_f: s = "crouch f";
			break;
		case idle: s = "IDLE";
			break;
		case jump: s = "jump";
			break;
		case jump_b: s = "jump b";
			break;
		case jump_f_idle: s = "jump f idle";
			break;
		case jump_b_idle: s = "jump f idle";
			break;
		case jump_f: s = "jump f";
			break;
		case jump_idle: s = "jump idle";
			break;
		case run: s = "run";
			break;
		case run_b: s = "run b";
			break;
		case walk: s = "walk";
			break;
		case walk_b: s = "walk b";
			break;
		case walk_b_crouch: s = "walk b crouch";
			break;
		case walk_down: s = "walk down";
			break;
		case walk_f_crouch: s = "walk f crouch";
			break;
		default:
			break;
		
		}
		
		return s;
	}
	
	public String String_Current_state(int index)
	{
		String s = "null";
		
		switch (index)
		{
		case 0: s = "IDLE";
			break;
		case 1: s = "Walking";
			break;
		case 2: s = "Walk B";
			break;
		case 3: s = "Jump";
			break;
		case 4: s = "Jump D";
			break;
		case 5: s = "crouch";
			break;
		}
		
		return s;
	}
	
	public void Dispose()
	{
		super.Dispose();
		button.dispose();
		font.dispose();
	}
	
	public void Rename()
	{
		SetAtlasPath myinput = new SetAtlasPath(this);
		Gdx.input.getTextInput(myinput, "Set Path to Atlas", "Atlas location");
	}
	
	public void SetPath(String s)
	{
		if (Gdx.files.absolute(s).exists())
		{
			Character.animation_list.get(current_animation).path_to_atlas = s;
			SetStrings_Valid(current_animation);
			
			RenameInput myinput = new RenameInput(this);
			Gdx.input.getTextInput(myinput, "Set New name of the animation", "New name");
			
			SetLastFrame mylastinput = new SetLastFrame(this);
			Gdx.input.getTextInput(mylastinput, "Set Number of Frames of the animation", "Number of frames");
		}
	}
	
	public void Rename_Animation(String s)
	{
		Character.animation_list.get(current_animation).name_of_animation = s;
	}
	
	public void SetLastFrameNumber(int s)
	{
		Character.animation_list.get(current_animation).number_frames = s; 
	}
	
	public void AddAnimation()
	{
		Character.animation_list.add(new Animations());
		current_animation = Character.animation_list.size() - 1;
		Character.animation_list.get(current_animation).index = current_animation;
		index = 0;
		this.AddNextPlus();
	}
	public void RemoveAnimation()
	{
		if (Character.animation_list.size() > 1)
		{
			Character.animation_list.remove(current_animation);
			current_animation--;
			index = 0;
		}
	}
	
	public void UpAnimation()
	{
		if (current_animation < Character.animation_list.size() - 1)
		{
			current_animation++;
			index = 0;
			last_frame_check = Character.animation_list.get(current_animation).last_frame;
			ChangeCurrentStatus();
		}
	}
	public void DownAnimation()
	{
		if (current_animation > 0)
		{
			current_animation--;
			index = 0;
			last_frame_check = Character.animation_list.get(current_animation).last_frame;
			ChangeCurrentStatus();
		}
	}
	
	public boolean CheckBounds()
	{
		if (this.index > this.GetLastFrameReal())
		{
			this.index = this.GetLastFrameReal();
			this.current_frame_index = this.GetLastFrameReal();
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	public void ChangeCurrentStatus()
	{
		switch(current_animation)
		{
		case 0: this.status = PlayerStatus.Status.idle; this.current_status = PlayerStatus.Status.idle; 
			this.index = 0; this.CheckBounds(); this.current_frame_index = 0;
			break;
		case 1: this.status = PlayerStatus.Status.walk; this.current_status = PlayerStatus.Status.walk; this.index = 0;
			this.CheckBounds(); this.current_frame_index = 0;
			break;
		case 2: this.status = PlayerStatus.Status.walk_b; this.current_status = PlayerStatus.Status.walk_b; this.index = 0;
			this.CheckBounds(); this.current_frame_index = 0;
			break;
		case 3: this.status = PlayerStatus.Status.jump; this.current_status = PlayerStatus.Status.jump; this.index = 0;
			this.CheckBounds(); this.current_frame_index = 0;
			break;
		case 4: this.status = PlayerStatus.Status.jump_b; this.current_status = PlayerStatus.Status.jump_b; this.index = 0;
			this.CheckBounds(); this.current_frame_index = 0;
			break;
		case 5: this.status = PlayerStatus.Status.crouch; this.current_status = PlayerStatus.Status.crouch; this.index = 0;
			this.CheckBounds(); this.current_frame_index = 0;
			break;
		}
	}
	
	public void AddAtlas()
	{
		ChangeAtlasInput change = new ChangeAtlasInput(this);
		Gdx.input.getTextInput(change, "Location of the atlas:", "Atlas Location");
	}

	public boolean CollisionGround(Player groundRect) {
		// TODO Auto-generated method stub
		return this.GroundCollision(groundRect, index, current_animation);
	}
	
}
