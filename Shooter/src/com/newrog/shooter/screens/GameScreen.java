package com.newrog.shooter.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.newrog.shooter.ShooterGame;
import com.newrog.shooter.units.Airplane;
import com.newrog.shooter.units.Ammunition;
import com.newrog.shooter.units.Enemy;
import com.newrog.shooter.units.Entity;
import com.newrog.shooter.units.Player;
import com.newrog.shooter.units.Tank;

public class GameScreen implements Screen{

	public Stage stage;
	public ShooterGame game;
	public ShooterGame gamez;
	public Touchpad tp;
	public Touchpad tp2;
	public Player p;
	//private Camera camera;
	protected FPSLogger fps;
	private int sixty = 0;
	//public float resolutionScale = 1;
	public  ArrayList<Entity> entities;
	public OrthographicCamera camera;
	public GameScreen(ShooterGame game) {
		entities = new ArrayList<Entity>();
		this.game = game;
		gamez = game;
		stage = new Stage();
		fps = new FPSLogger();

		stage.setViewport(800, 480, false);
		System.out.println(stage.getWidth());
		camera = new OrthographicCamera(800, 480);
		camera.translate(400, 240);
		camera.update();
		//camera = stage.getCamera();
	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		p = new Player(game);
		p.setPosition(400, 240);
		entities.add(p);

		
		Skin stag = new Skin(game.theArt);
		TouchpadStyle tps = new TouchpadStyle();
		tps.background = stag.getDrawable("outer");
		tps.knob = stag.getDrawable("pad");
		tp = new Touchpad(0, tps);
		tp.setBounds(50, 50,200, 200);
		stage.addActor(tp);
		tp2 = new Touchpad(0, tps);
		
		
		tp2.setBounds(stage.getWidth()-250, 50,200, 200);
		stage.addActor(tp2);
		
		Skin aa = new Skin(Gdx.files.internal("packed.json"));
		Button buttonMulti;
		
		//buttonMulti = new TextButton("ASFD", tbss);
		buttonMulti = new Button(aa, "default");
		buttonMulti.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				p = new Player(gamez);
				entities.add(p);
				
			}
		});
	
		WidgetGroup w = new WidgetGroup();
		//buttonMulti.setWidth(window.getWidth()/2);
		//buttonMulti.setHeight(window.getHeight()/3);
		buttonMulti.setX(0);
		buttonMulti.setY(stage.getHeight()-buttonMulti.getHeight());
		//buttonMulti.addActor(l);
		stage.addActor(buttonMulti);
		
		
		
	}
	public ArrayList<Entity> removeList = new ArrayList<Entity>();
	SpriteBatch batch;
	@Override
	public void render(float delta)
	{
		
		update(delta);
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		//stage.act();
		
		
		batch.begin();
		for(int i = entities.size()-1; i >= 0; --i) {
			Entity e = entities.get(i);
			e.draw(batch);
			
			
			ArrayList<Entity> collider = collided(e);
			if(collider != null ){
				
				for(int z = 0; z < collider.size(); ++z) {
					if(e instanceof Enemy && collider.get(z) instanceof Ammunition) {
						--e.life;
						--collider.get(z).life;
					}
					if(e instanceof Enemy && collider.get(z) instanceof Player) {
						--e.life;
						//--collider.get(z).life;
					}
				}
			}
			
			if(e.life <= 0)
				removeList.add(e);
		}
		batch.end();
		
		//System.out.println(entities.size());
		
		if (sixty++ > 50 || Gdx.input.isKeyPressed(Input.Keys.B)) {
			entities.add(new Airplane(game));
			//stage.addActor(new Airplane(game));
			sixty = 0;
		}
		fps.log();
		camera.update();
		
		entities.removeAll(removeList);
		removeList.clear();
	}
	
	
	private ArrayList<Entity> collided(Entity entity) {
		ArrayList<Entity> collisions = null;
		for(int i = 0; i < entities.size(); i++) {
			if(entity == entities.get(i))
				continue;
			if(entity.rect.overlaps(entities.get(i).rect)){
				if(collisions == null)
					collisions = new ArrayList<Entity>();
				collisions.add(entities.get(i));
			}
		}
		return collisions;
	}


	private void update(float delta)
	{
		
		//for(int i = 0; i < entities.size(); ++i) {
		//	entities.get(i).draw(batch, 1);
		//}
		
		tp.toFront();
		tp2.toFront();
		if(tp.isTouched()) {
			p.translate(tp.getKnobPercentX()*150*delta, tp.getKnobPercentY()*150*delta);	
		}
		if(tp2.isTouched()) {
			float getR = p.getRotation();
			p.setRotation(-getR);
			p.setRotation(MathUtils.radiansToDegrees*MathUtils.atan2(tp2.getKnobPercentY(), tp2.getKnobPercentX()));
			p.shoot();
		}		
		
		if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
			entities.add(new Tank(game));
		}
		
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide()
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
		
	}

}
