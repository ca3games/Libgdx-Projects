/*
 * This class receives the keys being pressed in the keydown function of libgdx
 * It uses then a list to store the keys being passed from the keydown function
 * When this class receive a key it simply add it at index 0 of the list
 * It then check for certain combinations to call the player.move(status)
 */

package com.Engine;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InputReceiver {
	
	Player player;
	
	// There's also released keys, this is usefull for double tapping motions (dash, backdash)
	enum Input_keys
	{
		idle,
		left, right, down, up, A, B, C, X, Y, Z,
		left_r, right_r, down_r, up_r, A_r, B_r, C_r, X_r, Y_r, Z_r,
		jump_f, jump_b, jump_f_r, jump_b_r, 
	}
	List<Input_keys> input_key;
	
	boolean idle, last_pressed_bool;
	
	// Libgdx doesn't have on keypressed so they're used as to keep checking a key if the player holds it
	boolean left_pressed = false, right_pressed = false, down_pressed = false, up_pressed = false, 
		A_pressed = false, B_pressed = false, C_pressed = false, X_pressed = false, Y_pressed = false, Z_pressed = false;
	
	/*
	 * Integers that increases whenever you don't press a key
	 * they're used as to avoid double jumping
	 */
	int last_pressed, min_time_pressed, last_pressed_up;
	
	public InputReceiver(Player play)
	{	
		input_key = new ArrayList<Input_keys>();
		player = play;
		last_pressed = 0;
		last_pressed_up = 0;
		min_time_pressed = 20;
		player.state.inputreceiver = this;
		last_pressed_bool = true;
	}
	
	public void ReceiveInput(Input_keys keys)
	{
		switch (keys)
		{
		case A: A_pressed = true;
			
			break;
		case A_r: A_pressed = false;
			AddKeys(keys);
			break;
		case B: B_pressed = true;
			AddKeys(keys);
			break;
		case B_r: B_pressed = false;
			AddKeys(keys);
			break;
		case C: C_pressed = true;
			AddKeys(keys);
			break;
		case C_r: C_pressed = false;
			AddKeys(keys);
			break;
		case X: X_pressed = true;
			AddKeys(keys);
			break;
		case X_r: X_pressed = false;
			AddKeys(keys);
			break;
		case Y: Y_pressed = true;
			AddKeys(keys);
			break;
		case Y_r: Y_pressed = false;
			AddKeys(keys);
			break;
		case Z: Z_pressed = true;
			AddKeys(keys);
			break;
		case Z_r: Z_pressed = false;
			AddKeys(keys);
			break;
		case down: down_pressed = true;
			AddKeys(keys);
			break;
		case down_r: down_pressed = false;
			AddKeys(keys);
			break;
		case left: left_pressed = true;
			AddKeys(keys);
			break;
		case left_r: left_pressed = false;
			AddKeys(keys);
			break;
		case right: right_pressed = true;
			AddKeys(keys);
			break;
		case right_r: right_pressed = false;
			AddKeys(keys);
			break;
		case up: up_pressed = true;
			last_pressed_up = 0;
			AddKeys(keys);
			break;
		case up_r: up_pressed = false;
			AddKeys(keys);
			break;
		case idle: idle = true;
			break;
		default:
			break;
		}
	}
	
	public void AddKeys(Input_keys keys)
	{
		input_key.add(0, keys);
		idle = false;
		last_pressed = 0;
	}
	
	public void Update()
	{
		last_pressed++;
		last_pressed_up++;
		
		if (last_pressed >= min_time_pressed)
		{
			player.state.last_pressed_idle = true;
			last_pressed_bool = true;
			input_key.clear();
			last_pressed = min_time_pressed;
		}
		else
		{
			player.state.last_pressed_idle = false;
			last_pressed_bool = false;
		}
		
		if (last_pressed_up >= 10)
		{
			player.state.last_up = true;
			last_pressed_up = 10;
		}
		else
		{
			player.state.last_up = false;
		}
		
		GetAnimation();
	}
	
	public void Draw(SpriteBatch batch, BitmapFont font)
	{	
		int id = 0;
		for (Input_keys key : input_key)
		{
			id++;
			font.draw(batch, key.toString(), 0, id * 16 + 30 );
		}
		
		
		int ypos = 30;
		int xpos = 50;
		
		switch(player.status.status)
		{
		case crouch: font.draw(batch, "crouch", xpos, ypos);
			break;
		case idle: font.draw(batch, "IDLE", xpos, ypos);
			break;
		case jump: font.draw(batch, "jump", xpos, ypos);
			break;
		case jump_b: font.draw(batch, "jump_b", xpos, ypos);
			break;
		case jump_f: font.draw(batch, "jump_f", xpos, ypos);
			break;
		case run: font.draw(batch, "run", xpos, ypos);
			break;
		case run_b: font.draw(batch, "run_b", xpos, ypos);
			break;
		case walk: font.draw(batch, "walk", xpos, ypos);
			break;
		case walk_b: font.draw(batch, "walk_b", xpos, ypos);
			break;
		case walk_down: font.draw(batch, "crouch walk", xpos, ypos);
			break;
		case crouch_b: font.draw(batch, "crouch left", xpos, ypos);
			break;
		case crouch_f: font.draw(batch, "crouch right", xpos, ypos);
			break;
		case jump_f_idle: font.draw(batch, "jump f idle", xpos, ypos);
			break;
		case jump_b_idle: font.draw(batch, "jump b idle", xpos, ypos);
			break;
		case jump_idle: font.draw(batch, "jump idle", xpos, ypos);
			break;
		case walk_b_crouch: font.draw(batch, "walk crouch b", xpos, ypos);
			break;
		case walk_f_crouch: font.draw(batch, "walk crouch f", xpos, ypos);
			break;
		}
		
		ypos += 20;
		if (player.physics.OnGround) { font.draw(batch, "On ground" , xpos, ypos); }
		else { font.draw(batch, "On air", xpos, ypos); }
		
		ypos += 30;
		font.draw(batch, "last: " + Integer.toString(last_pressed), xpos, ypos);
		
		ypos += 30;
		font.draw(batch, "up: " + Integer.toString(last_pressed_up), xpos, ypos);
		
		ypos += 30;
		font.draw(batch, "input: " + player.state.last_input.toString(), xpos, ypos);
	}
	
	public void GetAnimation()
	{
		if (idle)
		{
			player.SetStatus();
			
			if (player.physics.OnGround)
			{
				player.status.status = PlayerStatus.Status.idle;
				player.Move(StateManager.Commands.idle);
			}
		}
		
		if (last_pressed_bool)
		{
			player.Move(StateManager.Commands.idle);			
		}
		
		//Diagonal jump has bigger priority than walking
		//That's why is after basicmovement
		GetBasicMovement();
		GetDiagonalJumps();
		CrouchDiagonals();
		GetWalkCrouch();
	}
	
	public void GetBasicMovement()
	{
		/*
		 * This function mostly checks the basic movement (walk, crouch)
		 */
		if (player.physics.OnGround && input_key.size() > 0)
		{
			switch(input_key.get(0))
			{
			case up:
				if (last_pressed_up < 10)
				{
					player.Move(StateManager.Commands.jump);
				}
				break;
			case down:
				if (down_pressed)
				{
					player.Move(StateManager.Commands.down);
				}
				break;
			case right: 
				if(right_pressed && !left_pressed)
				{
					player.Move(StateManager.Commands.right);
				}
				break;
			case left:
				if (left_pressed && !right_pressed)
				{
					player.Move(StateManager.Commands.left);
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void GetWalkCrouch()
	{
		if (player.physics.OnGround && input_key.size() > 2)
		{
			if (input_key.get(0) == Input_keys.down_r && input_key.get(1) == Input_keys.left)
			{
				player.Move(StateManager.Commands.left);
			}
		}
	}
	
	public void CrouchDiagonals()
	{

		if (player.physics.OnGround && input_key.size() > 3)
		{
			if (input_key.get(0) == Input_keys.down_r && input_key.get(1) == Input_keys.left_r)
			{
				player.Move(StateManager.Commands.down_l);
			}
			if (input_key.get(0) == Input_keys.down && input_key.get(1) == Input_keys.left_r)
			{
				player.Move(StateManager.Commands.down_l);
			}
			if (input_key.get(0) == Input_keys.down_r && input_key.get(1) == Input_keys.left)
			{
				player.Move(StateManager.Commands.down_l);
			}
			if (input_key.get(0) == Input_keys.down && input_key.get(1) == Input_keys.left)
			{
				player.Move(StateManager.Commands.down_l);
			}
		}
		
		if (player.physics.OnGround && input_key.size() > 3)
		{
			if (input_key.get(0) == Input_keys.down_r && input_key.get(1) == Input_keys.right_r)
			{
				player.Move(StateManager.Commands.down_r);
			}
			if (input_key.get(0) == Input_keys.down && input_key.get(1) == Input_keys.right_r)
			{
				player.Move(StateManager.Commands.down_r);
			}
			if (input_key.get(0) == Input_keys.down_r && input_key.get(1) == Input_keys.right)
			{
				player.Move(StateManager.Commands.down_r);
			}
			if (input_key.get(0) == Input_keys.down && input_key.get(1) == Input_keys.right)
			{
				player.Move(StateManager.Commands.down_r);
			}
		}
	}
	
	
	public void GetDiagonalJumps()
	{
		/*
		 * This checks the buffer for diagonal jumps 		
		 */
		
		if (player.physics.OnGround && input_key.size() > 3)
		{
			if (input_key.get(0) == Input_keys.up_r && input_key.get(1) == Input_keys.right_r)
			{
				player.Move(StateManager.Commands.jump_r);
			}
			if (input_key.get(0) == Input_keys.up && input_key.get(1) == Input_keys.right_r)
			{
				player.Move(StateManager.Commands.jump_r);
			}
			if (input_key.get(0) == Input_keys.up_r && input_key.get(1) == Input_keys.right)
			{
				player.Move(StateManager.Commands.jump_r);
			}
			if (input_key.get(0) == Input_keys.up && input_key.get(1) == Input_keys.right)
			{
				player.Move(StateManager.Commands.jump_r);
			}
		}
		

		if (player.physics.OnGround && input_key.size() > 3)
		{
			if (input_key.get(0) == Input_keys.up_r && input_key.get(1) == Input_keys.left_r)
			{
				player.Move(StateManager.Commands.jump_l);
			}
			if (input_key.get(0) == Input_keys.up && input_key.get(1) == Input_keys.left_r)
			{
				player.Move(StateManager.Commands.jump_l);
			}
			if (input_key.get(0) == Input_keys.up_r && input_key.get(1) == Input_keys.left)
			{
				player.Move(StateManager.Commands.jump_l);
			}
			if (input_key.get(0) == Input_keys.up && input_key.get(1) == Input_keys.left)
			{
				player.Move(StateManager.Commands.jump_l);
			}
		}
	}
	
}
