package character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gui.Chara;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	public void AddAnimation()
	{
		TextureAtlas tmp = new TextureAtlas(Gdx.files.internal("default.atlas"));
		texture_atlases.add(tmp);
		animations.add(new Animation(1/15f, texture_atlases.get(texture_atlases.size()-1).findRegions("ani")));
	}
	
	public void AddAnimation(String name)
	{
		Preferences pref = Gdx.app.getPreferences("folder");
		String folder = pref.getString("filepath");
		
		try
		{
		if (Gdx.files.absolute(folder+name).exists())
		{
		TextureAtlas tmp = new TextureAtlas(Gdx.files.absolute(folder+name));
		texture_atlases.add(tmp);
		animations.add(chara.animation_index, new Animation(1/15f, texture_atlases.get(texture_atlases.size()-1).findRegions("ani")));
		}
		else
		{
			JOptionPane.showMessageDialog(null,"File " + folder+name + " not found");
		}
		}
		catch (NullPointerException e)
		{
			
		}
	}
	
	public void Draw(SpriteBatch batch)
	{
		try{
		if (animations.size() > 0)
		{
			batch.draw(animations.get(chara.animation_index).getKeyFrame(chara.frame_index, true), 
					chara.center_x + chara.character.animation.get(chara.animation_index).frame.get(chara.frame_index).offset_x
					, chara.center_y + chara.character.animation.get(chara.animation_index).frame.get(chara.frame_index).offset_y);
		}
		}
		catch (IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void Dispose()
	{
		for(TextureAtlas tex : texture_atlases)
		{
			tex.dispose();
		}
	}
	
}
