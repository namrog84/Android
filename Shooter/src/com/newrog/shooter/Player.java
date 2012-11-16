package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity{
	private Input input;
	private Sprite shadow;
	
	private Sound sound;
	
	public Player(ShooterGame game) {
		this.game = game;
		
		//texture = new Texture(Gdx.files.internal("ship.png"));
		//texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region =game.theArt.findRegion("ship1"); 
				//new TextureRegion(texture, 0, 0, 72, 40);
		
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

		shadow = new Sprite(region);

		shadow.setPosition(-sprite.getWidth()/3, -sprite.getHeight()/3);

		
		input = Gdx.input;
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
		
		prop = new Prop();
		sound = Gdx.audio.newSound(Gdx.files.internal("sound_33.wav"));
	
	}
	
	public Prop prop;
	private int shootDelay = 0;

	@Override
	public void render (SpriteBatch batch, float parentAlpha) {
		this.toFront();
		
		shadow.setRotation(getRotation());
		shadow.setColor(new Color(0.1f,0.1f,0.1f, 0.2f));
		shadow.setPosition(getX() - sprite.getWidth() / 3, getY() - sprite.getWidth() / 3);
		shadow.draw(batch);
		
		sprite.setPosition(getX()-getWidth()/2, getY()-getHeight()/2);
		sprite.setRotation(getRotation());
		sprite.draw(batch);
		prop.render(batch, this);
		++shootDelay;
		
		float delta = Gdx.graphics.getDeltaTime();
		if(input.isKeyPressed(Input.Keys.SPACE)) {
			this.shoot();
		}
		if(input.isKeyPressed(Input.Keys.A)) {
			this.translate(-300*delta, 0);
		}
		if(input.isKeyPressed(Input.Keys.D)) {
			this.translate(300*delta, 0);
		}
		if(input.isKeyPressed(Input.Keys.W)) {
			this.translate(0, 300*delta);
		}
		if(input.isKeyPressed(Input.Keys.S)) {
			this.translate(0, -300*delta);
		}
		if(input.isKeyPressed(Input.Keys.LEFT)) {
			this.rotate(200*delta);
		}
		if(input.isKeyPressed(Input.Keys.RIGHT)) {
			this.rotate(-200*delta);
		}
		
		//if(input.isKeyPressed(Input.Keys.B)) {
		///	game.gameScreen.stage.addActor(new Enemy(game));
		//	blah = true;
		//}
	}
	boolean blah = false;
	

	public void shoot() {
		if(shootDelay>10) {
			game.gameScreen.stage.addActor(new Bullet(game, getRotation(), getX(), getY()));
			shootDelay=0;
			sound.play(1.0f);
		}

	}
	
	public void dispose() {
		texture.dispose();
	}

	@Override
	protected void update()
	{
		// TODO Auto-generated method stub
		
	}


}
