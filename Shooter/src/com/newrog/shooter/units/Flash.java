package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newrog.shooter.ShooterGame;

public class Flash extends Entity{

	
	private float scale = 1f;

	public Flash(ShooterGame game, float x, float y) {
		TextureRegion region = game.theArt.findRegion("BulletFlash3");
		
		//sscale = 1;
		setPosition(x, y);
		
		sprite = new Sprite(region);
		setSize(0, 0);
		//sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		//sprite.setPosition(-sprite.getWidth(), sprite.getHeight());
		game.gameScreen.entities.add(this);
	}
	
	
	@Override
	protected void update() {
		scale -= 8f*delta;
		if(scale < 0) {
			life =0;
			//game.gameScreen.removeList.add(this);
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
