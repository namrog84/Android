package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.ShooterGame;

public class Player extends Entity {
	private Sprite shadow;

	private Sound bulletSound;
	private Sound missileSound;
	private int shootDelay = 6;

	public Player(ShooterGame game) {
		this.game = game;
		//this.setZIndex(2500);
		TextureRegion region = game.theArt.findRegion("ship1");
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		shadow = new Sprite(region);

		shadow.setPosition(-sprite.getWidth() / 3, -sprite.getHeight() / 3);

		
		setHeight(sprite.getHeight()/2);
		setWidth(sprite.getWidth()/4);
		
		prop = new Prop(game);
		bulletSound = Gdx.audio.newSound(Gdx.files.internal("sound_33.wav"));
		missileSound = Gdx.audio.newSound(Gdx.files.internal("SndMissile_.wav"));

	}

	//messy approach to prop, should I combine it with the helicopter in the form of a sprite?
	
	

	public Prop prop;
	private int shootTimer = 0;

	@Override
	public void render(SpriteBatch batch) {
		//System.out.println("SDF");
		//this.toFront();

		//offset for shadow
		shadow.setPosition(getX() -10, getY() - 30);
		shadow.setRotation(getRotation());
		shadow.setColor(new Color(0.1f, 0.1f, 0.1f, 0.2f));
		shadow.draw(batch);
		
		
		sprite.setRotation(getRotation());
		//sprite.setPosition(getX(), getY());
		sprite.setPosition(getX() + getWidth()  / 2 - sprite.getWidth()  / 2,
					       getY() + getHeight() / 2 - sprite.getHeight() / 2);
		sprite.draw(batch);
		prop.render(batch, this);

		++shootTimer;

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			this.shoot();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{
			this.translate(-300 * delta, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{
			this.translate(300 * delta, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			this.translate(0, 300 * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S))
		{
			this.translate(0, -300 * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			this.rotate(400 * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			this.rotate(-400 * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.V))
		{
			weaponToggle = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.G))
		{
			weaponToggle = false;
		}
		
		if(Gdx.input.isTouched() && (!game.gameScreen.tp.isTouched() && !game.gameScreen.tp2.isTouched())  ) {
			Vector2 v2 = game.gameScreen.stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
			//System.out.println(v2.x + " " + getX());//-Gdx.input.getY()+Gdx.graphics.getHeight()));
			this.setRotation(MathUtils.radiansToDegrees*MathUtils.atan2(-getCenterY()+v2.y, -getCenterX()+v2.x));
			this.shoot();
		}
	}
	boolean weaponToggle = true;
	boolean blah = false;

	public void shoot() {
		if (shootTimer > shootDelay)
		{
			if(weaponToggle) {
				game.gameScreen.entities.add(
						new Bullet(game, getRotation(), 
									getCenterX() + 40 * MathUtils.cosDeg(getRotation()+15), 
									getCenterY() + 40 * MathUtils.sinDeg(getRotation()+15)
									)
						);
				game.gameScreen.entities.add(
						new Bullet(game, getRotation(), 
									getCenterX() + 40 * MathUtils.cosDeg(getRotation()-15), 
									getCenterY() + 40 * MathUtils.sinDeg(getRotation()-15)
									)
						);
				bulletSound.play(game.sound);
			}else {
				game.gameScreen.entities.add(
						new Missile(game, getRotation(), 
									getCenterX() + 40 * MathUtils.cosDeg(getRotation()+15), 
									getCenterY() + 40 * MathUtils.sinDeg(getRotation()+15)
									)
						);
				game.gameScreen.entities.add(
						new Missile(game, getRotation(), 
									getCenterX() + 40 * MathUtils.cosDeg(getRotation()-15), 
									getCenterY() + 40 * MathUtils.sinDeg(getRotation()-15)
									)
						);
				missileSound.play(game.sound);
			}
			
			
			shootTimer = 0;
		}
	}

	public void dispose() {
		texture.dispose();
	}

	@Override
	protected void update() {

	}

}
