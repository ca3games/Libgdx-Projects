package com.pong.ca3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shape;
	Paddle player, ai;
	Ball ball;
	int score_player, score_ai;
	BitmapFont score;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		player = new Paddle(50, Gdx.graphics.getHeight()/2, 5, 10, 50);
		ai = new Paddle (Gdx.graphics.getWidth()-50, Gdx.graphics.getHeight()/2, 5, 10, 50);
		ball = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 5, 15, 15);
		score_player = 0;
		score_ai = 0;
		score = new BitmapFont();
		score.setColor(Color.WHITE);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Keys.UP) && player.body.y < Gdx.graphics.getHeight() - player.body.height)
		{
			player.body.y += player.speed;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) && player.body.y > 0)
		{
			player.body.y -= player.speed;
		}
		
		if (ball.body.y > ai.body.y)
		{
			ai.body.y += ai.speed - 1;			
		}
		else
		{
			ai.body.y -= ai.speed - 1;
		}
		
		if (ball.body.x < 0)
		{
			ball.body.x = Gdx.graphics.getWidth()/2;
			score_ai++;
		}
		
		if (ball.body.x > Gdx.graphics.getWidth() - ball.body.width)
		{
			ball.body.x = Gdx.graphics.getWidth()/2;
			score_player++;
		}
		
		ball.Update();
		ball.Collision(player.body, ai.body);
		
		batch.begin();
		score.draw(batch, Integer.toString(score_player), Gdx.graphics.getWidth()/3, 50);
		score.draw(batch, Integer.toString(score_ai), Gdx.graphics.getWidth()/3*2, 50);
		batch.end();
		
		shape.begin(ShapeType.Filled);
		shape.box(player.body.x, player.body.y, 0, player.body.width, player.body.height, 1);
		shape.box(ai.body.x, ai.body.y, 0, ai.body.width, ai.body.height, 1);
		shape.box(ball.body.x, ball.body.y, 0, ball.body.width, ball.body.height, 1);
		shape.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
