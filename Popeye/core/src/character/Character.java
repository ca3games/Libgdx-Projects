package character;

import java.util.ArrayList;
import java.util.List;

public class Character {

	public List<Animation_Hit> animation;
	
	public Character()
	{
		animation = new ArrayList<Animation_Hit>();
		animation.add(new Animation_Hit("nullo"));
	}
}
