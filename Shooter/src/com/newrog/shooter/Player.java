package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

public class Player extends Actor{
	private Texture texture;
	public Sprite sprite;
	private Input input;
	private ShooterGame game;
	public Player(ShooterGame game) {
		this.game = game;
		texture = new Texture(Gdx.files.internal("ship.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 72, 40);
		
		
		sprite = new Sprite(region);
				
		System.out.println(sprite.getWidth() + " " + sprite.getHeight());
		//sprite.setSize(1f, .1f);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		input = Gdx.input;
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
	//	System.out.println(sprite.getX() + " " + sprite.getY());
		prop = new Prop();
	
	
	}
	public Prop prop;
	private int shootDelay = 0;
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		this.toFront();
		//super.draw(batch, parentAlpha);
		sprite.setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
		sprite.setRotation(getRotation());
		sprite.draw(batch);
		
		prop.render(batch, this);
		
		//if(input.isKeyPressed(Input.Keys.SPACE)) {
		//	shoot();
		//}
		
		++shootDelay;
	}
	
	public void render(SpriteBatch batch) {
	
		if(input.isKeyPressed(Input.Keys.A)) {
			this.translate(-2, 0);
		}
		if(input.isKeyPressed(Input.Keys.D)) {
			this.translate(2, 0);
		}
		if(input.isKeyPressed(Input.Keys.W)) {
			this.translate(0, 2);
		}
		if(input.isKeyPressed(Input.Keys.S)) {
			this.translate(0, -2);
		}
	
		if(input.isKeyPressed(Input.Keys.LEFT)) {
			this.rotate(5);
		}
		if(input.isKeyPressed(Input.Keys.RIGHT)) {
			this.rotate(-5);
		}
		
		
		++shootDelay;
			

	  //setPosition(getX(), getY());
		sprite.setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
		sprite.setRotation(getRotation());
		sprite.draw(batch);
		
		prop.render(batch, this);
	}
	public void shoot() {
		if(shootDelay>10) {
			game.gameScreen.stage.addActor(new Bullet(game, getRotation(), getX(), getY()));
			shootDelay=0;
		}

	}
	
	public void dispose() {
		texture.dispose();
	}

}
