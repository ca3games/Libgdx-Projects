package character;

import com.badlogic.gdx.math.Rectangle;

public class Hitbox {

	public Rectangle rectangle;
	public enum Type
	{
		normal, crouch, block, low_block, nulo, air_attack, grab, air_grab, air, air_fall, 
		normal_attack, low_attack, overhead_attack
	}
	public Type type;
	
	public Hitbox()
	{
		rectangle = new Rectangle(0, 0, 100, 100);
		type = Type.normal;
	}
	
}
