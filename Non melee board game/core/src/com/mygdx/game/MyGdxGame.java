package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import map.Map;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape;
	Map map;
	int cell_size;
	public BitmapFont font;
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLUE);
		
		cell_size = 32;
		NewMap();
		
		shape = new ShapeRenderer();
		shape.setColor(Color.BLACK);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			NewMap();
		}
		
		shape.begin(ShapeType.Filled);
		map.DrawTiles(shape);
		shape.end();
		
		batch.begin();
		//map.DrawCellInfo(font, batch);
		batch.end();
		
	}
	
	public void NewMap()
	{
		map = new Map(cell_size, 30, 20, new Vector2(
				(Gdx.graphics.getWidth() / 2) - (15 * cell_size),
				(Gdx.graphics.getHeight() / 2) - (10 * cell_size)
				));
	}
	
	@Override
	public void dispose () {
		
		
	}
}
