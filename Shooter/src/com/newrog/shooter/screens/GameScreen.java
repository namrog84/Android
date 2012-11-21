package com.newrog.shooter.screens;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;
import com.newrog.shooter.units.Airplane;
import com.newrog.shooter.units.Ammunition;
import com.newrog.shooter.units.Enemy;
import com.newrog.shooter.units.Entity;
import com.newrog.shooter.units.Explosion;
import com.newrog.shooter.units.Player;
import com.newrog.shooter.units.PowerUp;
import com.newrog.shooter.units.Tank;
import com.newrog.shooter.units.TankBullet;

public class GameScreen implements Screen{
	private Sound expSound;
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
	public  List<Entity> entities;
	public OrthographicCamera camera;
	public Array<Entity> specialEffects;
	public static Array<PowerUp> powerUps;
	
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
		oldCamera = new Vector3(camera.position); //default camera
		camera.update();
		//camera = stage.getCamera();
		specialEffects = new Array<Entity>();
		powerUps = new Array<PowerUp>();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		p = new Player(game);
		p.setPosition(400, 240);
		entities.add(p);
		expSound = Gdx.audio.newSound(Gdx.files.internal("sound_40.wav"));
		
		Skin stag = new Skin(Art.theArt);
		TouchpadStyle tps = new TouchpadStyle();
		tps.background = stag.getDrawable("outer");
		tps.knob = stag.getDrawable("pad");
		tp = new Touchpad(0, tps);
		tp.setBounds(20, 20,200, 200);
		stage.addActor(tp);
		tp2 = new Touchpad(0, tps);
		
		
		tp2.setBounds(stage.getWidth()-220, 20,200, 200);
		stage.addActor(tp2);
		
		Skin aa = new Skin(Gdx.files.internal("packed.json"));
		Button buttonMulti;
		Button changeWeaponButton;
		
