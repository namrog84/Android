package com.newrog.shooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ShooterGame extends Game{

	public IntroScreen introScreen;
	public GameScreen gameScreen;
	
	
	@Override
	public void create()
	{
		introScreen = new IntroScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(introScreen);
	}

}
