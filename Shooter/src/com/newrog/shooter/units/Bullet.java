package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;

public class Bullet extends Ammunition {

	//public float speed = 10.0f;
	private int lifeSpan = 100;
	protected boolean exists = false;

	public Bullet(ShooterGame game, float angle, float x, float y) {
		super(game, angle, x, y);
		new Flash(game, x, y);
		
		sprite = new Sprite(Art.bulletTR);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight() / 2);

		sprite.setColor(0.8f, 0.8f, 0.8f, .99f);
		setRotation(angle);
		setPosition(x-sprite.getHeight()/2, y-sprite.getHeight()/2);
		
		setSize(sprite.getWidth() / 2, sprite.getHeight());
		speed = 3f;
		
		exists = true;
		computeVelocity();
		
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	@Override
	protected void update() {
		super.update();
		
	}


	@Override
	protected void render(SpriteBatch batch) {
		if(exists) {
			sprite.setRotation(getRotation());
			sprite.setPosition(getX()-getWidth()/2, getY());
			sprite.draw(batch);
		}
		
	}
	

}
