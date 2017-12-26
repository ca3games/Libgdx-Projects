package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ca3games.superextremefireofdoom.MainGame;

public class TitleScreen implements Screen {

	OrthographicCamera camera;
	MainGame game;
	
	Preferences pref;
	
	public TitleScreen(MainGame g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		
		pref = Gdx.app.getPreferences("currentscore");
		
		pref.putInteger("HP", 100);
		pref.putInteger("steps", 0);
		pref.flush();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Super Extreme Fire of Doom ", 0, 150);
        game.font.draw(game.batch, "Tap anywhere to DIE!", 100, 100);
        
        
        game.batch.end();
        
        if (Gdx.input.isTouched()) {
        	game.mapa = new GameMap(game);
            game.setScreen(game.mapa);
            dispose();
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
