package input;

import screens.GameMap;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class DungeonInput implements GestureListener {

	public GameMap map;
	
	public DungeonInput(GameMap m)
	{
		map = m;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		int directionx = 5;
		int directiony = 5;
		
		if (velocityX < -100 && velocityX < velocityY) { directionx = 4; directiony = 5; }
		if (velocityX > 100 && velocityX > velocityY) { directionx = 6;  directiony = 5;}
		if (velocityY < -100 && velocityY < velocityX) { directiony = 2; directionx = 5;}
		if (velocityY > 100 && velocityY > velocityX) { directiony = 8;  directionx = 5;}
		
		map.MovePlayer(directionx, directiony);		
		
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
