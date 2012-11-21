
package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;
import com.newrog.shooter.screens.GameScreen;

public class PowerUp extends Entity {

	public enum PType {
		BOMB, SPREAD, LASER, MISSILE
	};

	public PType type;

	public PowerUp (ShooterGame game, float x, float y) {
		setPosition(x, y);
		sprite = new Sprite(Art.rotorTR);
		setSize(sprite.getWidth(), sprite.getHeight());
		
		switch (MathUtils.random(0, 3)) {
		case 0:
			this.type = PType.BOMB;
			sprite = new Sprite(Art.tankDummyTR);
			break;
		case 1:
			this.type = PType.SPREAD;
			break;
		case 2:
			this.type = PType.MISSILE;
			sprite = new Sprite(Art.shipTR);
			break;
		case 3:
			this.type = PType.LASER;
			break;
		}
		
		GameScreen.powerUps.add(this);
	}

	@Override
	protected void update () {
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		sprite.setPosition(getX(), getY());
		sprite.draw(batch);
	}

}
