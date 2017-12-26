package gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class GUI {

	public Stage stage;
	public Chara character;
	
	public GUI(Chara c)
	{
		character = c;
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		VisUI.load();
		VisTextButton NextFrame =  new VisTextButton(">>");
		NextFrame.setWidth(50);
		NextFrame.setHeight(30);
		NextFrame.setPosition(width-50, 0);
		NextFrame.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.NextFrame();
				return true;
			}
		});
		
		VisTextButton PrevFrame = new VisTextButton("<<");
		PrevFrame.setWidth(50);
		PrevFrame.setHeight(30);
		PrevFrame.setPosition(width-100, 0);
		PrevFrame.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.PrevFrame();
				return true;
			}
		});
		
		final VisTextArea SetWidth = new VisTextArea("Set width");
		SetWidth.setWidth(100);
		SetWidth.setHeight(30);
		SetWidth.setPosition(100, 30);
		
		final VisTextArea SetHeight = new VisTextArea("Set height");
		SetHeight.setHeight(30);
		SetHeight.setWidth(100);
		SetHeight.setPosition(200, 30);
		
		VisTextButton SetSize = new VisTextButton("Set Size");
		SetSize.setWidth(100);
		SetSize.setHeight(30);
		SetSize.setPosition(0, 30);
		SetSize.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				try
				{
					character.SetSize(Integer.parseInt(SetWidth.getText()), Integer.parseInt(SetHeight.getText()));
				}
				catch (NumberFormatException e)
				{
					character.SetSize(200, 200);
				}
                return true;
        }
		});
		
		final VisTextArea SetPosX = new VisTextArea("Set X");
		SetPosX.setWidth(100);
		SetPosX.setHeight(30);
		SetPosX.setPosition(100, 60);
		
		final VisTextArea SetPosY = new VisTextArea("Set Y");
		SetPosY.setWidth(100);
		SetPosY.setHeight(30);
		SetPosY.setPosition(200, 60);
		
		VisTextButton SetPos = new VisTextButton("Set Position");
		SetPos.setWidth(100);
		SetPos.setHeight(30);
		SetPos.setPosition(0, 60);
		SetPos.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				try
				{
					character.SetPosition(Integer.parseInt(SetPosX.getText()), Integer.parseInt(SetPosY.getText()));
				}
				catch (NumberFormatException e)
				{
					character.SetPosition(200, 200);
				}
                return true;
        }
		});
		
		VisTextButton NewLayer = new VisTextButton("+");
		NewLayer.setWidth(50);
		NewLayer.setHeight(30);
		NewLayer.setPosition(width-50, 30);
		NewLayer.addListener(new InputListener()
				{
					@Override
					 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						character.AddLayer();
	                    return true;
	            }
			
				});
		
		VisTextButton DeleteLayer = new VisTextButton("-");
		DeleteLayer.setWidth(50);
		DeleteLayer.setHeight(30);
		DeleteLayer.setPosition(width-100, 30);
		DeleteLayer.addListener(new InputListener()
			{
				@Override
				 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					character.DeleteLayer();
	                return true;
	        }
		
			});
		
		VisTextButton LayerDown = new VisTextButton("Layer Down");
		LayerDown.setWidth(100);
		LayerDown.setHeight(30);
		LayerDown.setPosition(width-100, 70);
		LayerDown.addListener(new InputListener()
			{
				@Override
				 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					character.LayerDown();
	                return true;
	        }
			});
		
		VisTextButton LayerUp = new VisTextButton("Layer Up");
		LayerUp.setWidth(100);
		LayerUp.setHeight(30);
		LayerUp.setPosition(width-100, 100);
		LayerUp.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.LayerUp();
                return true;
        }
		});
		
		final VisTextButton EditSprite = new VisTextButton("Edit Sprite");
		EditSprite.setWidth(100);
		EditSprite.setHeight(30);
		EditSprite.setPosition(width-100, 140);
		EditSprite.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				character.SwitchSprite();
				if (character.EditSprite)
				{
					EditSprite.setText("Edit Hitbox");
				}
				else
				{
					EditSprite.setText("Edit Sprite");
				}
                return true;
        }
		});
		
		VisTextButton NextHitbox = new VisTextButton(">>");
		NextHitbox.setHeight(30);
		NextHitbox.setWidth(50);
		NextHitbox.setPosition(width-50, height/2);
		NextHitbox.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.ChangeType();
                return true;
        }
		});
		
		
		VisTextButton ResetHitbox = new VisTextButton("Reset");
		ResetHitbox.setWidth(50);
		ResetHitbox.setHeight(30);
		ResetHitbox.setPosition(width-100, height/2);
		ResetHitbox.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.ResetType();
                return true;
        }
		});
		
		VisTextButton Play = new VisTextButton("Play");
		Play.setWidth(100);
		Play.setHeight(30);
		Play.setPosition(width-100, height-30);
		
		final VisTextArea FilePathSave = new VisTextArea("Folder path");
		FilePathSave.setWidth(200);
		FilePathSave.setHeight(30);
		FilePathSave.setPosition(width/2-150, 30);
		Preferences pref = Gdx.app.getPreferences("folder");
		FilePathSave.setText(pref.getString("filepath"));
		
		final VisTextArea FileNameSave = new VisTextArea("Json name");
		FileNameSave.setWidth(100);
		FileNameSave.setHeight(30);
		FileNameSave.setPosition(width/2+50, 30);
		
		VisTextButton SaveAnimation = new VisTextButton("Save Json");
		SaveAnimation.setWidth(150);
		SaveAnimation.setHeight(30);
		SaveAnimation.setPosition(width/2-150, 0);
		SaveAnimation.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Preferences pref = Gdx.app.getPreferences("folder");
				pref.putString("filepath", FilePathSave.getText().toString());
				pref.flush();
				character.SaveFile(FilePathSave.getText().toString(), FileNameSave.getText().toString());
                return true;
        }
		});
		
		VisTextButton LoadAnimation = new VisTextButton("Load Json");
		LoadAnimation.setWidth(100);
		LoadAnimation.setHeight(30);
		LoadAnimation.setPosition(width/2, 0);
		LoadAnimation.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Preferences pref = Gdx.app.getPreferences("folder");
				pref.putString("filepath", FilePathSave.getText().toString());
				pref.flush();
				character.LoadFile(FilePathSave.getText().toString(), FileNameSave.getText().toString());
                return true;
        }
		});
		
		VisTextButton MoreAnim = new VisTextButton("+Anim");
		MoreAnim.setWidth(100);
		MoreAnim.setHeight(30);
		MoreAnim.setPosition(width-100, 180);
		MoreAnim.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.AddAnimation();
                return true;
        }
		});
		
		VisTextButton LessAnim = new VisTextButton("-Anim");
		LessAnim.setWidth(100);
		LessAnim.setHeight(30);
		LessAnim.setPosition(width-100, 210);
		LessAnim.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.DeleteAnimation();
                return true;
        }
		});
		
		VisTextButton UpAnim = new VisTextButton("A>>");
		UpAnim.setWidth(50);
		UpAnim.setHeight(30);
		UpAnim.setPosition(width-50, 240);
		UpAnim.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.UpAnim();;
                return true;
        }
		});
		
		VisTextButton DownAnim = new VisTextButton("A<<");
		DownAnim.setWidth(50);
		DownAnim.setHeight(30);
		DownAnim.setPosition(width-100, 240);
		DownAnim.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.DownAnim();
                return true;
        }
		});
		
		final VisTextArea NameAnimText = new VisTextArea("Name");
		NameAnimText.setWidth(150);
		NameAnimText.setHeight(30);
		NameAnimText.setPosition(100, 90);
		
		VisTextButton NameAnimButton = new VisTextButton("Change Name");
		NameAnimButton.setWidth(100);
		NameAnimButton.setHeight(30);
		NameAnimButton.setPosition(0, 90);
		NameAnimButton.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.ChangeName(NameAnimText.getText().toString());
                return true;
        }
		});
		

		final VisTextArea AtlasName = new VisTextArea("Atlas Name");
		AtlasName.setWidth(150);
		AtlasName.setHeight(30);
		AtlasName.setPosition(100, 0);
		
		VisTextButton LoadAtlas = new VisTextButton("Load Atlas");
		LoadAtlas.setWidth(100);
		LoadAtlas.setHeight(30);
		LoadAtlas.setPosition(0, 0);
		LoadAtlas.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Preferences pref = Gdx.app.getPreferences("folder");
				pref.putString("filepath", FilePathSave.getText().toString());
				pref.flush();
				character.AddAnimation(AtlasName.getText());
                return true;
        }
		});
		
		final VisTextArea Speed_X = new VisTextArea("0");
		Speed_X.setWidth(50);
		Speed_X.setHeight(30);
		Speed_X.setPosition(0, Gdx.graphics.getHeight()/2-30);
		
		final VisTextArea Speed_Y = new VisTextArea("0");
		Speed_Y.setWidth(50);
		Speed_Y.setHeight(30);
		Speed_Y.setPosition(50, Gdx.graphics.getHeight()/2-30);
		
		VisTextButton SetSpeed = new VisTextButton("Set speed");
		SetSpeed.setWidth(100);
		SetSpeed.setHeight(30);
		SetSpeed.setPosition(0, Gdx.graphics.getHeight()/2);
		SetSpeed.addListener(new InputListener()
		{
			@Override
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				character.SetSpeed(Integer.parseInt(Speed_X.getText()), Integer.parseInt(Speed_Y.getText()));
				return true;
        }
		});
		
		
		stage = new Stage();
		stage.addActor(Speed_Y);
		stage.addActor(SetSpeed);
		stage.addActor(Speed_X);
		stage.addActor(NameAnimButton);
		stage.addActor(NameAnimText);
		stage.addActor(UpAnim);
		stage.addActor(DownAnim);
		stage.addActor(MoreAnim);
		stage.addActor(LessAnim);
		stage.addActor(LoadAnimation);
		stage.addActor(SaveAnimation);
		stage.addActor(FileNameSave);
		stage.addActor(FilePathSave);
		stage.addActor(NextFrame);
		stage.addActor(PrevFrame);
		stage.addActor(AtlasName);
		stage.addActor(LoadAtlas);
		stage.addActor(SetWidth);
		stage.addActor(SetHeight);
		stage.addActor(SetSize);
		stage.addActor(SetPosX);
		stage.addActor(SetPosY);
		stage.addActor(SetPos);
		stage.addActor(DeleteLayer);
		stage.addActor(NewLayer);
		stage.addActor(LayerUp);
		stage.addActor(LayerDown);
		stage.addActor(EditSprite);
		stage.addActor(ResetHitbox);
		stage.addActor(NextHitbox);
		stage.addActor(Play);
	}
	
	public void Draw()
	{
		stage.draw();
	}
	
	public void Dispose()
	{
		VisUI.dispose();
	}
	
}
