package com.newrog.shooter;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Shooter";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 720;
		
		//cfg.resizable = false;
		//cfg.fullscreen=true;
		new LwjglApplication(new ShooterGame(), cfg);
	}
}
