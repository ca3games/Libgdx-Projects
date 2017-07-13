package map;

import java.util.Random;

public class Map_Generator {

	private Cell[][] map;
	Random random;
	int width, height;

	public Cell[][] getNewMap(int cell, int w, int h) {

		random = new Random();
		map = new Cell[w][h];
		
		width = w;
		height = h;
		
		MakeEmptyMap(cell, w, h);
		Random_Room_Seeds(w, h);
		Make_Rooms();
		
		return map;
	}
	
	void MakeEmptyMap(int cell, int w, int h)
	{
		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				map[x][y] = new Cell(x*cell, y*cell, cell, cell, Cell.Cell_type.EMPTY);
				map[x][y].room = 0;
			}
		}
	}
	
	void Random_Room_Seeds(int w, int h)
	{
		for (int i = 0; i < 10; i++)
		{
			int x = random.nextInt(w-1) + 1;
			int y = random.nextInt(h-1) + 1;
			
			map[x][y].cell_type = Cell.Cell_type.ROOM_START;
			map[x][y].room = i;
		}
	}
	
	void Make_Rooms()
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (map[x][y].cell_type == Cell.Cell_type.ROOM_START)
				{
					for (int i = 0; i < 4; i++)
					{
						Random_Drunk_Wall(x, y, map[x][y].room, 0);
					}
					
				}
			}
		}
	}

	void Random_Drunk_Wall (int x, int y, int room, int id)
	{
		if (!IsInside(x, y))
		{
			return;
		}
		if (id > 50)
		{
			return;
		}
		
		if (map[x][y].cell_type == Cell.Cell_type.EMPTY)
		{
			map[x][y].cell_type = Cell.Cell_type.AIR;
			map[x][y].room = room;
		}
		
		int direction = random.nextInt(4);
		switch(direction)
		{
		case 1:	Random_Drunk_Wall(x-1, y, room, id++);
			break;
		case 2: Random_Drunk_Wall(x+1, y, room, id++);
			break;
		case 3: Random_Drunk_Wall(x, y-1, room, id++);
			break;
		case 4: Random_Drunk_Wall(x, y+1, room, id++);
			break;
		default: Random_Drunk_Wall(x+1, y, room, id++);
			break;
		}
	}
	
	boolean IsInside(int x, int y)
	{
		if (x < 0 || x > width-1 || y < 0 || y > height-1)
		{
			return false;
		}
		
		return true;
	}
}
