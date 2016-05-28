package com.Engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends Player_HitBoxEditor{
	
	PlayerStatus status;
	PhysicsManager physics;
	public StateManager state;
	Player oponent;
	public boolean collides;
	boolean IsP1;
	
	Animation_Manager animation;
	
	public Player(int x, int y, boolean P1)
	{
		status = new PlayerStatus();
		animation = new Animation_Manager();
		physics = new PhysicsManager(x, y);
		state = new StateManager(null, this, status, physics, null);
		
		animation.status = PlayerStatus.Status.idle;
		collides = false;
		IsP1 = P1;
		
	}
	
	
	
	public void update(Player enemy)
	{	
		if (!animation.CollisionGround(this))
		{
			physics.GravityFall();
		}
		else
		{
			physics.CheckGround();
		}
		
		
		if(animation.CollisionOtherBox(
				this.physics.x, enemy.physics.x, this.physics.y, enemy.physics.y,
				this.animation.Character.animation_list.get(animation.current_animation).frame.get(animation.index),
				enemy.animation.Character.animation_list.get(animation.current_animation).frame.get(animation.index)
				))
		{
			collides = true;
			if (enemy.physics.OnGround)
			{
				if (this.physics.Speed_x == 0)
				{
					if (this.physics.x <= enemy.physics.x)
					{
						this.physics.Speed_x = -this.physics.walk_speed;
					}
					else
					{
						this.physics.Speed_x = this.physics.walk_speed;
					}
				}
				else
				{
					if (this.physics.x <= enemy.physics.x)
					{
						this.physics.Speed_x = -this.physics.walk_speed;
					}
					else
					{
						this.physics.Speed_x = this.physics.walk_speed;
					}
				}
			}
			else
			{
				if (this.physics.x <= enemy.physics.x)
				{
					this.physics.Speed_x = -this.physics.walk_speed*3;
				}
				else
				{
					this.physics.Speed_x = this.physics.walk_speed*3;
				}
			}
			

		}
		else
		{
			collides = false;
		}
		
		physics.Update();
		SetStatus();
		state.Update();
			
		animation.Update(physics.x, physics.y, status.status);
	}
	
	
	public void Move(StateManager.Commands st)
	{
		state.last_input = st;
	}
	
	public void SetStatus()
	{
		state.Update();
		animation.status = status.status;
	}
	
	public void DrawHitboxes(ShapeRenderer Box)
	{
		animation.Draw_Hitbox_Current
		(Box, animation.index, animation.layer,
				animation.hitbox_width, animation.hitbox_height,
				animation.current_animation, this.physics.x, this.physics.y
				);
		physics.RenderHitBoxes(Box);
	}
	
	public void Draw(SpriteBatch batch)
	{
		animation.RenderPlay(batch);
		animation.Render(animation.GetLastFrameReal(), batch, animation.status);
	}
	
	public void DrawInfoHitbox(SpriteBatch batch)
	{
		if (animation.CheckBounds())
		{
		animation.RenderHitboxInfo(batch, animation.current_frame_index, animation.GetHitbox_x() ,
				animation.GetHitbox_y(), animation.String_Current_state(), animation.GetLastFrameReal(),
				animation.current_animation, this.collides,
				this.physics.x, this.physics.y
				);
		}
	}
	
	public void Dispose()
	{
		animation.Dispose();
	}
}
