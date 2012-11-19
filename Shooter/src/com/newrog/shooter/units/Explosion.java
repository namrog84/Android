
package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Explosion extends Entity {


	TextureRegion currentFrame;
	private Animation smoke;

	public Explosion () {
		TextureRegion[][] tmp = game.theArt.findRegion("Explosion1").split(32, 28);
		TextureRegion[] explosionRegions = new TextureRegion[5];

		for (int i = 0; i < 5; i++) {
			explosionRegions[i] = tmp[0][i];
		}
		smoke = new Animation(.15f, explosionRegions);
		sprite = new Sprite(smoke.getKeyFrame(0));
		active = false;
	}

	public void init (float x, float y) {
		active = true;
		speed = MathUtils.random(0, 10);
		setRotation(MathUtils.random(0, 360));
		computeVelocity(); // go thata way!
		
		//increase scale depending upon object?
	}

	float stateTime = 0;

	@Override
	protected void update () {
		if (active) {
			translate(velo.x, velo.y);
			if (smoke.isAnimationFinished(stateTime)) {
				active = false;
			}
		}
	}

	@Override
	protected void render (SpriteBatch batch) {
		if (active) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = smoke.getKeyFrame(stateTime, false);
			sprite.setPosition(getX(), getY());
			sprite.draw(batch);
		}
	}

}
