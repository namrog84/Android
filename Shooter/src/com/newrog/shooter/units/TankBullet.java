package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newrog.shooter.ShooterGame;

public class TankBullet extends Enemy {

	//public float speed = 10.0f;
	private int lifeSpan = 150;
	protected boolean exists = false;

	public TankBullet(ShooterGame game, float angle, float x, float y) {
		super(game);
		new Flash(game, x, y);
		TextureRegion region = game.theArt.findRegion("bullet3");
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight() / 2);

		sprite.setColor(0.8f, 0.8f, 0.8f, .99f);
		setRotation(angle);
		setPosition(x-sprite.getHeight()/2, y-sprite.getHeight()/2);
		
		setSize(sprite.getWidth() / 2, sprite.getHeight());
		speed = 3f;
		
		exists = true;
		computeVelocity();
		life = 1;
	}



	@Override
	protected void update() {
		super.update();
		if(lifeSpan-- < 0) {
			life = 0;
		}
		//translate(speed * MathUtils.cosDeg(getRotation()), speed * MathUtils.sinDeg(getRotation()));
	
	}


	@Override
	public void render(SpriteBatch batch) {

		if(exists) {
			sprite.setRotation(getRotation());
			sprite.setPosition(getX()-getWidth()/2, getY());
			sprite.draw(batch);
		}
		
	}
	

}
