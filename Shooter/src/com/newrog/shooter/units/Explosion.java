
package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;

public class Explosion extends Entity {


	TextureRegion currentFrame;
	private Animation smoke;

	public Explosion (ShooterGame game) {
	
		smoke = new Animation(.03f, Art.explosionTRegions);
		sprite = new Sprite(smoke.getKeyFrame(0));
		zIndex= 50;
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
