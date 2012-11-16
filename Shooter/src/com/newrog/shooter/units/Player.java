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

	private Sound sound;
	private int shootDelay = 4;

	public Player(ShooterGame game) {
		this.game = game;

		TextureRegion region = game.theArt.findRegion("ship1");
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		shadow = new Sprite(region);

		shadow.setPosition(-sprite.getWidth() / 3, -sprite.getHeight() / 3);

		
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());

		prop = new Prop(game);
		sound = Gdx.audio.newSound(Gdx.files.internal("sound_33.wav"));

	}

	//messy approach to prop, should I combine it with the helicopter in the form of a sprite?
	
	public Prop prop;
	private int shootTimer = 0;

	@Override
	public void render(SpriteBatch batch, float parentAlpha) {
		this.toFront();

		//offset for shadow
		shadow.setPosition(getX() - 25, getY() - 35);
		shadow.setRotation(getRotation());
		shadow.setColor(new Color(0.1f, 0.1f, 0.1f, 0.2f));
		shadow.draw(batch);

		sprite.setPosition(getX() - getWidth() / 2, getY() - getHeight() / 2);
		sprite.setRotation(getRotation());
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
		if(Gdx.input.isTouched() && (!game.gameScreen.tp.isTouched() && !game.gameScreen.tp2.isTouched())  ) {
			Vector2 v2 = game.gameScreen.stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
			System.out.println(v2.y + " " + (-Gdx.input.getY()+Gdx.graphics.getHeight()));
			this.setRotation(MathUtils.radiansToDegrees*MathUtils.atan2(-getY()+v2.y, -getX()+v2.x));
			this.shoot();
		}
	}

	boolean blah = false;

	public void shoot() {
		if (shootTimer > shootDelay)
		{
			game.gameScreen.stage.addActor(new Bullet(game, getRotation(), getX(), getY()));
			shootTimer = 0;
			sound.play(1.0f);
		}
	}

	public void dispose() {
		texture.dispose();
	}

	@Override
	protected void update() {

	}

}
