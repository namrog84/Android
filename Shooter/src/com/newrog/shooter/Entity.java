package com.newrog.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor{

	protected Texture texture;
	protected Sprite sprite;
	protected ShooterGame game;

	public boolean active = true;
	
	public Entity() {
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentalpha) {
		if(active)
			update();
		
		if(isVisible())
			render(batch, parentalpha);
	}
	
	protected abstract void update();
	protected abstract void render(SpriteBatch batch, float parentalpha);
	
	
}
