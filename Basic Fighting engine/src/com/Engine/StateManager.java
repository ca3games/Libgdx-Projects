package com.Engine;


public class StateManager {

	Player oponent, me;
	enum Commands
	{
		idle, down, 
		jump, jump_l, jump_r, 
		left, right,
		down_l, down_r
	}
	
	Commands last_input;
	PlayerStatus status;
	public boolean last_pressed_idle, last_up;
	PhysicsManager physics;
	public InputReceiver inputreceiver;
	
	public StateManager(Player op, Player m, PlayerStatus ps, PhysicsManager phy, InputReceiver ir)
	{
		oponent = op;
		me = m;
		status = ps;
		
		last_pressed_idle = false;
		last_up = false;
		physics = phy;
		inputreceiver = ir;
		last_input = Commands.idle;
	}
	
	public void Update()
	{
		SetCommands();
		SetStatus();
	}
	
	@SuppressWarnings("static-access")
	public void SetCommands()
	{
		switch(last_input)
		{
		case down: if (
				status.status == status.status.idle
				)
		{
			status.status = status.status.crouch;
		}
			break;
		case down_l:
			if (
					status.status == status.status.idle ||
					status.status == status.status.walk_b
					||
					status.status == status.status.crouch
				)
			{
				status.status = status.status.crouch_b;
			}
			break;
		case down_r:
			if (
					status.status == status.status.idle ||
					status.status == status.status.walk_b
				)
			{
				status.status = status.status.crouch_f;
			}
			break;
		case idle:
			break;
		case jump:
			if (
					status.status == status.status.idle 
				)
			{
				status.status = status.status.jump;
			}
			break;
		case jump_l:
			if (
					status.status == status.status.walk_b ||
					status.status == status.status.crouch ||
					status.status == status.status.idle
				)
			{
				status.status = status.status.jump_b;
			}
			break;
		case jump_r:
			if (
					status.status == status.status.walk ||
					status.status == status.status.crouch ||
					status.status == status.status.idle
				)
			{
				status.status = status.status.jump_f;
			}
			break;
		case left:
			if (
					status.status == status.status.idle ||
					status.status == status.status.crouch
				)
			{
				status.status = status.status.walk_b;
			}
			break;
		case right:
			if (
					status.status == status.status.idle ||
					status.status == status.status.crouch
				)
			{
				status.status = status.status.walk;
			}
			break;
		
		
		}
	}
	
