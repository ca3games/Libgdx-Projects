package map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Map {

	public int width, height;
	public Cell[][] map;
	public Vector2 offset;
	public int size;
	Map_Generator generator;
	
	public Map(int cell_size, int w, int h, Vector2 o)
	{
		map = new Cell[w][h];
		width = w;
		height = h;
		size = cell_size;
		
		generator = new Map_Generator();
		
		map = generator.getNewMap(cell_size, width, height);
		offset = o;
	}
	
	public void Draw(ShapeRenderer batch)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				switch (map[x][y].cell_type)
				{
				case EMPTY:
					batch.setColor(Color.BLACK);
					break;
				case RAMDON_WALK_START:
					batch.setColor(Color.RED);
					break;
				default:
					break;
				}
				batch.box(offset.x + map[x][y].rect.x, offset.y + map[x][y].rect.y, 0, size, size, 1);
				
			}
		}
		
		batch.setColor(Color.BLUE);
		for (Rectangle i : generator.TestQuadtree)
		{
			batch.box((i.x * size) + offset.x, (i.y * size) + offset.y, 0, i.width * size, i.height * size, 1);
		}
	}
	
	public void DrawTiles(ShapeRenderer shape)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				switch (map[x][y].cell_type)
				{
				case EMPTY:
					shape.setColor(Color.BROWN);
					break;
				case RAMDON_WALK_START:
					shape.setColor(Color.ORANGE);
					break;
				case AIR:
					shape.setColor(Color.WHITE);
				case DIRT:
					break;
				case WALL:
					shape.setColor(Color.GRAY);
					break;
				case ROOM_SEED:
					shape.setColor(Color.RED);
					break;
				default:
					break;
				}
				
				shape.box(offset.x + map[x][y].rect.x, offset.y + map[x][y].rect.y, 0, size, size, 1);
			}
		}
	}
	
	public void DrawCellInfo(BitmapFont font, SpriteBatch batch)
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				font.draw(batch, Integer.toString(map[x][y].room), offset.x + map[x][y].rect.x + 10, offset.y + map[x][y].rect.y + 10);
			}
		}
	}
	
}
