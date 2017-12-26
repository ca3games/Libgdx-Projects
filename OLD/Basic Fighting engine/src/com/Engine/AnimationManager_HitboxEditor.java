package com.Engine;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;


public class AnimationManager_HitboxEditor extends AnimationManager {
	
	PlayerStatus.Status current_status;
	BitmapFont font;
	int xpos, ypos;
	boolean play;
	int hitbox_width, hitbox_height;
	int layer;
	int current_animation;
	int character_index;
	int last_frame_check;
	boolean save_hitbox, edit_width;
	FileHandle Character_file;
	int xpos_copy, ypos_copy;
	int x_off, y_off;
	
	public AnimationManager_HitboxEditor()
	{
		super();
		current_status = PlayerStatus.Status.idle;
		index = 0;
		layer = 0;
		last_frame_check = index;
		font = new BitmapFont();
		character_index = 0;
		hitbox_width = 100;
		hitbox_height = 100;
		edit_width = true;
		current_animation = 0;
		Character.animation_list.get(current_animation).frame.get(index).AddFrame(0, 0, hitbox_width, hitbox_height, this.index, this.layer);
		save_hitbox = false;
		
		Character_file = Gdx.files.external("athena.txt");
		xpos_copy = 100;
		ypos_copy = 100;
		
	}
	
	
	public void Update_Box()
	{
		if (Gdx.input.isTouched())
		{
			xpos = Gdx.input.getX();
			ypos = Gdx.graphics.getHeight() - Gdx.input.getY();
			if (this.save_hitbox)
			{
				
			}
			else
			{
				Character
				.animation_list.get(current_animation)
				.frame.get(index).hitbox.get(layer)
				.SetHitbox(xpos - x_off, ypos - y_off, 
						GetHitboxWidth(), GetHitboxHeigth());
			}
		}
	}
	
	public void SetCenterPosition()
	{
		xpos = Gdx.input.getX();
		ypos = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		Character.animation_list.get(current_animation)
		.frame.get(index).hitbox.get(layer)
		.Center = new Point(xpos - x_off, ypos - y_off);

	}
	
	
	public void Play_Animation()
	{
		if (play)
		{
			play = false;
			index = 0;
			current_frame_index = 0;
			current_animation = 0;
		}
		else
		{
			play = true;
		}
	}
	
	public void AddFrame()
	{
		int max_layer = Character.animation_list.get(current_animation).frame.get(index).hitbox.size();
		layer = max_layer;
		Character.animation_list.get(current_animation).frame.get(index).hitbox.add(
				new Hitbox(0,
						0, 0, 0, index, layer));
	}
	
	public int GetLastFrame()
	{
		int frame_last = this.GetLastFrameReal();
		
		frame_last--;
		return frame_last;
	}
	
	public int GetLastFrameReal()
	{
		int frame_last = 0;
		frame_last = Character.animation_list.get(current_animation).number_frames;
		return frame_last;
	}
	
