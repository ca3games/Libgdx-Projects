package com.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player_HitBoxEditor {

	PlayerStatus.Status status;
	public StateManager state;
	public int x, y;
	BitmapFont font;
	
	Animation_Manager animation_editor;
	
	public Player_HitBoxEditor()
	{
		status = PlayerStatus.Status.idle;
		animation_editor = new Animation_Manager();
		x = Gdx.graphics.getWidth()/3;
		y = Gdx.graphics.getHeight()/3;
		animation_editor.x = x;
		animation_editor.y = y;
		animation_editor.x_off = x;
		animation_editor.y_off = y;
		
		font = new BitmapFont();
	}
	
	
	public void update()
	{	
		animation_editor.TouchDown();
	}
	
	public void Draw(SpriteBatch batch)
	{
		animation_editor.Draw(batch);
		font.setColor(Color.BLUE);
		DrawLayerNumber(batch, font);
	}
	
	public void RenderGUI(SpriteBatch batch)
	{
		animation_editor.RenderGUIEditor(batch);
		animation_editor.RenderAnimations(batch);
	}
	
	public void DrawShapesCurrent(ShapeRenderer box)
	{
		animation_editor.Draw_Hitbox_Current
		(box, animation_editor.index, animation_editor.layer,
				animation_editor.hitbox_width, animation_editor.hitbox_height,
				animation_editor.current_animation,
				animation_editor.x_off, animation_editor.y_off
				);
	}
	
	public void DrawShapes(ShapeRenderer box)
	{
		animation_editor.Draw_Hitbox
		(box, animation_editor.index, animation_editor.layer, animation_editor.current_animation,
				animation_editor.x_off, animation_editor.y_off
				);
	}
	
	public void DrawLayerNumber(SpriteBatch batch, BitmapFont font)
	{
		animation_editor.Draw_Hitbox(batch, font, animation_editor.index,
				animation_editor.current_animation,
				animation_editor.x_off, animation_editor.y_off);
		animation_editor.DrawListFrames(batch, animation_editor.index, animation_editor.layer,
				animation_editor.current_animation);
	}
	
	public void Dispose()
	{
		animation_editor.Dispose();
		font.dispose();
	}
	
}
