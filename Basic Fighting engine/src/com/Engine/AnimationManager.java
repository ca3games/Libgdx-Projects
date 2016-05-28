/*
 * This class manages the animation being shown
 * It takes in the update function the current state
 * and then uses a switch to render it 
 */


package com.Engine;


import com.Engine.Hitbox.type;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class AnimationManager {

	public boolean CollisionSolid, CollisionDamage, CollisionGround;
	
	AnimationStrings a_string;
	AthenaStrings Athena;
	BitmapFont font;
	
	Character_Hitboxes Character;
	int index;
	int current_frame_index;
	
	public TextureAtlas
	t_Idle, t_walking, t_walking_back,
	t_jump, t_jump_d,
	t_crouch
	;
	private Animation 
	a_Idle, a_walking, a_walking_back,
	a_jump, a_jump_d,
	a_crouch
	;
	private float deltatime = 0;
	float current_deltatime = 0;
	int x, y;
	PlayerStatus.Status status;
	
	public AnimationManager()
	{
		Character = new Character_Hitboxes();
		
		index = 0;
		current_frame_index = 0;
		a_string = new AnimationStrings();
		font = new BitmapFont();
		
		CollisionGround = false;
		CollisionDamage = false;
		CollisionSolid = false;
		
	}
	
	public Frame GetAnimation(Player player, int current_anim, int current_frame)
	{
		return Character.animation_list.get(current_anim).frame.get(current_frame);
	}
	
	public boolean CollisionOtherBox(int play1_x, int play2_x, int play1_y, int play2_y, Frame player, Frame enemy)
	{
		for (Hitbox hit_play: player.hitbox)
		{
			for (Hitbox hit_ene: enemy.hitbox)
			{
				Rectangle play1 = new Rectangle(play1_x + hit_play.hitbox.x, play1_y + hit_play.hitbox.y, hit_play.hitbox.width, hit_play.hitbox.height);
				Rectangle play2 = new Rectangle(play2_x + hit_ene.hitbox.x, play2_y + hit_ene.hitbox.y, hit_ene.hitbox.width, hit_ene.hitbox.height);
						
				if (play1.x >= play2.x)
				{
					if (play1.x <= play2.x + play2.width)
					{
						if (play1.y >= play2.y)
						{
							if (play1.y <= play2.y + play2.height)
							{
								return true;
							}
						}
					}
				}
				if (play2.x >= play1.x)
				{
					if (play2.x <= play1.x + play1.width)
					{
						if (play2.y >= play1.y)
						{
							if (play2.y <= play1.y + play1.height)
							{
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean GroundCollision(Player player, int current_frame, int current_anim)
	{
		for (Animations ani : Character.animation_list)
		{
			for (Frame fra : ani.frame)
			{
				for (Hitbox hitb : fra.hitbox)
				{
					if (hitb.Frame == current_frame && ani.index == current_anim)
					{
						if (hitb.hitbox.y + player.physics.y <= player.physics.groundRect.y)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	public void SetStrings_Valid(int current_anim)
	{
		Athena = new AthenaStrings(a_string);
		Athena.SetStrings(a_string, Character, current_anim);
		
		if (a_string.Idle != "null") {this.LoadIdle();}
		if (a_string.crouch != "null") {this.Load_Crouch();}
		if (a_string.walking != "null") {this.Load_Walking();}
		if (a_string.walking_back != "null") {this.Load_WalkingBack();}
		if (a_string.jump != "null") {this.Load_Jump();}
		if (a_string.jump_d != "null") {this.Load_Jump_D();}
	}
	
	public void LoadStrings_Valid()
	{
		Athena = new AthenaStrings(a_string);
		Athena.LoadStrings(a_string, Character);
		
		if (a_string.Idle != "null") {this.LoadIdle();}
		if (a_string.crouch != "null") {this.Load_Crouch();}
		if (a_string.walking != "null") {this.Load_Walking();}
		if (a_string.walking_back != "null") {this.Load_WalkingBack();}
		if (a_string.jump != "null") {this.Load_Jump();}
		if (a_string.jump_d != "null") {this.Load_Jump_D();}
	}
	
	public void LoadIdle()
	{
		t_Idle = new TextureAtlas(Gdx.files.internal(a_string.Idle));
		a_Idle = new Animation(1/15f, t_Idle.getRegions());
	}
	
	public void Load_Walking()
	{
		t_walking = new TextureAtlas(Gdx.files.internal(a_string.walking));
		a_walking = new Animation(1/15f, t_walking.getRegions());
	}
	
	public void Load_WalkingBack()
	{
		t_walking_back = new TextureAtlas(Gdx.files.internal(a_string.walking_back));
		a_walking_back = new Animation(1/15f, t_walking_back.getRegions());
	}
	
	public void Load_Jump()
	{
		t_jump = new TextureAtlas(Gdx.files.internal(a_string.jump));
		a_jump = new Animation(1/7f, t_jump.getRegions());
	}
	
	public void Load_Jump_D()
	{
		t_jump_d = new TextureAtlas(Gdx.files.internal(a_string.jump_d));
		a_jump_d = new Animation(1/7f, t_jump_d.getRegions());
	}
	
	public void Load_Crouch()
	{
		t_crouch = new TextureAtlas(Gdx.files.internal(a_string.crouch));
		a_crouch = new Animation(1/7f, t_crouch.getRegions());
	}
	
	public void Update(int xpos, int ypos, PlayerStatus.Status state)
	{
		x = xpos;
		y = ypos;
		status = state;
	}
	
	public void RenderFrame(PlayerStatus.Status state, int frame, SpriteBatch batch)
	{
		switch(state)
		{
		case crouch: if (a_crouch != null)
		{
			batch.draw(a_crouch.getKeyFrame(frame, true),x, y);
		}
			break;
		case crouch_b: if (a_crouch != null)
		{
			batch.draw(a_crouch.getKeyFrame(frame, true),x, y);
		}
			break;
		case crouch_f: if (a_crouch != null)
		{
			batch.draw(a_crouch.getKeyFrame(frame, true),x, y);
		}
			break;
		case idle: if (a_Idle != null)
		{
			batch.draw(a_Idle.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump: if (a_jump != null)
		{
			batch.draw(a_jump.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump_b: if (a_jump_d != null)
		{
			batch.draw(a_jump_d.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump_b_idle: if (a_jump_d != null)
		{
			batch.draw(a_jump_d.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump_f_idle: if (a_jump_d != null)
		{
			batch.draw(a_jump_d.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump_f: if (a_jump_d != null)
		{
			batch.draw(a_jump_d.getKeyFrame(frame, true),x, y);
		}
			break;
		case jump_idle: if (a_jump != null)
		{
			batch.draw(a_jump.getKeyFrame(frame, true),x, y);
		}
			break;
		case run:
			break;
		case run_b:
			break;
		case walk: if (a_walking != null)
		{
			batch.draw(a_walking.getKeyFrame(frame, true),x, y);
		}
			break;
		case walk_b: if (a_walking_back != null)
		{
			batch.draw(a_walking_back.getKeyFrame(frame, true),x, y);
		}
			break;
		case walk_b_crouch: if (a_crouch != null)
		{
			batch.draw(a_crouch.getKeyFrame(frame, true),x, y);
		}
			break;
		case walk_down:
			break;
		case walk_f_crouch:
			break;
		
		}
	}
	
	public void Render(int max_frame, SpriteBatch batch, PlayerStatus.Status state_debug)
	{
		deltatime += Gdx.graphics.getDeltaTime();
		current_deltatime += Gdx.graphics.getDeltaTime();
		
		if (current_deltatime > 0.33)
		{
			current_deltatime = 0;
			current_frame_index++;
			if (current_frame_index == max_frame)
			{
				current_frame_index = 0;
			}
		}
		
		switch(state_debug)
		{
		case crouch: 
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case idle: 
			if (a_Idle != null)
			{
				batch.draw(a_Idle.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump: 
			if (a_jump != null)
			{
				batch.draw(a_jump.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump_b: 
			if (a_jump_d != null)
			{
				batch.draw(a_jump_d.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump_f: 
			if (a_jump_d != null)
			{
				batch.draw(a_jump_d.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case run:
			break;
		case run_b:
			break;
		case walk: 
			if (a_walking != null)
			{
				batch.draw(a_walking.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case walk_b: 
			if (a_walking_back != null)
			{
				batch.draw(a_walking_back.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case walk_down: 
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump_b_idle:
			if (a_jump_d != null)
			{
				batch.draw(a_jump_d.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump_f_idle:
			if (a_jump_d != null)
			{
				batch.draw(a_jump_d.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case jump_idle: 
			if (a_jump != null)
			{
				batch.draw(a_jump.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case crouch_b: 
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case crouch_f: 
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case walk_b_crouch:
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		case walk_f_crouch:
			if (a_crouch != null)
			{
				batch.draw(a_crouch.getKeyFrame(deltatime, true), x, y);
			}
			break;
		
		}
	}
	
	public void RenderHitboxInfo(SpriteBatch batch, int frame, int x, int y, 
			String status, int max_frames, int current_anim, boolean colision, int xpos, int ypos)
	{
		font.setColor(Color.BLUE);
		font.draw(batch, "x: " + Integer.toString(x) + "/ y: " +  Integer.toString(y) + "/frame: " + Integer.toString(frame), 
				Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-20);
		font.draw(batch, "max frame: " + Integer.toString(max_frames) + " /current anim: " + Integer.toString(current_anim), 
				Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-40);
		font.draw(batch, "status: " + status, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-60);
		if (colision)
		{
			font.draw(batch, "Colision true", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-80);
		}
		else
		{
			font.draw(batch, "Colision false", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-80);
		}
		font.draw(batch, "xpos " + Integer.toString(xpos) + "/ ypos" + Integer.toString(ypos), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-5);
	}
	
	
	public void Dispose()
	{
		if (t_Idle != null)	{t_Idle.dispose();}
		if (t_walking != null) {t_walking.dispose();}
		if (t_walking_back != null) {t_walking_back.dispose();}
		if (t_jump != null) { t_jump.dispose();}
		if (t_jump_d != null) {t_jump_d.dispose();}
		if (t_crouch != null) {t_crouch.dispose();}
		font.dispose();
	}
	
	public void Draw_Hitbox(ShapeRenderer Box, int current_frame, int current_layer, int current_anim, int x_off, int y_off)
	{	
		for (Animations ani : Character.animation_list)
			{
				for (Frame fra : ani.frame)
				{
					for (Hitbox hitb : fra.hitbox)
					{
						if (hitb.Frame == current_frame && ani.index == current_anim)
						{
							if (hitb.layer != current_layer)
							{
								if (hitb.Type == type.attack_high || hitb.Type == type.attack_low || hitb.Type == type.attack_mid)
								{Box.setColor(Color.RED); }
								if (hitb.Type == type.normal || hitb.Type == type.crouched)
								{Box.setColor(Color.GREEN); }
								Box.rect(hitb.hitbox.x + x_off, hitb.hitbox.y + y_off, hitb.hitbox.width, hitb.hitbox.height);
							}
						}
					}
				}
			}
	}
	
	public void Draw_Hitbox(SpriteBatch Box, BitmapFont font, int current_frame, int current_anim, int x_off, int y_off)
	{	
			for (Animations ani : Character.animation_list)
			{
				for (Frame fra : ani.frame)
				{
			
					for (Hitbox hitb : fra.hitbox)
					{
						if (hitb.Frame == current_frame && ani.index == current_anim)
						{
							font.draw(Box, "L: " +Integer.toString(hitb.layer), hitb.hitbox.x + x_off, hitb.hitbox.y + y_off);
							font.draw(Box, "F: " +Integer.toString(hitb.Frame), hitb.hitbox.x + 50 + x_off, hitb.hitbox.y + y_off);
						}
					}
				}
			}
	}
	
	
	public void Draw_Hitbox_Current(ShapeRenderer Box, int current_frame, int current_layer, int width, int height, 
			int current_anim, int x_off, int y_off
			)
	{
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
								Box.setColor(Color.ORANGE);
								Box.rect(hitb.hitbox.x + x_off, hitb.hitbox.y + y_off, width, height);
								Box.setColor(Color.RED);
								Box.rect(hitb.Center.x + x_off, hitb.Center.y + y_off, 2, 2);
							}
						}
					}
				}
			}
	}
	
	
}
