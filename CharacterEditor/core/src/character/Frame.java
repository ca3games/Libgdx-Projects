package character;

import java.util.ArrayList;
import java.util.List;

public class Frame {
	
	public List <Hitbox> hitbox;
	public float offset_x, offset_y;
	public int cancel_level;
	public float speed_x, speed_y;
	
	public Frame()
	{
		offset_x = 0;
		offset_y = 0;
		hitbox = new ArrayList<Hitbox>();
		hitbox.add(new Hitbox());
	}

}