		//buttonMulti = new TextButton("ASFD", tbss);
		buttonMulti = new Button(aa, "default");
		buttonMulti.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				p = new Player(gamez);
				p.setPosition(400, 240);
				entities.add(p);
				
			}
		});
	
		changeWeaponButton = new Button(aa, "default");
		changeWeaponButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				cycleWeapons();
			}
		});
		
		WidgetGroup w = new WidgetGroup();
		//buttonMulti.setWidth(window.getWidth()/2);
		//buttonMulti.setHeight(window.getHeight()/3);
		buttonMulti.setX(0);
		buttonMulti.setY(stage.getHeight()-buttonMulti.getHeight());
		changeWeaponButton.setX(0);
		changeWeaponButton.setY(stage.getHeight()-2*buttonMulti.getHeight());
		//buttonMulti.addActor(l);
		stage.addActor(buttonMulti);
		stage.addActor(changeWeaponButton);
		
		background = new Sprite(Art.background);
		//background.setPosition(0, 44);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/CONSOLA.TTF"));
		BitmapFont f = generator.generateFont(35);
		generator.dispose();
		lab = new Label("0", new LabelStyle(f, Color.WHITE));
		lab.setPosition(stage.getWidth()/2, stage.getHeight()-40);
		stage.addActor(lab);
		
	}
	Label lab;
	public ArrayList<Entity> removeList = new ArrayList<Entity>();
	SpriteBatch batch;
	public void cycleWeapons () {
		p.weaponToggle = !p.weaponToggle;
	}
	
	Sprite background;
	private float shakeIntensity = 5f;
	
	@Override
	public void render(float delta)
	{
		
		update(delta);
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//stage.act();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		//background.setScale(scaleX, scaleY);
		background.draw(batch);
		
		
		for(int i = entities.size()-1; i >= 0; --i) {
			Entity e = entities.get(i);
			e.draw(batch);
			
			
			ArrayList<Entity> collider = collided(e);
			if(collider != null ){

				for(int z = 0; z < collider.size(); ++z) {
					if(e instanceof Enemy && !(e instanceof TankBullet) && collider.get(z) instanceof Ammunition) {
						--e.life;
						--collider.get(z).life;
						expSound.play(game.sound);
						Explosion ex = new Explosion(game);
						ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10));
						entities.add(ex);
						ex = new Explosion(game);
						ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10));
						entities.add(ex);
						ex = new Explosion(game);
						ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10));
						entities.add(ex);
						expSound.play(game.sound);
						cameraShakeTicks+=10;
						if (MathUtils.random(0, 100) < 15) {
							new PowerUp(game, e.getX(), e.getY());
						}
						
						
					}
					if(e instanceof Enemy && collider.get(z) instanceof Player) {
						--e.life;
						//--collider.get(z).life;
					}
					if(e instanceof Player && collider.get(z) instanceof Enemy) {
						--e.life;
						Explosion ex = new Explosion(game);
						ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10));
						entities.add(ex);
						ex = new Explosion(game);
						ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10));
						entities.add(ex);
						cameraShakeTicks+=50;
						shakeIntensity = 15;
						expSound.play(game.sound);
						for(int iz = 0; iz < 20; iz++) {
							ex = new Explosion(game);
							ex.init(e.getX()+MathUtils.random(-10, 10), e.getY()+MathUtils.random(-10, 10), MathUtils.random(0,4));
							entities.add(ex);
						}
					}
				}
			}
			
			if (e.life <= 0) {
				removeList.add(e);
				if (e instanceof Airplane || e instanceof Tank) lab.setText("" + ++killCount);

			}
		}
		for(int i =0 ;i < powerUps.size;++i) {
			powerUps.get(i).draw(batch);
			if(powerUps.get(i).rect.overlaps(p.rect)) {
				if(powerUps.get(i).type == PowerUp.PType.SPREAD)
					p.ammo += 200;
				if(powerUps.get(i).type == PowerUp.PType.MISSILE)
					p.weaponToggle = !p.weaponToggle;
				if(powerUps.get(i).type == PowerUp.PType.BOMB) {
					for(int j = 0 ; j <entities.size();++j) {
						if(entities.get(j) instanceof Enemy) {
							entities.get(j).life = 0;
						}
						
					}
					shakeIntensity = 50;
					cameraShakeTicks+=10;
				}
				powerUps.removeIndex(i);
			}
		}
		batch.end();
		stage.draw();
		//System.out.println(entities.size());
		
		if (sixty++ > 30 || Gdx.input.isKeyPressed(Input.Keys.B)) {
			if(MathUtils.randomBoolean())
				entities.add(new Airplane(game));
			else
				entities.add(new Tank(game));
			//stage.addActor(new Airplane(game));
			sixty = 0;
		}
		fps.log();
		
		if(cameraShakeTicks>0) {
			camera.translate(-offsetCamera.x, -offsetCamera.y);
			offsetCamera.x = MathUtils.random(0,shakeIntensity );
			offsetCamera.y = MathUtils.random(0,shakeIntensity);
			camera.translate(offsetCamera.x, offsetCamera.y);
			cameraShakeTicks--;
		}else {
			shakeIntensity = 3;
			camera.position.set(oldCamera);
			//oldCamera = camera.position;
		}

		//stage.getCamera().translate(10, 0, 0);
//		camera.translate(10, 0, 0);
		camera.update();
		
		entities.removeAll(removeList);
		removeList.clear();
		Collections.sort(entities, new customComparator());
		System.out.println("Entities: " + entities.size());
	}
	Vector3 offsetCamera = new Vector3(0,0,0);
	Vector3 oldCamera;
	float cameraShakeTicks = 0;
	int killCount = 0;
	
	
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
		if(Gdx.input.isKeyPressed(Input.Keys.X)) {
			Explosion e = new Explosion(game);
			e.init(MathUtils.random(000, 800), MathUtils.random(000, 400));
			entities.add(e);
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
	public class customComparator implements Comparator<Entity>{
		@Override
		public int compare(Entity e1, Entity e2) {
			if(e1.zIndex < e2.zIndex)
				return 1;
			if(e1.zIndex > e2.zIndex)
				return -1;
			else
				return 0;
		}
	}

}
