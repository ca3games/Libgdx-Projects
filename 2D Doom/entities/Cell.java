package entities;


public class Cell {
	
	public enum Type
	{
		empty, wall
	}

	public Type type;
	public int room;
	
	public int freecheck;
}
