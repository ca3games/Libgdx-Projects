package screens;

import input.Playerinput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ca3games.superextremefireofdoom.MainGame;

import entities.Enemy;
import entities.Player;

public class BattleSystem extends Stage implements Screen {
	SpriteBatch batch;
	Player player;
	Random random;
	
	List<Enemy> enemychase;
	ShapeRenderer shape;
	
	MainGame game;
	
	int player_HP;
	Preferences pref;
	BitmapFont font;
	
	
	public BattleSystem(MainGame g)
	{
		super(new StretchViewport(320.0f, 480.0f, new OrthographicCamera()));
		game = g;
		pref = Gdx.app.getPreferences("currentscore");
		player_HP = pref.getInteger("HP");
		
	}
	
	@Override
	public void dispose()
	{
		player.Dispose();
		
		for (Enemy i : enemychase)
		{
			i.Dispose();
		}
	}
	
	public void EnemyTap(float x, float y)
	{	
		boolean create = false;
		for (Enemy i : enemychase)
		{
			if (i.rect.contains(new Vector2(x, y)))
			{
				float xr = random.nextInt(Gdx.graphics.getWidth());
				i.sprite.setBounds(xr, 0, i.sprite.getWidth(), i.sprite.getHeight());
				create = true;
				
				i.HP -= 25;
				if (i.HP < 1)
				{
					pref.putInteger("HP", player_HP);
					pref.flush();
					game.setScreen(game.mapa);
				}
			}
		}
		
		if (create)
		{
			if(random.nextInt(4) == 2 && enemychase.size() < 50)
			{
				SpawnChase();
			}
		}
	}
	
	public void SpawnChase()
	{
		float x = random.nextInt(Gdx.graphics.getWidth());
		float y = random.nextInt(Gdx.graphics.getHeight());
		enemychase.add(new Enemy(x, y));
	}

	@Override
	public void show() {
		
		player_HP = pref.getInteger("HP");
		
		random = new Random();
		batch = new SpriteBatch();
		player = new Player();
		
		enemychase = new ArrayList<Enemy>();
		SpawnChase();
		
		GestureDetector play = new GestureDetector(new Playerinput(player, this));
		Gdx.input.setInputProcessor(play);
		shape = new ShapeRenderer();
		shape.setColor(Color.GREEN);
		
		font = new BitmapFont();
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for (Enemy i : enemychase)
		{
			i.Seek(new Vector2(player.sprite.getX(), player.sprite.getY()));
			i.Margins();
		}
		
		font.setColor(Color.BLUE);
		batch.begin();
		player.Draw(batch);
		font.draw(batch, "HP: " + Integer.toString(player_HP), 10, 10);
		player.Margins();
		for (Enemy i : enemychase)
		{
			i.Draw(batch);
		}
		
		batch.end();
		
		shape.begin(ShapeType.Line);
		for (Enemy i : enemychase)
		{
			i.DrawBounds(shape);
		}
		shape.end();
		
		for (Enemy i : enemychase)
		{
			if (i.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle()))
			{
				player_HP -= 10;
				pref.putInteger("HP", player_HP);
				pref.flush();
				
				i.sprite.setX(random.nextInt(Gdx.graphics.getWidth()));
				i.sprite.setY(random.nextInt(Gdx.graphics.getHeight()));
				
				if (player_HP < 1)
				{
					pref.putInteger("HP", player_HP);
					pref.flush();
					game.setScreen(new GameOver(game));
					dispose();
				}
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
