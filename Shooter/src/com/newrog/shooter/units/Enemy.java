package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.newrog.shooter.ShooterGame;

public class Enemy extends Entity {

	protected int health = 1;
	protected Vector2 velo;

	protected float turn = 0;

	protected float timer1;
	protected float speed;
	protected float speedModifer = 1;
	protected float direction;

	protected Sprite shadow;
	private Sound sound;
	private Sound sound2;

	public Enemy(ShooterGame game) {
		sound = Gdx.audio.newSound(Gdx.files.internal("sound_22.wav"));
		sound2 = Gdx.audio.newSound(Gdx.files.internal("sound_40.wav"));
		this.game = game;
		float start = MathUtils.random(0, 1);
		if (start > 0.75f)
		{
			setPosition(game.gameScreen.stage.getWidth() + 10, MathUtils.random(0, game.gameScreen.stage.getHeight()));
		} else if (start > 0.5f)
		{
			setPosition(-10, MathUtils.random(0, game.gameScreen.stage.getHeight()));
		} else if (start > 0.25f)
		{
			setPosition(MathUtils.random(0, game.gameScreen.stage.getWidth()), 0);
		} else
		{
			setPosition(MathUtils.random(0, game.gameScreen.stage.getWidth()),
						MathUtils.random(0, game.gameScreen.stage.getHeight()));
		}

		speed = 900;

		timer1 = 80 + MathUtils.random(0, 60);
		velo = new Vector2(1, 1);
		turn = 0;
		moveTo(MathUtils.random(0, 360), 10);

	}

	@Override
	protected void update() {

	}

	@Override
	public void render(SpriteBatch batch, float parentalpha) {
		if (shadow != null)
		{
			shadow.setPosition(getX() - sprite.getWidth() / 2 + 25, getY() - sprite.getHeight() / 2 - 25);
			shadow.setRotation(getRotation());
			shadow.setColor(new Color(0.1f, 0.1f, 0.1f, 0.2f));
			shadow.draw(batch);
		}

		if (sprite != null)
		{
			sprite.setPosition(getX() - sprite.getWidth() / 2, getY() - sprite.getHeight() / 2);
			sprite.setRotation(getRotation());
			sprite.draw(batch);
		}

	}

	protected void moveTo(float newDirection, float strengthMove) {
		float x1 = MathUtils.cosDeg(direction) * speed;
		float y1 = MathUtils.sinDeg(direction) * speed;
		float x2 = MathUtils.cosDeg(newDirection) * strengthMove;
		float y2 = MathUtils.sinDeg(newDirection) * strengthMove;
		float xc = x1 + x2;
		float yc = y1 + y2;

		speed = (float) Math.sqrt(xc * xc + yc + yc);
		direction = MathUtils.atan2(yc, xc) * MathUtils.radiansToDegrees;
	}

	protected void calculateSpeed() {
		float a = (float) Math.sqrt(velo.x * velo.x + velo.y * velo.y);
		if (a > 0)
		{
			speed = a;
		}
		computeVelocity();
	}

	protected void computeVelocity() {
		velo.x = speed * MathUtils.cosDeg(direction);
		velo.y = speed * MathUtils.sinDeg(direction);
	}

	protected float calculateAngle(float x1, float y1, float x2, float y2) {
		return MathUtils.atan2(y1 - y2, x1 - x2) * MathUtils.radiansToDegrees;
	}

	protected void handleCollisions() {

		Actor a = game.gameScreen.stage.hit(getX(), getY(), true);
		if (a != null && a instanceof Player)
		{
			sound.play();
			this.setVisible(false);
			this.clearActions();
			this.remove();
		}
		if (a != null && a instanceof Bullet)
		{
			sound2.play();
			this.setVisible(false);
			this.clearActions();
			this.remove();
			a.setVisible(false);
			a.clearActions();
			a.remove();

		}
	}

}
