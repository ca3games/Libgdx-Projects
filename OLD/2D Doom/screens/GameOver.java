package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ca3games.superextremefireofdoom.MainGame;

public class GameOver implements Screen {

	OrthographicCamera camera;
	MainGame game;
	Preferences pref;
	int steps;
	
	public GameOver(MainGame g)
	{
		game = g;
	}
	
	@Override
	public void show() {
		pref = Gdx.app.getPreferences("currentscore");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		
		steps = pref.getInteger("steps");
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 1, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);

		game.font.setColor(Color.WHITE);
        game.batch.begin();
        game.font.draw(game.batch, "GAME OVER FAGGOT ", 0, 150);
        game.font.draw(game.batch, "Tap anywhere to go title", 100, 100);
        game.font.draw(game.batch, "Steps: " + Integer.toString(steps), 20, 20);
        game.batch.end();

        if (Gdx.input.isTouched()) {
        	game.setScreen(new TitleScreen(game));
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
