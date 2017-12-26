package character;

import java.util.ArrayList;
import java.util.List;

public class Animation_Hit {

	public List<Frame> frame;
	public String name_animation;
	public String name_anim_file;
	
	public Animation_Hit()
	{
		
	}
	
	public Animation_Hit(boolean truth)
	{
		frame = new ArrayList<Frame>();
		frame.add(new Frame());
		name_animation = "new empty animation";
	}
	
	public Animation_Hit(String name)
	{
		frame = new ArrayList<Frame>();
		frame.add(new Frame());
		name_animation = name;
	}
}
