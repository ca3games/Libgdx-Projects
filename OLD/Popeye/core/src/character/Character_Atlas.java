package character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class Character_Atlas {

	public List<TextureAtlas> texture_atlases;
	public List<Animation> animations;
	public Chara chara;
	
	public Character_Atlas(Chara c)
	{
		texture_atlases = new ArrayList<TextureAtlas>();
		animations = new ArrayList<Animation>();
		chara = c;
	}
	
	public int GetAnimationSize()
	{
		try
		{
		if (animations.size() > 0)
		{
			return animations.get(chara.animation_index).getKeyFrames().length;
		}
		}
		catch (IndexOutOfBoundsException e)
		{
			return 0;
		}
		return 0;
	}
	
	public void AddAnimation(String name)
	{
		TextureAtlas tmp = new TextureAtlas(Gdx.files.internal(name));
		texture_atlases.add(tmp);
		animations.add(chara.animation_index, new Animation(1/15f, texture_atlases.get(texture_atlases.size()-1).findRegions("ani")));
	}
	
	public void Draw(SpriteBatch batch)
	{
		
		batch.draw(animations.get(chara.animation_index).getKeyFrame(chara.frame_index, true), 100, 140);
		
	}
	
	public void Dispose()
	{
		for(TextureAtlas tex : texture_atlases)
		{
			tex.dispose();
		}
	}
	
}
