package character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

public class Chara {

	public int animation_index;
	public int frame_index;
	public int center_x;
	public Character character;
	public int center_y;
	public Character_Atlas chara;
	float sprite_index;
	
	public Chara()
	{
		sprite_index = 0;
		animation_index = 0;
		frame_index = 0;
		center_x = 0;
		center_y = 0;
		
		chara = new Character_Atlas(this);
		
		Json json = new Json();
		character = json.fromJson(Character.class, Gdx.files.internal("popeye.json"));
		
		chara.AddAnimation(character.animation.get(0).name_anim_file);
	}
	
	public void Update()
	{
		sprite_index += Gdx.graphics.getDeltaTime();
		frame_index = (int)sprite_index;
		if (frame_index > chara.GetAnimationSize())
		{
			frame_index = 0;
			sprite_index = 0;
		}
	}
	
	public void Draw(SpriteBatch batch)
	{
		chara.Draw(batch);
	}

	public void Dispose()
	{
		chara.Dispose();
	}
}
