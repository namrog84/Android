package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.newrog.shooter.ShooterGame;

public class Bullet extends Actor{

	private Texture texture;
	public Sprite sprite;
	
	private int lifeSpan = 400;
	
	public Bullet(ShooterGame game, float angle, float x, float y) {
	
		//texture = new Texture(Gdx.files.internal("ship.png"));
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region =game.theArt.findRegion("ship1"); 
				//new TextureRegion(texture, 0, 0, 72, 40);
		
		
		sprite = new Sprite(region);
				
		sprite.setScale(.5f);
		
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	  
	  //sprite.setColor(Color.RED);
	  sprite.setColor(0.3f, 0.3f, 1.0f, .5f);
		setRotation(angle);
		setPosition(x, y);
		setSize(sprite.getWidth(), sprite.getHeight());

		

	}
	
	public float speed = 10.0f;
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		this.translate(speed*MathUtils.cosDeg(getRotation()), speed*MathUtils.sinDeg(getRotation()));
		
		sprite.setRotation(getRotation());
		sprite.setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
		sprite.draw(batch);
		
		if(lifeSpan < 0 ) {
			this.remove();
		}
		--lifeSpan;
	}
	ShapeRenderer sr = new ShapeRenderer();
	public boolean isKill() {
		return lifeSpan < 0;
	}
	
	public void dispose() {
		texture.dispose();
	}
		
}
