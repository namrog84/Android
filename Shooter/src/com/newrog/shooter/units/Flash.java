package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;

public class Flash extends Entity{

	
	private float scale = 1f;

	public Flash(ShooterGame game, float x, float y) {
		setPosition(x, y);
		sprite = new Sprite(Art.bulletFlashTR);
		
		setSize(0, 0);
		game.gameScreen.entities.add(this);
	}
	
	
	@Override
	protected void update() {
		scale -= 8f*delta;
		if(scale < 0) {
			life =0;
		}
		
	}

	@Override
	protected void render(SpriteBatch batch) {
		sprite.setPosition(getX() - sprite.getWidth()/2 ,
					       getY() - sprite.getHeight()/2);
		sprite.setScale(scale);
		sprite.draw(batch);
		
	}



}
