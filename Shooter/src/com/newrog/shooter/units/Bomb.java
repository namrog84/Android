package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newrog.shooter.ShooterGame;

public class Bomb extends Entity{

	public Bomb(ShooterGame game, float x, float y) {
		
		circle.x = x;
		circle.y = y;
		circle.radius=1;
		game.gameScreen.entities.add(this);
	}
	@Override
	protected void update () {
		setRadius(getRadius() + 10);
		if(getRadius() > 750) {
			life = 0;
		}
		
	}

	@Override
	protected void render (SpriteBatch batch) {
		
		
	}

}
