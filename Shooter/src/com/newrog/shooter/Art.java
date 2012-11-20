
package com.newrog.shooter;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static TextureAtlas theArt;

	public static TextureRegion background;
	public static TextureRegion[] explosionTRegions;
	public static TextureRegion[] missileTrailTRegions;
	public static TextureRegion airplaneTR;
	public static TextureRegion bulletTR;
	public static TextureRegion missileTR;
	public static TextureRegion bulletFlashTR;
	public static TextureRegion shipTR;
	public static TextureRegion rotorTR;
	public static TextureRegion tankTurretTR;
	public static TextureRegion tankBulletTR;
	public static TextureRegion tankDummyTR;

	private Art () {
		/*
		 * static class: Its a sloppy approach to this problem, but I just wanted a easy way to pull together the different art and
		 * findRegion shouldn't be called often.
		 */
	}

	public static void init () {
		theArt = new TextureAtlas("arty.txt");
		loadExplosion();
		loadMissileTrail();
		background = theArt.findRegion("classic_low");
		airplaneTR = theArt.findRegion("harrier1");
		bulletTR = theArt.findRegion("bullet3");
		missileTR = theArt.findRegion("missile");
		bulletFlashTR = theArt.findRegion("BulletFlash3");
		shipTR = theArt.findRegion("ship1");
		rotorTR = theArt.findRegion("rotor1");
		tankTurretTR = theArt.findRegion("Tank_ImgTurret");
		tankBulletTR = theArt.findRegion("bullet3");
		tankDummyTR = theArt.findRegion("Tank_ImgDummyTank");
	}

	public static void reset () {
		explosionTRegions = null;
		missileTrailTRegions = null;

		background = null;
		airplaneTR = null;
		bulletTR = null;
		missileTR = null;
		bulletFlashTR = null;
		shipTR = null;
		rotorTR = null;
		tankTurretTR = null;
		tankBulletTR = null;
		tankDummyTR = null;
	}

	private static void loadMissileTrail () {
		TextureRegion region2 = theArt.findRegion("MissileTrail2");
		TextureRegion[][] tmp = region2.split(64, 28);
		missileTrailTRegions = new TextureRegion[5];
		for (int i = 0; i < 5; i++) {
			missileTrailTRegions[i] = tmp[0][i];
		}
	}

	private static void loadExplosion () {
		TextureRegion explodedTR = theArt.findRegion("exploded");
		TextureRegion[][] tmp = explodedTR.split(64, 64);
		explosionTRegions = new TextureRegion[25];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				explosionTRegions[index++] = tmp[i][j];
			}
		}
	}

}
