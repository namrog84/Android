
package com.newrog.shooter.units;

import com.badlogic.gdx.utils.Array;
import com.newrog.shooter.ShooterGame;

public class ExplosionFactory {
	private static ShooterGame game;

	public ExplosionFactory (ShooterGame game) {
		this.game = game;
	}

	private static void init () {
		explosions = new Array<Explosion>();

		// Lets start with 10
		for (int i = 0; i < 10; ++i) {
			explosions.add(new Explosion(game));
		}

	}

	public static Array<Explosion> explosions;

	public static Explosion getExplosion () {
		if (explosions == null) {
			init();
		}

		if (explosions.size < 1) { //If there aren't any more, lets add some more!
			for (int i = 0; i < 10; ++i) {
				explosions.add(new Explosion(game));
			}
		}
		Explosion e = explosions.get(0);
		explosions.removeIndex(0);
		return e;
	}
	
	public static void returnExplosion(Explosion e) {
		explosions.add(e);
	}

}
