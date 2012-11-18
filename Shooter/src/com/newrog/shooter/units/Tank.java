package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.ShooterGame;

public class Tank extends Enemy {

	Sprite turret;
	public Tank(ShooterGame game) {
		super(game);
		velo = new Vector2(1, 1);
		TextureRegion region = game.theArt.findRegion("Tank_ImgDummyTank");
		TextureRegion region2 = game.theArt.findRegion("Tank_ImgTurret");
		
		sprite = new Sprite(region);
		sprite.flip(true, false);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		turret = new Sprite(region2);
		turret.setOrigin(turret.getWidth()/2, turret.getHeight()/2);
		
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());

		
		speedXModifer = 1.5f;
		speedYModifer = 1.5f;
		life = 2;
	}
	private float aimDirection = 0;
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		//turret.setScale(1, scalarY );
		turret.setRotation(aimDirection);
		turret.setPosition(getCenterX() + 6 * MathUtils.cosDeg(aimDirection)-turret.getWidth()/2,
						   getCenterY() + 6 * MathUtils.sinDeg(aimDirection)-turret.getHeight()/2);
		//turret.setPosition(getCenterX()-9.5f,
			//			   getCenterY()-9.5f);
		
		
		turret.draw(batch);
	}
	private float shootTime = 0;
	@Override
	protected void update() {
		super.update();

		--shootTime;
		if(shootTime< 0) {
			game.gameScreen.entities.add(new TankBullet(game, aimDirection,getCenterX(),getCenterY()));
			shootTime = MathUtils.random(50f, 500f);
		}
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
		
		aimDirection = calculateAngle(game.gameScreen.p.getCenterX(), game.gameScreen.p.getCenterY(), getCenterX(), getCenterY());
		//System.out.println(getX() + " " + getY() + " " +getWidth() + " " + getHeight());
	}

}
