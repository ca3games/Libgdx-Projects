package map;

import com.badlogic.gdx.math.Rectangle;

public class Cell {

	public Rectangle rect;
	public int room;
	public enum Cell_type 
	{
		EMPTY, WALL, DIRT, ROOM_START, AIR
		
	}
	public Cell_type cell_type;
	
	public Cell(int x, int y, int width, int height, Cell_type cel)
	{
		rect = new Rectangle(x, y, width, height);	
		cell_type = cel;
	}
	
}
