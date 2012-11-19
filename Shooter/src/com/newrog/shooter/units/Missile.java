package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.ShooterGame;

public class Missile extends Ammunition {

	//public float speed = 10.0f;
	private int lifeSpan = 400;
	protected boolean exists = false;
	protected float counter = 0;
	private float finDir;
	Sprite smokeSprite;
	Animation smoke;
	
	TextureRegion[] smokeyTrail;
	TextureRegion currentFrame;
	public Missile(ShooterGame game, float angle, float x, float y) {
		super(game, angle, x, y);
	
		TextureRegion region = game.theArt.findRegion("missile");
		TextureRegion region2 = game.theArt.findRegion("MissileTrail2");
		TextureRegion[][] tmp = region2.split(64, 28);
		TextureRegion[] smokeyTrail = new TextureRegion[5];
		
		for(int i = 0; i < 5; i++) {
			smokeyTrail[i] = tmp[0][i];
		}
		System.out.println("A " + tmp.length);
		smoke = new Animation(.15f, smokeyTrail);
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth(), -sprite.getHeight() / 2);
		smokeSprite = new Sprite(smoke.getKeyFrame(0));
		sprite.setColor(0.8f, 0.8f, 0.8f, .99f);
		setRotation(angle);
		setPosition(x, y);
		setSize(sprite.getWidth() / 2, sprite.getHeight());
		speed = 5f;
		
		//smokeSprite = new Sprite(region);
		//smokeSprite.setOrigin(smokeSprite.getWidth()/2, smokeSprite.getHeight()/2);
		exists = true;
		computeVelocity();
		velo.x = -1 * velo.x / 5;
		velo.y = velo.y * -1 / 5;
		//computeVelocity();
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	@Override
	protected void update() {
		super.update();
		
		//timer1 = timer1 - FlxG.elapsed * 12;
		timer1 -= delta * 6;
		
        if (timer1 < 0 && counter == 0) //in the beginning!
        {
            setSpeed(.8f);
            computeVelocity();
            counter++;
        }
        else if (counter > 0)
        {
            if (timer1 < 0)
            {
                float _loc_2 = MathUtils.random(0,1.0f);
                if (_loc_2 >= 0.5)
                {
                    _loc_2 = -1;
                }
                else
                {
                    _loc_2 = 1;
                }
                
                finDir = direction + 500f * delta * _loc_2;
                if (Math.abs(finDir - direction) > 180)
                {
                    finDir = direction + MathUtils.random(0,1.0f) * 200 - 100;
                }
                if (finDir >= 360)
                {
                    finDir = finDir % 360;
                }
                timer1 = .5f;
            }
            else
            {
                if (Math.abs(finDir - direction) > 120)
                {
                	//System.out.println("1");
                    finDir = direction + MathUtils.random(0,1.0f) * 200 - 100;
                }
                if (Math.abs(finDir - direction) < 180)
                {
                	//System.out.println("2");
                    direction = direction + 2 * (finDir - direction) * 5*delta;
                    
                }
                else
                {
                    direction = direction - (finDir - direction) * delta / 2;
                }
                if (direction >= 360)
                {
                    direction = direction % 360;
                }
               // setRotation(direction);//angle = direction;
                if (speed < 52.0000f)
                {
                    speed = speed + 5.20000f * delta;
                    setSpeed(speed);
                    
                }
                setRotation(direction);
            }
            
          //  computeVelocity();
        }
        
      /*  for(_loc_1 in PlayState.enemies)
        {
            
            if (HitTest.complexHitTestObject(this.dummy, _loc_1.dummy, 6))
            {
                FlxG.play(this.SndMissileHit, FlxG.volume + 0.2);
                new MissExp(this.x, this.y, "missile");
                this._reset();
                break;
            }
        }*/
	}


	 float stateTime = 0 ;

	@Override
	protected void render(SpriteBatch batch) {
	
		if(exists) {
			stateTime+= Gdx.graphics.getDeltaTime();
			currentFrame = smoke.getKeyFrame(stateTime, true);

			smokeSprite.setRegion(currentFrame);
			smokeSprite.setRotation(getRotation());
			smokeSprite.setPosition(getX() - smokeSprite.getWidth() / 2, getY()-smokeSprite.getHeight());
			smokeSprite.draw(batch);
			
			sprite.setRotation(getRotation());
			sprite.setPosition(getCenterX() - sprite.getWidth() / 2, getY());
			sprite.draw(batch);
			
		
		
			
		}
		
		
	}
	

}
