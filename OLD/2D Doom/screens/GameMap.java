package screens;

import java.util.Random;

import levels.Map;
import input.DungeonInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.ca3games.superextremefireofdoom.MainGame;

public class GameMap implements Screen {

	OrthographicCamera camera;
	public MainGame game;
	Map mapa;
	
	int playx, playy;
	Random random;
	
	int playerHP;
	Preferences pref;
	
	public GameMap(MainGame g)
	{
		game = g;
		NewMap();
		
		playx = 5;
		playy = 5;
		
		pref = Gdx.app.getPreferences("currentscore");
		
		playerHP = pref.getInteger("HP");
		
	}
	
	@Override
	public void show() {
		random = new Random();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		
		Gdx.input.setInputProcessor(new GestureDetector(new DungeonInput(this)));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);

		game.font.setColor(Color.BLUE);
        game.batch.begin();
        mapa.Draw(game.batch, game.font);
        game.font.draw(game.batch, "HP: " + Integer.toString(pref.getInteger("HP")) + " steps: " + Integer.toString(pref.getInteger("steps")) , 10, 20);        
        game.batch.end();
		
	}

	public void MovePlayer(int x, int y)
	{
		int steps = pref.getInteger("steps");
		playx = x;
		playy = y;
		if (x == 6 && y == 5) { mapa.MovePlayer(1, 0); steps++; pref.putInteger("steps", steps); }
		if (x == 4 && y == 5) { mapa.MovePlayer(-1, 0); steps++; pref.putInteger("steps", steps); }
		if (x == 5 && y == 2) { mapa.MovePlayer(0, 1); steps++; pref.putInteger("steps", steps); }
		if (x == 5 && y == 8) { mapa.MovePlayer(0, -1); steps++; pref.putInteger("steps", steps); }
		
		pref.flush();
		
		if (random.nextInt(5) == 2)
		{
			game.setScreen(new BattleSystem(game));
		}
	}
	
	void NewMap()
	{
		do
		{
			mapa = new Map();
		}
		while (mapa.AfterMap());
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

	@Override
	public void dispose() {
		mapa.Dispose();				
	}

	
	
}
