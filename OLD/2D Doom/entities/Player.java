package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Player {

	public Sprite sprite;
	Rectangle rect;
	Texture tex;
	
	public Player()
	{
		tex = new Texture(Gdx.files.internal("player.png"));
		sprite = new Sprite(tex);
		
		sprite.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, sprite.getWidth(), sprite.getHeight());
	}
	
	public void Margins()
	{
		rect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		if(sprite.getX() < 0) { sprite.setX(0); }
		if(sprite.getX() > Gdx.graphics.getWidth() - sprite.getWidth()) { sprite.setX(Gdx.graphics.getWidth() - sprite.getWidth()); }
		if (sprite.getY() < 0) { sprite.setY(0); }
		if (sprite.getY() > Gdx.graphics.getHeight() - sprite.getHeight()) { sprite.setY(Gdx.graphics.getHeight() - sprite.getHeight()); }
	}
	
	
	public void Move(float x, float y)
	{
		sprite.setBounds(sprite.getX() + x, sprite.getY() + y, sprite.getWidth(), sprite.getHeight());
	}
	
	public void Draw(SpriteBatch batch)
	{
		sprite.draw(batch, 1f);
	}
	
	public void Dispose()
	{
		tex.dispose();
	}
}
