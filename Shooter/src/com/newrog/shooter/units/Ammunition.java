package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.ShooterGame;

public class Ammunition extends Entity {

	//public float speed = 10.0f;
	protected int lifeSpan = 100;
	protected boolean exists = false;
	protected float timer1 = 0;
	
	public Ammunition(ShooterGame game, float angle, float x, float y) {
		velo = new Vector2(1, 1);
		/*TextureRegion region = game.theArt.findRegion("bullet3");
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth(), -sprite.getHeight() / 2);

		sprite.setColor(0.9f, 0.3f, 0.2f, .8f);
		setRotation(angle);
		setPosition(x, y);
		setSize(sprite.getWidth() / 2, sprite.getHeight());*/

	}

	//public void dispose() {
		//texture.dispose();
	//}

	@Override
	protected void update() {
		translate(speed * velo.x, speed * velo.y);
		
		//translate(speed * MathUtils.cosDeg(getRotation()), speed * MathUtils.sinDeg(getRotation()));
		if (lifeSpan < 0)
		{
			life = 0;
			
		}
		--lifeSpan;
	}

	@Override
	protected void render(SpriteBatch batch) {

		if(exists) {
			sprite.setRotation(getRotation());
			sprite.setPosition(getCenterX() - sprite.getWidth() / 2, getY());
			sprite.draw(batch);
		}
		
	}

}
