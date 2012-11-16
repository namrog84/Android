package com.newrog.shooter.units;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.newrog.shooter.ShooterGame;

public class Prop extends Entity {

	public Prop(ShooterGame game) {

		TextureRegion region = game.theArt.findRegion("rotor1");

		// random start location of prop!
		setRotation(MathUtils.random(360));

		sprite = new Sprite(region);

		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		setSize(sprite.getWidth(), sprite.getHeight());

	}

	//rewrite prop to integrate with traditional update/render
	public void render(SpriteBatch batch, Player p) {
		rotate(20);
		setPosition(p.getX() + 12 * MathUtils.cosDeg(p.getRotation()),
					p.getY() + 12 * MathUtils.sinDeg(p.getRotation()));

		sprite.setRotation(getRotation());
		sprite.setPosition(getX() - getWidth() / 2, getY() - getHeight() / 2);
		sprite.draw(batch);
	}

	@Override
	protected void update() {

	}

	@Override
	protected void render(SpriteBatch batch, float parentalpha) {
		// TODO Auto-generated method stub

	}

}
