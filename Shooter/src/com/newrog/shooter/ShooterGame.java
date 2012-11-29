package com.newrog.shooter;

import com.badlogic.gdx.Game;
import com.newrog.shooter.screens.GameScreen;
import com.newrog.shooter.screens.IntroScreen;
import com.newrog.shooter.units.AmmunitionPool;


// TODO:
// restart screen
// better explosions
// physical parts explosion
// SAT collision

// tank ai
// add c-130
// add enemy helicopter

// laser weapons



public class ShooterGame extends Game {

	public static IntroScreen introScreen;
	public static GameScreen gameScreen;
	//public TextureAtlas theArt;
	
	public float sound = 1.0f;
	
	@Override
	public void create() {
		Art.init();
		AmmunitionPool p = new AmmunitionPool(this);
		AmmunitionPool.init();
		
		introScreen = new IntroScreen(this);
		gameScreen = new GameScreen(this);

		setScreen(introScreen);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		Art.reset(); //clear out the art!
	}
	
	@Override
	public void resume () {
		super.resume();	
	}
	
	public static GameScreen getGameScreenInstance() {
		return gameScreen;
	}
	

}
