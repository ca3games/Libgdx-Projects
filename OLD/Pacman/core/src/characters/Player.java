package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	
	public Sprite sprite;
	
	public Player()
	{
		sprite = new Sprite(new Texture(Gdx.files.internal("player.png")));
		sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	public void Render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
}