	public void SetStatus()
	{
		switch(this.status.status)
		{
		case crouch:
			this.Crouch_Walk();
			break;
		case idle:
			this.Idle();
			break;
		case jump: 
			this.Jump();
			status.status = PlayerStatus.Status.jump_idle;
			break;
		case jump_b:
			status.status = PlayerStatus.Status.jump_b_idle;
			if (physics.OnGround && inputreceiver.last_pressed_up < 10)
			{
				this.Jump_D();
				this.Jump_Back_X();
			}
			break;
		case jump_f:
			status.status = PlayerStatus.Status.jump_f_idle;
			if (physics.OnGround && inputreceiver.last_pressed_up < 10)
			{
				this.Jump_D();
				this.Jump_F_X();
			}
			break;
		case run:
			break;
		case run_b:
			break;
		case walk:
			if(physics.OnGround)
			{
				if (inputreceiver.up_pressed && !inputreceiver.left_pressed 
						&& !inputreceiver.down_pressed && inputreceiver.last_pressed_up < 20 
						&& last_input != StateManager.Commands.idle)
				{
					status.status = PlayerStatus.Status.jump_f;
					return;
				}
				if (inputreceiver.down_pressed)
				{
					status.status = PlayerStatus.Status.walk_f_crouch;
					return;
				}
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.walk_b;
					return;
				}
				physics.Speed_x = physics.walk_speed;
				this.Walk();
			}
			else
			{
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.jump_b_idle;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.jump_f_idle;
					return;
				}
				status.status = PlayerStatus.Status.jump_idle;
			}
			break;
		case walk_b:
			if(physics.OnGround)
			{
				if (inputreceiver.up_pressed && !inputreceiver.right_pressed 
						&& !inputreceiver.down_pressed && inputreceiver.last_pressed_up < 20
						&& last_input != StateManager.Commands.idle)
				{
					status.status = PlayerStatus.Status.jump_b;
					return;
				}
				if (inputreceiver.down_pressed)
				{
					status.status = PlayerStatus.Status.walk_b_crouch;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.walk;
					return;
				}
				physics.Speed_x = -physics.walk_speed;
				this.Walk();
			}
			else
			{
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.jump_b_idle;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.jump_f_idle;
					return;
				}
				status.status = PlayerStatus.Status.jump_idle;
			}
			break;
		case walk_down:
			this.Crouch_Walk();
			break;
		case jump_idle:
			if (physics.OnGround)
			{
				physics.Speed_x = 0;
				status.status = PlayerStatus.Status.idle;
				return;
			}
			break;
		case crouch_b:
			this.Crouch_Walk();
			break;
		case crouch_f:
			this.Crouch_Walk();
			break;
		case walk_b_crouch: Crouch_Walk();
			break;
		case walk_f_crouch: Crouch_Walk();
			break;
		case jump_b_idle: 
			if (physics.OnGround)
			{
				status.status = PlayerStatus.Status.idle;
				return;
			}
			else
			{
				this.Jump_Back_X();
			}
			break;
		case jump_f_idle: 
			if (physics.OnGround)
			{
				status.status = PlayerStatus.Status.idle;
				return;
			}
			else
			{
				this.Jump_F_X();
			}
			break;
		default:
			break;
		
		}
	}
	
	public void Crouch()
	{
		if (inputreceiver.left_pressed)
		{
			status.status = PlayerStatus.Status.walk_b;
			return;
		}
		if (inputreceiver.right_pressed)
		{
			status.status = PlayerStatus.Status.walk;
			return;
		}
		physics.Speed_x = 0;
	}
	
	
	public void Crouch_Walk()
	{
		physics.Speed_x = 0;
		if (inputreceiver.down_pressed)
		{
			return;
		}
		if (inputreceiver.left_pressed)
		{
			status.status = PlayerStatus.Status.walk_b;
			return;
		}
		if (inputreceiver.right_pressed)
		{
			status.status = PlayerStatus.Status.walk;
			return;
		}
	}
	
	public void Idle()
	{
		if (physics.OnGround)
		{
			if (inputreceiver != null)
			{
			if (inputreceiver.down_pressed)
			{
				status.status = PlayerStatus.Status.crouch;
				return;
			}
			if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
			{
				status.status = PlayerStatus.Status.walk_b;
				return;
			}
			if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
			{
				status.status = PlayerStatus.Status.walk;
				return;
			}
			}
			physics.Speed_x = 0;
		}
	}
	
	public void Jump()
	{
		if (physics.OnGround)
		{
			if(inputreceiver.last_pressed_up < 30)
			{
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.jump_b;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.jump_f;
					return;
				}
				
				physics.y+= 1;
				physics.Jump();
			}
			else
			{
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.walk_b;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.walk;
					return;
				}
				
				status.status = PlayerStatus.Status.idle;
			}
		}
		else
		{
			status.status = PlayerStatus.Status.jump_idle;
		}
	}
	
	void Jump_D()
	{
		if (physics.OnGround)
		{
			if(inputreceiver.last_pressed_up < 10)
			{
				physics.y++;
				physics.Jump();
			}
			else
			{
				if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
				{
					status.status = PlayerStatus.Status.walk_b;
					return;
				}
				if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
				{
					status.status = PlayerStatus.Status.walk;
					return;
				}
				status.status = PlayerStatus.Status.idle;
			}
		}
	}
	
	void Jump_Back_X()
	{
		if (!physics.OnGround)
		{
			status.status = PlayerStatus.Status.jump_b_idle;
			physics.Speed_x = -physics.walk_speed;	
		}
		else
		{
			status.status = PlayerStatus.Status.idle;
			if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
			{
				status.status = PlayerStatus.Status.walk_b;
				return;
			}
			if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
			{
				status.status = PlayerStatus.Status.walk;
				return;
			}
		}
		
	}
	
	void Jump_F_X()
	{
		if (!physics.OnGround)
		{
			status.status = PlayerStatus.Status.jump_f_idle;
			physics.Speed_x = physics.walk_speed;	
		}
		else
		{
			status.status = PlayerStatus.Status.idle;
			if (inputreceiver.left_pressed && !inputreceiver.right_pressed)
			{
				status.status = PlayerStatus.Status.walk_b;
				return;
			}
			if (inputreceiver.right_pressed && !inputreceiver.left_pressed)
			{
				status.status = PlayerStatus.Status.walk;
				return;
			}
		}
	}
	
	void Walk()
	{
		
	}
}
