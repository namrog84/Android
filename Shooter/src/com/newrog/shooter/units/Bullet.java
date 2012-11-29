package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;

public class Bullet extends Ammunition {

	//public float speed = 10.0f;
	//private int lifeSpan = 100;
	protected boolean exists = false;

	public Bullet(ShooterGame game, float angle, float x, float y) {
		super(game, angle, x, y);
		
		sprite = new Sprite(Art.bulletTR);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight() / 2);

		sprite.setColor(0.8f, 0.8f, 0.8f, .99f);
		setRotation(angle);
		setPosition(x-sprite.getHeight()/2, y-sprite.getHeight()/2);
		
		setSize(sprite.getWidth() / 2, sprite.getHeight());
		setRadius(sprite.getWidth()/3);
		speed = 3f;
		
		exists = true;
		computeVelocity();
	}
	
	public void init(float angle, float x, float y) {
	    lifeSpan = 100;
	    life = 1;
	    velo = new Vector2(1, 1);
	    exists = true;
	    speed = 3f;
	    setRotation(angle);
	    setPosition(x-sprite.getHeight()/2, y-sprite.getHeight()/2);
	    computeVelocity();
	    new Flash(game, x, y);
	   // System.out.println("POW");
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
