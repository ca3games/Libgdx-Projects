package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Map_Generator {

	private Cell[][] map;
	Random random;
	int width, height;
	int size;
	public List<Rectangle> TestQuadtree;

	public Cell[][] getNewMap(int s, int w, int h) {

		TestQuadtree = new ArrayList<Rectangle>();
		
		size = s;
		
		random = new Random();
		map = new Cell[w][h];
		
		width = w;
		height = h;
		
		MakeEmptyMap(w, h);
		
		Random_Room_Quadtree(0, 0, w, h, 0);
		Random_Room_Seeds();
		Make_Rooms_Squares();
		Make_Rooms();
		
		return map;
	}
	
	void MakeEmptyMap(int w, int h)
	{
		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				map[x][y] = new Cell(x*size, y*size, size, size, Cell.Cell_type.EMPTY);
				map[x][y].room = 0;
			}
		}
	}
	
	void Random_Room_Seeds()
	{
		int i_rooms = random.nextInt(10) + 2;
		for (int i = 0; i < i_rooms; i++)
		{
			int x = random.nextInt(width-1) + 1;
			int y = random.nextInt(height-1) + 1;
			
			map[x][y].cell_type = Cell.Cell_type.RAMDON_WALK_START;
			map[x][y].room = i;
		}
	}
	
	void Random_Room_Quadtree(int ix, int iy, int w, int h, int level)
	{
		TestQuadtree.add(new Rectangle(ix, iy, w, h));
		if (level < 1)
		{
			Random_Room_Quadtree(ix, iy, w/2, h/2, ++level);
			Random_Room_Quadtree(ix+w/2, iy, w/2, h/2, ++level);
			Random_Room_Quadtree(ix, iy+h/2, w/2, h/2, ++level);
			Random_Room_Quadtree(ix+w/2, iy+h/2, w/2, h/2, ++level);
		}
		else
		{
			int x = random.nextInt(w) + ix;
			int y = random.nextInt(h) + iy;
			
			map[x][y].cell_type = Cell.Cell_type.RAMDON_WALK_START;
			return;
		}
	}
	
	void Make_Rooms_Squares()
	{
		int i = 0;
		int rooms = 0;
		int limit = random.nextInt(8)+ 1;
		do 
		{
			i++;
			int x = random.nextInt(width - 1) + 1;
			int y = random.nextInt(height - 1) + 1;
			
			int left = random.nextInt(5) + 1;
			int right = random.nextInt(5) + 1;
			int top = random.nextInt(5) + 1;
			int bottom = random.nextInt(5) + 1;
			
			if (Check_Room_Square(x - left, y - top, x + right, y + bottom))
			{
				this.Make_Room_Square(x - left, y -top, x + right, y + bottom, rooms);
				rooms++;
			}
			if (i > 1000)
			{
				return;
			}
		}
		while (rooms < limit);
	}
	
	void Make_Rooms()
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (map[x][y].cell_type == Cell.Cell_type.RAMDON_WALK_START)
				{
					for (int i = 0; i < 4; i++)
					{
						Random_Drunk_Wall(x, y, map[x][y].room, 0);
					}
					
				}
			}
		}
	}
	
	boolean Check_Room_Square(int ix, int iy, int w, int h)
	{
		for (int x = ix; x <= w; ++x)
		{
			for (int y = iy; y <= h; ++y)
			{
				if (!IsInside(x, y))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	void Make_Room_Square(int ix, int iy, int w, int h, int room)
	{
		for (int x = ix; x <= w; ++x)
		{
			for (int y = iy; y <= h; ++y)
			{
				if (x == ix || x == w || y == iy || y == h)
				{
					map[x][y].cell_type = Cell.Cell_type.WALL;
					map[x][y].room = 0;
				}
				else
				{
					map[x][y].cell_type = Cell.Cell_type.AIR;
					map[x][y].room = room;
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
