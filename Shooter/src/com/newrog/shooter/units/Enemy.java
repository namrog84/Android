package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.ShooterGame;

public class Enemy extends Entity {

	protected int health = 1;


	protected float turn = 0;

	protected float timer1;

	protected float speedModifer = 1;
	//protected float direction;

	protected Sprite shadow;
	private Sound sound;
	private Sound sound2;
	protected float scalarY = 1.0f;


	protected float speedXModifer = 1;
	protected float speedYModifer = 1;

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
		
		turn = 0;
		moveTo(MathUtils.random(0, 360), 10);
	
		

	}

	@Override
	protected void update() {
		translate(speedXModifer * velo.x, speedYModifer * velo.y);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if (shadow != null)
		{
			shadow.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
			shadow.setPosition(getCenterX() - sprite.getWidth() / 2 + 15, getY() - sprite.getHeight() / 2 - 10);
			shadow.setRotation(getRotation());
			shadow.setColor(new Color(0.1f, 0.1f, 0.1f, 0.2f));
			shadow.draw(batch);
		}

		if (sprite != null)
		{
			//sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
			sprite.setScale(1, 1 );
			sprite.setRotation(getRotation());
			sprite.setPosition(getX() + getWidth()  / 2 - sprite.getWidth()  / 2,
							   getY() + getHeight() / 2 - sprite.getHeight() / 2);
			
			sprite.draw(batch);
		}
		

	}


	protected float calculateAngle(float x1, float y1, float x2, float y2) {
		return MathUtils.atan2(y1 - y2, x1 - x2) * MathUtils.radiansToDegrees;
	}
//	protected float direction = 1;
	

}
