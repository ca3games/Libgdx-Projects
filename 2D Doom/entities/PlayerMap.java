package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerMap {

	public int x, y;
	public Texture player;
	
	public PlayerMap()
	{
		player = new Texture(Gdx.files.internal("player.png"));
	}
	
	public void Draw(SpriteBatch batch, int multiplier, int offset)
	{
		batch.draw(player, (x)*32, (y+offset)*32);
	}
	
	public void Dispose()
	{
		player.dispose();
	}
	
}
