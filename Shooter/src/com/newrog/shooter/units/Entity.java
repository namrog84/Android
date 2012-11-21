
package com.newrog.shooter.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.newrog.shooter.ShooterGame;

public abstract class Entity {

	protected Texture texture;
	protected Sprite sprite;
	protected ShooterGame game;

	public int life = 1;
	public int zIndex = 0;
	public boolean active = true;

	public Rectangle rect = new Rectangle(0, 0, 15, 5);
	protected Vector2 velo = new Vector2(0, 0);
	protected float direction;
	public float speed = 1;

	ShapeRenderer sr = new ShapeRenderer();

	protected float delta;
	private boolean debug = true;

	public Entity () {

	}

	protected void setSpeed (float speed2) {
		speed = speed2;
		computeVelocity();
	}

	public void setRotation (float angle) {
		direction = angle;
	}

	public void rotate (float angle) {
		direction += angle;
	}

	protected void setSize (float width, float height) {
		rect.setWidth(width);
		rect.setHeight(height);

	}

	public void setPosition (float x, float y) {
		rect.x = x;
		rect.y = y;
	}

	public void draw (SpriteBatch batch) {
		delta = Gdx.graphics.getDeltaTime();
		if (active) update();

		render(batch);

		if (debug) {
			sr.setProjectionMatrix(batch.getProjectionMatrix());
			sr.begin(ShapeType.Rectangle);
			sr.setColor(Color.RED);
			sr.rect(getX(), getY(), getWidth(), getHeight());
			sr.end();
		}
	}

	protected abstract void update ();

	protected abstract void render (SpriteBatch batch);

	protected float getHeight () {
		return rect.getHeight();
	}

	public float getCenterX () {
		return rect.x + rect.width / 2;
	}

	public float getCenterY () {
		return rect.y + rect.height / 2;
	}

	public float getY () {
		return rect.y;
	}

	protected float getWidth () {
		return rect.getWidth();

	}

	public float getX () {
		return rect.x;
	}

	public float getRotation () {
		return direction;
	}

	public void translate (float x, float y) {
		rect.x += x;
		rect.y += y;
	}

	protected void setWidth (float width) {
		rect.setWidth(width);

	}

	protected void setHeight (float height) {
		rect.setHeight(height);
	}

	protected void moveTo (float newDirection, float strengthMove) {
		float x1 = MathUtils.cosDeg(direction) * speed;
		float y1 = MathUtils.sinDeg(direction) * speed;
		float x2 = MathUtils.cosDeg(newDirection) * strengthMove;
		float y2 = MathUtils.sinDeg(newDirection) * strengthMove;
		float xc = x1 + x2;
		float yc = y1 + y2;

		speed = (float)Math.sqrt(xc * xc + yc + yc);
		direction = MathUtils.atan2(yc, xc) * MathUtils.radiansToDegrees;
	}

	protected void calculateSpeed () {
		float a = (float)Math.sqrt(velo.x * velo.x + velo.y * velo.y);
		if (a > 0) {
			speed = a;
		}
		computeVelocity();
	}

	protected void computeVelocity () {
		velo.x = speed * MathUtils.cosDeg(direction);
		velo.y = speed * MathUtils.sinDeg(direction);
	}

}
