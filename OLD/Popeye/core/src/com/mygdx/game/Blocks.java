package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Blocks {

	public Texture background;
	public Image image;
	
	public List<Rectangle> floor;
	
	public Blocks()
	{
		background = new Texture(Gdx.files.internal("background.png"));
		image = new Image(background);
		
		floor = new ArrayList<Rectangle>();
		floor.add(new Rectangle(30,140,750,16));
		floor.add(new Rectangle(30,240,750,16));
		floor.add(new Rectangle(0,340,200,16));
		floor.add(new Rectangle(600,340,200,16));
		
		image.setBounds(0, 0, 800, 600);
	}
	
	public void Draw(SpriteBatch batch)
	{
		image.draw(batch, 1);
	}
	public void Draw_Rect(ShapeRenderer shape)
	{
		for(Rectangle i : floor)
		{
			shape.box(i.x, i.y, 0, i.width, i.height, 1);
		}
	}
	
	public void dispose()
	{
		background.dispose();
	}
		
}
