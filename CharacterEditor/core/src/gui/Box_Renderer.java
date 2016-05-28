package gui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import character.Animation_Hit;
import character.Hitbox;

public class Box_Renderer {
	
	public Chara chara;
	
	public ShapeRenderer Box;
	
	public Box_Renderer(Chara c)
	{
		Box = new ShapeRenderer();
		chara = c;
	}
	
	public void DrawBoxesLabel(SpriteBatch batch, BitmapFont font)
	{
		try
		{
		int x = 0;
		int y = 0;
		
		List<Hitbox> hitbox = chara.character.animation.get(chara.animation_index).frame.get(chara.frame_index)
					.hitbox;
		for (int i = 0; i < hitbox.size(); i++) {
			Hitbox hit = hitbox.get(i);
			
			if (i == chara.hitbox_index)
			{
				font.setColor(Color.RED);				
			}
			else
			{
				font.setColor(Color.BLUE);
			}
			
			y++;
			font.draw(batch, "L: " + Integer.toString(y) + " X: " + Integer.toString((int) hit.rectangle.x) + " Y: " +
					Integer.toString((int) hit.rectangle.y) + " Type: " +
					hit.type.toString()
			, x, Gdx.graphics.getHeight() - y * 16);
			font.draw(batch, "L " + Integer.toString(y), hit.rectangle.x + chara.center_x, hit.rectangle.y + chara.center_y);
			font.draw(batch, "F " + Integer.toString(chara.frame_index), hit.rectangle.x + 50 + chara.center_x, hit.rectangle.y + chara.center_y);
			
			font.draw(batch, "X: " + Integer.toString(
					(int) chara.character.animation.get(chara.animation_index).frame.get(chara.frame_index).offset_x
					) + " Y: " + Integer.toString(
					(int) chara.character.animation.get(chara.animation_index).frame.get(chara.frame_index).offset_y
					), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()
					);
			
			font.draw(batch, chara.character.animation.get(chara.animation_index).name_animation, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()-20);
			if (chara.character.animation.get(chara.animation_index).name_anim_file != null)
			{
				font.draw(batch, chara.character.animation.get(chara.animation_index).name_anim_file, Gdx.graphics.getWidth()/4 + 100, Gdx.graphics.getHeight()-20);
			}
		}
		
		x = Gdx.graphics.getWidth() - 300;
		y = 1;
		
		List<Animation_Hit> animation = chara.character.animation;
		for (int i = 0; i < animation.size(); i++) {
			Animation_Hit hit = animation.get(i);
			y++;
			
			if (chara.animation_index == i)
			{
				font.setColor(Color.RED);
			}
			else
			{
				font.setColor(Color.BLUE);
			}
			
			font.draw(batch, Integer.toString(y) + " Anim: " + hit.name_animation, x, Gdx.graphics.getHeight() - y*16);
		}
		
			font.draw(batch, "Anim size " + Integer.toString(chara.character.animation.size()), x, Gdx.graphics.getHeight() - 16);
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void Draw()
	{	
		Box.begin(ShapeRenderer.ShapeType.Line);
		try
		{
		for(Hitbox hit : chara.character
				.animation.get(chara.animation_index).frame.get(chara.frame_index).
				hitbox)
		{
			switch (hit.type)
			{
			case air: Box.setColor(Color.BLUE);
				break;
			case air_attack: Box.setColor(Color.RED);
				break;
			case air_fall: Box.setColor(Color.BLUE);
				break;
			case air_grab: Box.setColor(Color.ORANGE);
				break;
			case block: Box.setColor(Color.BROWN);
				break;
			case crouch: Box.setColor(Color.GREEN);
				break;
			case grab: Box.setColor(Color.ORANGE);
				break;
			case low_block: Box.setColor(Color.BROWN);
				break;
			case normal: Box.setColor(Color.GREEN);
				break;
			case nulo: Box.setColor(Color.GRAY);
				break;
			case low_attack: Box.setColor(Color.PINK);
				break;
			case normal_attack: Box.setColor(Color.RED);
				break;
			case overhead_attack: Box.setColor(Color.PINK);
				break;
			}
			Box.rect(hit.rectangle.x + chara.center_x, hit.rectangle.y + chara.center_y, hit.rectangle.width, hit.rectangle.height);
		}
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
		Box.end();
	}
	
}
