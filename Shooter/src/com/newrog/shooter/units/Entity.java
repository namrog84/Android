package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.newrog.shooter.ShooterGame;

public abstract class Entity extends Actor{

	protected Texture texture;
	protected Sprite sprite;
	protected ShooterGame game;

	public boolean active = true;
	
	public Entity() {
	 	
	}
	protected float delta;
	@Override
	public void draw(SpriteBatch batch, float parentalpha) {
		delta = Gdx.graphics.getDeltaTime();
		if(active)
			update();
		
		if(isVisible())
			render(batch, parentalpha);
	}
	
	protected abstract void update();
	protected abstract void render(SpriteBatch batch, float parentalpha);
	
	
}
