package com.ca3games.bouncychicken;

import game.Chicken;
import game.Inputprocessor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	PerspectiveCamera camera;
	ModelBatch modelbatch;
	Environment enviroment;
	
	AssetManager assets;
	public Array<Chicken> chickens = new Array<Chicken>();
	private boolean loading;
	TextureRegion[] ground = new TextureRegion[2];
	Texture t_ground, t_grass;
	Decal decal_ground, decal_grass;
	DecalBatch decalbatch;
	public Rectangle mouse_pos;
	ShapeRenderer shape;
	float chicken_speed;

	
	@Override
	public void create () {
		chicken_speed = 0.5f;
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, 40f, 100f);
		camera.lookAt(0,0,0);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();
		
		mouse_pos = new Rectangle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 16, 16);
		shape.setColor(Color.RED);
		
		CameraGroupStrategy camerastrategy = new CameraGroupStrategy(camera);
		decalbatch = new DecalBatch(camerastrategy);
		t_ground = new Texture(Gdx.files.internal("ground.png"));
		t_grass = new Texture(Gdx.files.internal("grass.png"));
		ground[0] = new TextureRegion(t_ground, 0, 0, 32, 32);
		ground[1] = new TextureRegion(t_grass, 0, 0, 64, 64);
		decalbatch.setGroupStrategy(camerastrategy);
		
		decal_ground = Decal.newDecal(ground[0], true);
		decal_ground.setPosition(-4, 0, -4);
		decal_ground.setRotationX(140);
		decal_ground.setScale(4);
		
		decal_grass = Decal.newDecal(ground[1], true);
		decal_grass.setPosition(-8, 0, -4.5f);
		decal_grass.setRotationX(140);
		decal_grass.setScale(4.5f);
		
		
		modelbatch = new ModelBatch();
		enviroment = new Environment();
		enviroment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		enviroment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		assets = new AssetManager();
		assets.load("models/broiler.g3db", Model.class);
		
		loading = true;
		
		Inputprocessor input = new Inputprocessor(this);
		Gdx.input.setInputProcessor(input);
		
	}
	
	private void doneLoading()
	{
		Model model = assets.get("models/broiler.g3db", Model.class);
		chickens.add(new Chicken(model));
		Quaternion rot = new Quaternion();
		rot.set(new Vector3(0,1,0), 60);
		chickens.get(0).transform.set(new Vector3(0,0,0), rot);
		chickens.get(0).transform.scale(1.5f, 1.5f, 1.5f);
		loading = false;
		chickens.get(0).rectangle = new Rectangle(0,0,16,16);
	}

	@Override
	public void render () {
		
		if (loading && assets.update())
		{
			doneLoading();
		}
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        
        if (chickens.size > 0)
        {
        	chickens.get(0).Update(camera);
        	if (chickens.get(0).rectangle.x < mouse_pos.x)
        	{
        		chickens.get(0).transform.trn(chicken_speed, 0, 0);
        	}
        	else
        	{
        		chickens.get(0).transform.trn(-chicken_speed, 0, 0);
        	}
        }
        
        decalbatch.add(decal_grass);
        decalbatch.add(decal_ground);
        decalbatch.flush();
		
		modelbatch.begin(camera);
		modelbatch.render(chickens, enviroment);
		modelbatch.end();
		
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.box(mouse_pos.x, mouse_pos.y, 0, mouse_pos.width, mouse_pos.height, 1);
		shape.end();
		
		
		/*
		shape.setColor(Color.BLUE);
		if (chickens.size > 0)
		{
			chickens.get(0).DrawRectangle(shape, camera);
		}
		shape.end();
		*/

		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		modelbatch.dispose();
		chickens.clear();
		assets.dispose();
		decalbatch.dispose();

	}
}
