package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Prop extends Actor{
	private Texture texture;
	public Sprite sprite;
	private int lifeSpan;
	
	public Prop() {
	texture = new Texture(Gdx.files.internal("rotor.png"));
	texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	
	TextureRegion region = new TextureRegion(texture, 0, 0, 64, 64);
	setRotation(MathUtils.random(360));
	//random starting location
	
	sprite = new Sprite(region);
			
	
	sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	setSize(sprite.getWidth(), sprite.getHeight());
	//sprite.setPosition(-sprite.getWidth()/2, sprite.getHeight()/2);
//	this.setPosition(100, 5)
	//System.out.println(sprite.getX() + " " + sprite.getY());
	}
	
	
	public void render(SpriteBatch batch, Player p) {
		rotate(20);
	//	this.translate(3*MathUtils.cosDeg(getRotation()), 3*MathUtils.sinDeg(getRotation()));
		//System.out.println(p.getY() + " " + getY());
		setPosition(p.getX()+12*MathUtils.cosDeg(p.getRotation()), p.getY()+12*MathUtils.sinDeg(p.getRotation()));//-sprite.getHeight()/2);
		
	
		//sprite.setOrigin(sprite.getWidth()/2, -sprite.getHeight()/2);
		sprite.setRotation(getRotation());
		sprite.setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
		sprite.draw(batch);
		
		//System.out.println(sprite.getX() + " " + sprite.getY());
		
	
		--lifeSpan;
		
	}
	
	
}
