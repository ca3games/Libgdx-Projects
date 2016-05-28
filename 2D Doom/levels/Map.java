package levels;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import entities.Cell;
import entities.PlayerMap;

public class Map {

	public Texture cell;
	public Texture wall;
	public Sprite s_wall;
	
	Cell[][] mapa;
	
	int map_width, map_height;
	Random random;
	
	PlayerMap player;
	Vector2 position_player;
	
	public int all_free, free;
	
	
	public Map()
	{
		player = new PlayerMap();
		random = new Random();
		cell = new Texture(Gdx.files.internal("cell.png"));
		wall = new Texture(Gdx.files.internal("wall.png"));
		s_wall = new Sprite();
		
		map_width = 10;
		map_height = 10;
		
		mapa = new Cell[map_width][map_height];
		
		for (int x = 0;  x < map_width; x++)
		{
			for (int y = 0; y < map_height; y++)
			{
				mapa[x][y] = new Cell();
				mapa[x][y].type = Cell.Type.wall;
				mapa[x][y].room = 99;
			}
		}
		
		for(int i = 0; i < 10; i++)
		{
			int m_w = random.nextInt(map_width);
			int m_h = random.nextInt(map_height);
			int r_w = random.nextInt(4); if (r_w < 2) { r_w = 2;}
			int r_h = random.nextInt(4); if (r_h < 2) { r_h = 2;}
			DrunkenPath(m_w, m_h, i);
		}
		
		TotalFree();
		FloodFill();
		
		
		for (int x = 0; x < map_width; x++)
		{
			for (int y = 0; y < map_height; y++)
			{
				if (mapa[x][y].freecheck == 2)
				{
					mapa[x][y].freecheck = 1;
				}
			}
		}
		
		position_player = new Vector2(0, 0);
		InitialPosition();
	}
	
	public void MovePlayer(int x, int y)
	{
		if (IsValidCell(player.x + x, player.y + y))
		{
			if (mapa[player.x + x][player.y + y].type == Cell.Type.empty)
			{
				player.x += x;
				player.y += y;
			}
		}
	}
	
	public boolean AfterMap()
	{
		if (all_free >= free - 5) { return false; }
		return true;
	}
	
	public void InitialPosition()
	{
		player.x = 0;
		player.y = 0;
		
		do
		{
			PlacePlayer(random.nextInt(map_width-1), random.nextInt(map_height-1));
		}
		while (player.x == 0 && player.y == 0);
	}
	
	public boolean PlacePlayer(int x, int y)
	{
		if (!this.IsValidCell(x, y))
		{
			return false;
		}
		
		if (mapa[x][y].freecheck != 1)
		{
			return false;
		}
		
		if (mapa[x][y].freecheck == 1)
		{
			mapa[x][y].freecheck = 2;
			player.x = x;
			player.y = y;
			return true;
		}
		
		
		PlacePlayer(x-1, y-1);
		PlacePlayer(x, y-1);
		PlacePlayer(x+1, y-1);
		PlacePlayer(x-1, y);
		PlacePlayer(x+1, y);
		PlacePlayer(x-1, y+1);
		PlacePlayer(x, y+1);
		PlacePlayer(x+1, y+1);
		
		
		return false;
	}
	
	public void TotalFree()
	{
		for (int x = 0; x < map_width; x++)
		{
			for (int y = 0; y < map_height; y++)
			{
				if (mapa[x][y].room != 99)
				{
					free++;
					mapa[x][y].freecheck = 1;
				}
				else
				{
					mapa[x][y].freecheck = 0;
				}
			}
		}
	}
	
	public void FloodFill()
	{	
		Vector2 pos = GetPosition();
		free = 0;
		all_free = 0;
		TotalFree();
		int x = (int)pos.x;
		int y = (int)pos.y;
			FloodRecursion(x, y, 0);
			FloodRecursion(x+1, y, 0);
			FloodRecursion(x, y+1, 0);
			FloodRecursion(x, y-1, 0);
			FloodRecursion(x-1, y, 0);
			FloodRecursion(x+1, y, 0);
	}
	
	public Vector2 GetPosition()
	{
		for(int x = 0; x < map_width; x++)
		{
			for (int y = 0; y < map_height; y++)
			{
				if (mapa[x][y].freecheck == 1 && x >= 1 && y >= 1)
				{
					return new Vector2(x, y);
				}
			}
		}
		return new Vector2(1, 1);
	}
	
	public boolean FloodRecursion(int x, int y, int counter)
	{
		if (counter > 1500) { return false; } else { counter++; }
		
		if (!IsValidCell(x, y))
		{
			return false;
		}
		
		if (mapa[x][y].freecheck == 1)
		{
			all_free++;
			mapa[x][y].freecheck = 2;
			
			FloodRecursion(x, y, counter);
			FloodRecursion(x, y+1, counter);
			FloodRecursion(x, y-1, counter);
			FloodRecursion(x-1, y, counter);
			FloodRecursion(x+1, y, counter);
			FloodRecursion(x+1, y, counter);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public void DrunkenPath(int x, int y, int room)
	{	
		DrunkenStep(x+1, y, 0, room);
		DrunkenStep(x, y+1, 0, room);
		DrunkenStep(x-1, y, 0, room);
		DrunkenStep(x, y-1, 0, room);
	}
	
	public void DrunkenStep(int x, int y, int counter, int room_seed)
	{
		if (counter > 2) { return; } else { counter++; }
		
		if (x < 0) { x = 0; }
		if (y < 0) { y = 0; }
		if (x > map_width-1) { x = map_width-1; }
		if (y > map_height-1) { y = map_height-1; }
		
		if (mapa[x][y].room != 99) 	{ return; }
		else { 
			mapa[x][y].room = room_seed; 
			mapa[x][y].type = Cell.Type.empty;
		}
		
		DrunkenStep(x+1, y, counter, room_seed);
		DrunkenStep(x, y+1, counter, room_seed);
		DrunkenStep(x-1, y, counter, room_seed);
		DrunkenStep(x, y-1, counter, room_seed);
	}
	
	public void Draw(SpriteBatch batch, BitmapFont font)
	{
		for(int x = 0; x < map_width; x++)
		{
			for (int y = 0; y < map_height; y++)
			{
				switch(mapa[x][y].type)
				{
				case empty: batch.draw(this.cell, x*32, (y+3)*32, 32, 32);
					break;
				case wall: batch.draw(this.wall, x*32, (y+3)*32, 32, 32);
					break;
				default:
					break;
				}
				//font.draw(batch, Integer.toString(mapa[x][y].freecheck), x*32, (y+3)*32);
			}
		}
		player.Draw(batch, 32, 3);
	}
	
	public boolean IsValidCell(int x, int y)
	{
		if (x < 0) { return false; }
		if (y < 0) { return false; }
		if (x > map_width-1) { return false; }
		if (y > map_height-1) { return false; }
		
		return true;
	}
	
	public void Dispose()
	{
		cell.dispose();
		wall.dispose();
		player.Dispose();
	}
}
