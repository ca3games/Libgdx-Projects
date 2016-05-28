/*
 * This class holds the current status of the player
 */

package com.Engine;

public class PlayerStatus {

	public static enum Status
	{
		walk, walk_b, idle, crouch,
		crouch_b, crouch_f,
		walk_b_crouch, walk_f_crouch,
		run, run_b,
		jump, jump_f, jump_b,
		walk_down,
		jump_idle, jump_f_idle, jump_b_idle
	}
	
	Status status;
	
	public PlayerStatus()
	{
		status = Status.idle;
	}
	
	public Status GetStatus()
	{
		return status;
	}
	
	public void SetStatus(Status state)
	{
		status = state;
	}
	
}
