package com.newrog.shooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ShooterGame extends Game{

	public IntroScreen introScreen;
	public GameScreen gameScreen;
	
	public TextureAtlas theArt;
	@Override
	public void create()
	{
		theArt = new TextureAtlas("arty.txt");
		
		introScreen = new IntroScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(introScreen);
		
	}

}
