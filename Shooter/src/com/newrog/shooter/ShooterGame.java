package com.newrog.shooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.newrog.shooter.screens.GameScreen;
import com.newrog.shooter.screens.IntroScreen;

public class ShooterGame extends Game {

	public IntroScreen introScreen;
	public GameScreen gameScreen;
	public TextureAtlas theArt;
	
	public float sound = 1.0f;
	
	@Override
	public void create() {
		System.out.println("ART");
		theArt = new TextureAtlas("arty.txt");
		System.out.println("ART END");
		introScreen = new IntroScreen(this);
		gameScreen = new GameScreen(this);

		
		setScreen(introScreen);
	}

}
