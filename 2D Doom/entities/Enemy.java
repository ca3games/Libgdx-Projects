package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

	public Sprite sprite;
	Texture texture;
	Vector2 velocity;
	public Rectangle rect;
	public int HP;
	
	public Enemy(float x, float y)
	{
		texture = new Texture(Gdx.files.internal("enemy01.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
		
		velocity = new Vector2(0, 0);
		rect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		HP = 50;
	}
	
	public void Margins()
	{
		rect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		if(sprite.getX() < 0) { sprite.setX(0); }
		if(sprite.getX() > Gdx.graphics.getWidth()) { sprite.setX(Gdx.graphics.getWidth()); }
		if (sprite.getY() < 0) { sprite.setY(0); }
		if (sprite.getY() > Gdx.graphics.getHeight()) { sprite.setY(Gdx.graphics.getHeight()); }
	}
	
	public void Seek(Vector2 playerposition)
	{
		float speedx = 1f;
		
		velocity.set(playerposition).sub(sprite.getX(), sprite.getY()).nor().scl(speedx);
		sprite.setX(sprite.getX() + velocity.x);
		sprite.setY(sprite.getY() + velocity.y);
	}
	
	public void Flee(Vector2 target)
	{
		float speedx = 1f;
		
		velocity.set(target).sub(sprite.getX(), sprite.getY()).nor().scl(speedx);
		velocity.scl(-1);
		
		sprite.setX(sprite.getX() + velocity.x);
		sprite.setY(sprite.getY() + velocity.y);
		
	}
	
	public void Arrival(Vector2 target)
	{
		float speedx = 1f;
		
		velocity.set(target).sub(sprite.getX(), sprite.getY()).nor().scl(speedx);
		sprite.setX(sprite.getX() + velocity.x);
		sprite.setY(sprite.getY() + velocity.y);
	}
	
	public void Draw(SpriteBatch batch)
	{
		sprite.draw(batch, 1f);
	}
	
	public void DrawBounds(ShapeRenderer shape)
	{
		shape.box(rect.x, rect.y, 0, rect.width, rect.height, 1);
	}
	
	public void Dispose()
	{
		texture.dispose();
	}
	
}
