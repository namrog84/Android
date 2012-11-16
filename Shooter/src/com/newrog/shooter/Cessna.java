package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Cessna extends Enemy{

	public Cessna(ShooterGame game) {
		super(game);

		TextureRegion region = game.theArt.findRegion("cessna1");//new TextureRegion(texture, 0, 0, 64, 74);
		
		sprite = new Sprite(region);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		shadow = new Sprite(region);
		shadow.setOrigin(sprite.getWidth() / 3, sprite.getHeight() / 3);
		
		setHeight(sprite.getHeight());
		setWidth(sprite.getWidth());
	}


	@Override
	protected void update()
	{
		handleCollisions();
		float delta = Gdx.graphics.getDeltaTime();
		
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
			float tempAng = calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY());
			moveTo(tempAng, 3 * delta);

		} else if (getX() < 20 || getX() > 800 - 20 || getY() < 20 || getY() > 800 - 20)
		{
			moveTo(calculateAngle(game.gameScreen.p.getX(), game.gameScreen.p.getY(), getX(), getY()), 3 * delta);
		} else
		{

			direction = direction + turn * delta * 50;
		}

		calculateSpeed();
		setRotation(direction);
		translate(2.5f*velo.x, 2.5f*velo.y);
	}

}
