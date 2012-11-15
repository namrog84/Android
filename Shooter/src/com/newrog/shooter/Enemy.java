package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Entity {

	private int health;
	private Vector2 velo;
	private float turn = 0;
	
	private float timer1;
	private float speed;
	
	private float direction;

	private Sprite shadow;
	public Enemy(ShooterGame game)
	{
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
			setPosition(MathUtils.random(0, game.gameScreen.stage.getWidth()), MathUtils.random(0, game.gameScreen.stage.getHeight()));
		}

		speed = 375;
		
		
		texture = new Texture(Gdx.files.internal("Airplane.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region = new TextureRegion(texture, 0, 0, 40, 40);
		
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		shadow = new Sprite(region);
		shadow.setOrigin(sprite.getWidth() / 3, sprite.getHeight() / 3);
		
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
		
		timer1 = 80 + MathUtils.random(0, 60);
		velo = new Vector2(1, 1);
		turn = 0;
		moveTo(MathUtils.random(0, 360), 10);
	}

	public void render(SpriteBatch batch, float parentalpha)
	{
		shadow.setRotation(getRotation());
		shadow.setColor(new Color(0.1f,0.1f,0.1f, 0.2f));
		shadow.setPosition(getX() - sprite.getWidth() / 3, getY() - sprite.getWidth() / 3);
		shadow.draw(batch);
		
		sprite.setRotation(getRotation());
		sprite.setPosition(getX() - sprite.getWidth() / 2, getY() - sprite.getWidth() / 2);
		sprite.draw(batch);
		
		
	}

	protected void update()
	{
		handleCollisions();
		float delta = Gdx.graphics.getDeltaTime();
		
		timer1 -= delta * 50;

		if (timer1 < 0)
		{
			if (turn == 0)
			{
				if (MathUtils.randomBoolean())
				{
					turn = -1;
				} else
				{
					turn = 1;
				}
				timer1 = 45 + MathUtils.random(0, 45);
			} else
			{
				turn = 0;
				timer1 = 30 + MathUtils.random(00, 30);
			}
		}
		if (turn == 0)
		{
			float tempAng = calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY());
			moveTo(tempAng, 5 * delta);

		} else if (getX() < 20 || getX() > 800 - 20 || getY() < 20 || getY() > 800 - 20)
		{
			moveTo(calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY()), delta);
		} else
		{

			direction = direction + turn * delta * 100;
		}

		setDirection(direction);
		setRotation(direction);
		translate(velo.x, velo.y);
	}

	public void setSpeed(float sped)
	{
		speed = sped;
		computeVelocity();
	}

	private void moveTo(float param1, float param2)
	{
		float x1 = MathUtils.cosDeg(direction) * speed;
		float y1 = MathUtils.sinDeg(direction) * speed;
		float x2 = MathUtils.cosDeg(param1) * param2;
		float y2 = MathUtils.sinDeg(param1) * param2;
		float xc = x1 + x2;
		float yc = y1 + y2;

		speed = (float) Math.sqrt(xc * xc + yc * yc);
		direction = MathUtils.atan2(yc, xc) * MathUtils.radiansToDegrees;
	}

	public void setDirection(float number)
	{
		float a = (float) Math.sqrt(velo.x * velo.x + velo.y * velo.y);
		if (a > 0)
		{
			speed = a;
		}

		direction = number;
		computeVelocity();
	}

	private void computeVelocity()
	{
		velo.x = speed * MathUtils.cosDeg(direction);
		velo.y = speed * MathUtils.sinDeg(direction);
	}

	private float calculateAngle(float x1, float y1, float x2, float y2)
	{
		return MathUtils.atan2(y1 - y2, x1 - x2) * MathUtils.radiansToDegrees;

	}

	private void handleCollisions() {

		Actor a = game.gameScreen.stage.hit(getX(), getY(), false);
		if (a != null && a instanceof Player)
		{
			this.setVisible(false);
			this.clearActions();
			this.remove();
		}
		if (a != null && a instanceof Bullet)
		{

			this.setVisible(false);
			this.clearActions();
			this.remove();
			a.setVisible(false);
			a.clearActions();
			a.remove();
		}
	}

}
