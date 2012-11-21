package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.Art;
import com.newrog.shooter.ShooterGame;

public class Airplane extends Enemy {

	
	public Airplane(ShooterGame game) {
		super(game);
		velo = new Vector2(1, 1);
		
		
		sprite = new Sprite(Art.airplaneTR);
		sprite.flip(true, false);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		shadow = new Sprite(Art.airplaneTR);
		shadow.flip(true, false);
		shadow.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		setHeight(.75f*sprite.getHeight());
		setWidth(.75f*sprite.getWidth());
		setRadius(sprite.getWidth()/3);
		
		zIndex = 10; 
		speedXModifer = 2.5f;
		speedYModifer = 2.5f;
		life = 2;
	}
	
	@Override
	protected void update() {
		super.update();

		timer1 -= delta * 50;

		if (timer1 < 0)
		{
			if (turn == 0)
			{
				if (MathUtils.randomBoolean())
				{
					turn = -1;
				} else
				{
					turn = 1;
				}
				timer1 = 45 + MathUtils.random(0, 45);
			} else
			{
				turn = 0;
				timer1 = 30 + MathUtils.random(00, 30);
			}
		}

		if (turn == 0)
		{
			moveTo(calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY()), 3f * delta);

		} else if (getX() < 20 || getX() > 800 - 20 || getY() < 20 || getY() > 800 - 20)
		{
			moveTo(calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY()), 3f * delta);
		} else
		{
			direction = direction + turn * delta * 50;
		}
		
		//scalarY = 0.8f + .2f*Math.abs(  ((Math.abs(getRotation())%90 - 45) / 45)   );

		//System.out.println(getRotation()%90);
		//System.out.println("T " +Math.abs(  ((Math.abs(getRotation())%90 - 45) / 45)   ));
		calculateSpeed();
		setRotation(direction);
		
		//System.out.println(getX() + " " + getY() + " " +getWidth() + " " + getHeight());
	}

}
