
package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.ShooterGame;

public class Explosion extends Entity {


	TextureRegion currentFrame;
	private Animation smoke;

	public Explosion (ShooterGame game) {
		
		TextureRegion tmp0 = game.theArt.findRegion("exploded");
		
		TextureRegion[][] tmp = tmp0.split(64, 64);
		
		TextureRegion[] explosionRegions = new TextureRegion[25];
		//System.out.println(tmp[4].length);
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				explosionRegions[index++] = tmp[i][j];
			}
		}
		smoke = new Animation(.03f, explosionRegions);
		sprite = new Sprite(smoke.getKeyFrame(0));
		
		active = false;
	}

	public void init (float x, float y) {
		active = true;
		speed = MathUtils.random(0, 1);
		setPosition(x, y);
		setRotation(MathUtils.random(0, 360));
		computeVelocity(); // go thata way!
		
		//increase scale depending upon object?
		//System.out.println(smoke.animationDuration + " " + smoke.frameDuration);
	}
	public void init (float x, float y, int speed) {
		active = true;
		this.speed = speed;
		setPosition(x, y);
		setRotation(MathUtils.random(0, 360));
		computeVelocity(); // go thata way!
		
		//increase scale depending upon object?
		//System.out.println(smoke.animationDuration + " " + smoke.frameDuration);
	}

	float stateTime = 0;

	@Override
	protected void update () {
		if (active) {
			translate(velo.x, velo.y);
			if (smoke.isAnimationFinished(stateTime)) {
				active = false;
				life = 0;
			}
		}
	}

	@Override
	protected void render (SpriteBatch batch) {
		if (active) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = smoke.getKeyFrame(stateTime, false);
			sprite.setRegion(currentFrame);
			sprite.setPosition(getX(), getY());
			sprite.draw(batch);
		}
	}

}