	public int GetHitboxWidth()
	{
		return (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.width;
	}
	public int GetHitboxHeigth()
	{
		return (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.height;
	}
	public int GetHitbox_x()
	{
		return (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.x;
	}
	public int GetHitbox_y()
	{
		return (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.y;
	}
	
	public void RenderPlay(SpriteBatch batch)
	{
		this.index = this.current_frame_index;
		
		switch(this.status)
		{
		case crouch: current_animation = 5;
			break;
		case crouch_b: current_animation = 5;
			break;
		case crouch_f: current_animation = 5;
			break;
		case idle: current_animation = 0;
			break;
		case jump: current_animation = 3;
			break;
		case jump_b: current_animation = 4;
			break;
		case jump_b_idle: current_animation = 4;
			break;
		case jump_f_idle: current_animation = 4;
			break;
		case jump_f: current_animation = 4;
			break;
		case jump_idle: current_animation = 3;
			break;
		case run:
			break;
		case run_b:
			break;
		case walk: current_animation = 1;
			break;
		case walk_b: current_animation = 2;
			break;
		case walk_b_crouch: current_animation = 5;
			break;
		case walk_down: current_animation = 5;
			break;
		case walk_f_crouch: current_animation = 5;
			break;
		default:
			break;
		
		}
	}
	
	public void Draw(SpriteBatch batch)
	{
		font.setColor(Color.BLUE);
		if (play)
		{
			this.Render(this.GetLastFrameReal(), batch, current_status);
			font.draw(batch, "play: true" , 30, 0);
			this.index = this.current_frame_index;
		}
		else
		{
			this.RenderFrame(current_status, index, batch);
			font.draw(batch, "play: false" , 30, 0);
		}
		
		font.draw(batch, "layer: " + Integer.toString(layer), 60, 0);
		font.draw(batch, "frame index: " + Integer.toString(index), 60, 0);
		font.draw(batch, "width: " + Integer.toString(hitbox_width), 0, 30);
		font.draw(batch, "height: " + Integer.toString(hitbox_height), 150, 30);
		font.draw(batch, "current frame: " + Integer.toString(current_frame_index), 300, 30);
		
		if (edit_width)
		{
			font.draw(batch, "edit width mode", 0, 60);
		}
		else
		{
			font.draw(batch, "edit height mode", 0, 60);
		}
	}

	public void DrawListFrames(SpriteBatch batch, int current_frame, int current_layer, int current_anim)
	{
		int y = 0;
			for (Animations ani : Character.animation_list)
			{
				for (Frame fra : ani.frame)
				{
			
				for (Hitbox hitb : fra.hitbox)
				{
					if (hitb.Frame == current_frame && ani.index == current_anim)
					{
						if (hitb.layer == current_layer)
						{
							font.setColor(Color.RED);
						}
						else
						{
							font.setColor(Color.BLUE);
						}
						
						font.draw(
								batch, "L: " + Integer.toString(hitb.layer) + " x: "	+
								Integer.toString((int)hitb.hitbox.x) + " y: " +
										Integer.toString((int)hitb.hitbox.y)
										+ " w :" + Integer.toString((int)hitb.hitbox.width)
										+ " h: " + Integer.toString((int)hitb.hitbox.height)
										+ " t: " + GetTypeHitbox(hitb.Type)
								, 0, Gdx.graphics.getHeight() - y*20);
						y++;
					}
				}
			}
			}
	}
	
	public String GetTypeHitbox(Hitbox.type type)
	{
		String name = "";
		
		switch(type)
		{
		case attack_high: name = "attack high";
			break;
		case attack_low: name = "attack low";
			break;
		case attack_mid: name = "attack mid";
			break;
		case block_low: name = "block low";
			break;
		case block_mid: name = "block mid";
			break;
		case crouched: name = "crouched";
			break;
		case grab: name = "grab";
			break;
		case normal: name = "normal";
			break;
		case nulo: name = "nulo";
			break;
		default:
			break;
		
		}
		
		return name;
	}
	
	public void IndexPlus()
	{
		if (index <= GetLastFrame())
		{	
				index++;
				layer = 0;
				if (index > Character.animation_list.get(current_animation).last_frame)
				{
					Character.animation_list.get(current_animation).last_frame = index;
					AddNextPlus();
				}
		}
	}
	
	public void AddNextPlus()
	{
		Character.animation_list.get(current_animation).frame.add(new Frame());
		layer = 0;
		Character.animation_list.get(current_animation).frame.get(index).AddFrame(xpos, ypos, hitbox_width, hitbox_height, index, layer);
	}
	
	
	public void IndexLess()
	{
		if (index > 0)
		{
			index--;
			layer = 0;
		}
	}
	
	public void LayerUp()
	{
		if (layer < Character.animation_list.get(current_animation).frame.get(index).hitbox.size()-1 
				&& Character.animation_list.get(current_animation).frame.get(index).hitbox.size() > 0)
		{
			layer++;
		}
	}
	
	public void LayerDown()
	{
		if (layer > 0)
		{
			layer--;
		}
	}
	
	public void AddWidth(int ancho)
	{
		hitbox_width = ancho;
		if (hitbox_width < 0)
		{
			hitbox_width = 0;
		}
	}
	
	public void AddHeight(int ancho)
	{
		hitbox_height = ancho;
		if (hitbox_height < 0)
		{
			hitbox_height = 0;
		}
	}
	
	public void SaveHitbox()
	{
		Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer)
		.SetHitbox(GetHitbox_x(), GetHitbox_y(), hitbox_width, hitbox_height);
	}
	
	public void DeleteHitbox()
	{
			for (Animations ani : Character.animation_list)
			{
				for (Frame fra : ani.frame)
				{
				for (Hitbox hitb : fra.hitbox)
				{
					if (hitb.Frame == index)
					{
						if (hitb.layer > layer)
						{
							hitb.layer -= 1;
						}
					}
				}
				}
		
		}
		if (Character.animation_list.get(current_animation).frame.get(index).hitbox.size() > 1)
		{
			Character.animation_list.get(current_animation).frame.get(index).hitbox.remove(layer);
			if (layer > 0)
			{
				layer--;
			}
		}
	}
	
	public void EditWidth()
	{
		if (edit_width)
		{
			edit_width = false;
		}
		else
		{
			edit_width = true;
		}
	}
	
	public void CopySize()
	{
		hitbox_width = (int) Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.width;
		hitbox_height = (int) Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.height;
	}
	
	public void SaveFile()
	{
		int character_number = 0;
		int frame_number = 0;
		
		Character_file.writeString("File" + System.getProperty("line.separator"), false);		
		
			character_number++;
			Character_file.writeString("Character: " + Integer.toString(character_number) + System.getProperty("line.separator"), true);
			for (Frame fra : Character.animation_list.get(current_animation).frame)
			{
				Character_file.writeString("frame " + Integer.toString(frame_number) + System.getProperty("line.separator"), true);
				for (Hitbox hitb : fra.hitbox)
				{
					Character_file.writeString("x " + Integer.toString((int)hitb.hitbox.x), true);
					Character_file.writeString(" y " + Integer.toString((int)hitb.hitbox.y), true);
					Character_file.writeString(" w " + Integer.toString((int)hitb.hitbox.width), true);
					Character_file.writeString(" h " + Integer.toString((int)hitb.hitbox.height), true);
					Character_file.writeString(System.getProperty("line.separator"), true);
				}
				frame_number++;
			}
	}
	
	public void SaveFileJSON(String s)
	{
		Json json = new Json();
		String character_string = json.toJson(Character);
		FileHandle file = Gdx.files.absolute(s);
		
		file.writeString(character_string, false);
	}
	
	public void CopyLocation()
	{
		xpos_copy = (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.x;
		ypos_copy = (int)Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.y;
	}
	public void PasteLocation()
	{
		Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.x = xpos_copy;
		Character.animation_list.get(current_animation).frame.get(index).hitbox.get(layer).hitbox.y = ypos_copy;
	}
}
