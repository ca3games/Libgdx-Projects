package game;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Chicken extends ModelInstance {

	public Chicken(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}
	public Rectangle rectangle;
	
	public void Update(PerspectiveCamera camera)
	{
		Vector3 pos = transform.getTranslation(new Vector3());
		camera.project(pos);
		rectangle.x = pos.x;
		rectangle.y = pos.y;
	}
	
	public void DrawRectangle(ShapeRenderer shape, PerspectiveCamera camera)
	{
		shape.box(rectangle.x, rectangle.y, 0, rectangle.width, rectangle.height, 1);
	}
	
	public Vector3 GetScreenPos (PerspectiveCamera camera)
	{
		return camera.project(transform.getTranslation(new Vector3()));
	}
	
}
