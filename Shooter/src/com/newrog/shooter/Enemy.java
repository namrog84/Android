package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor{

	public int health;
	private float speed;
	private Vector2 velocity;
	private Texture texture;
	public Sprite sprite;
	
	ShooterGame game;
	public Enemy(ShooterGame game) {
		this.game = game;
		float start = MathUtils.random(0,1);
		if(start > 0.75f) {
			setPosition(game.gameScreen.stage.getWidth()+10, MathUtils.random(0, game.gameScreen.stage.getHeight()));
		}else if(start > 0.5f) {
			setPosition(-10, MathUtils.random(0, game.gameScreen.stage.getHeight()));
		}else if(start > 0.25f) {
			setPosition(MathUtils.random(0, game.gameScreen.stage.getWidth()) , 0 );
		}else {
			setPosition(MathUtils.random(0, game.gameScreen.stage.getWidth()), MathUtils.random(0, game.gameScreen.stage.getHeight()));
		}

		speed = .01f;
		
		texture = new Texture(Gdx.files.internal("stealth_plane.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		TextureRegion region = new TextureRegion(texture, 0, 0, 106, 126);
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
		timer1 = 80 + MathUtils.random(0, 60);
		velocity = new Vector2(1,1);
	}
	float timer1;
	@Override
	public void draw(SpriteBatch batch, float parentalpha) {
		
		update();
		
		sprite.setRotation(getRotation()+180);
		sprite.setPosition(getX(), getY());
		sprite.draw(batch);
	}

	private float turn = 0;
	private float randomAng;
	private Vector2 pos = new Vector2(200,200);

	private void update() {
		timer1 -= Gdx.graphics.getDeltaTime() * 500;
		
		if (timer1 < 0) {
			if (turn < .01f && turn > -0.01f) {
				turn = MathUtils.random.nextFloat();
				if (turn >= 0.4f) {
					turn = -1;
				} else {
					turn = 1;
				}
				timer1 = 50 + MathUtils.random(0, 50);
			}
			else {
				turn = 0;
				timer1 = 30 + MathUtils.random(0, 30);
				//randomAng = calculateAngle(MathUtils.random(20, 800), MathUtils.random(20, 800), getX(), getY());
			}
		}
		  if (turn < .01f && turn > -0.01f)
          {
              //if (PlayState.ship.exists)
              {
                  //if (Ship.count == 0)
                  //{
                      pos.x = game.gameScreen.p.getX();
                      pos.y = game.gameScreen.p.getY();
                  //}
                 // else
                 // {
                 //     this.pos.x = Ship.exX[(Ship.count - 1)];
                 //     this.pos.y = Ship.exY[(Ship.count - 1)];
                 // }
                      moveTo(calculateAngle(getX(), getY(), pos.x, pos.y), 10 * Gdx.graphics.getDeltaTime());
              }
              //else
              //{
               //   motionAdd(calculateAngle(FlxG.mouse.x, FlxG.mouse.y, x, y), 1400 * FlxG.elapsed);
              //}
         // }
         // else //if (getX() < 20 || getX() > 800 - 20 || getY() < 20 || getY() > 800 - 20)
          //{
        	//  System.out.println(getX() + "asdasdasdasd " + getY());
        //	  moveTo(calculateAngle(getX(), getY(), Gdx.input.getX(), Gdx.input.getY()), 1400 * Gdx.graphics.getDeltaTime());
		  
          }else {
        	  direction = direction + turn * Gdx.graphics.getDeltaTime() * 10;
          }
		 
		  setDirection(direction);
		  setRotation(direction);
		  
		  setPosition(getX()+velocity.x, getY()+velocity.y);
	}

	private void moveTo(float angle, float delta) {
		float _loc_3 = MathUtils.cosDeg(direction) * speed;
		float _loc_4 = MathUtils.sinDeg(direction) * speed;
		float _loc_5 = MathUtils.cosDeg(angle) * delta;
		float _loc_6 = MathUtils.sinDeg(angle) * delta;
		float _loc_7 = _loc_3 + _loc_5;
		float _loc_8 = _loc_4 + _loc_6;

		speed = (float) Math.sqrt(_loc_7 * _loc_7 + _loc_8 * _loc_8);
		direction = MathUtils.atan2(_loc_8, _loc_7) * MathUtils.radiansToDegrees;
	}

	public float direction;
	public void setDirection(float number) {
		float a = (float) Math.sqrt(velocity.x * velocity.x + velocity.y+velocity.y);
		
		if(a>0) {
			speed = a;
		}
		direction = number;
		computeVelocity();	
	}
	
	
	private void computeVelocity() {
		velocity.x = speed * MathUtils.cosDeg(direction);
		velocity.y = speed * MathUtils.sinDeg(direction);
	}
	
	
	private float calculateAngle(float x1, float y1, float x2, float y2) {
		return MathUtils.atan2(y2-y1, x2-x1)*MathUtils.radiansToDegrees;
	}
	
}
