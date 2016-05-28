package com.Engine;

import java.util.ArrayList;
import java.util.List;

public class Animations {

public List<Frame> frame;
public String name_of_animation, path_to_atlas;
public int index, last_frame, number_frames;
//"data/ATHENA/walking.atlas" path to atlas
	
	public Animations()
	{
		frame = new ArrayList<Frame>();
		frame.add(new Frame());
		name_of_animation = "null name";
		path_to_atlas = "null path";
		index = 0;
		last_frame = 0;
		number_frames = 0;
	}
	
}
