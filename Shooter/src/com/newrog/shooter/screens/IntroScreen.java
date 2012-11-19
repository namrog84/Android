package com.newrog.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.newrog.shooter.ShooterGame;

public class IntroScreen implements Screen {

	public Stage stage;
	private ShooterGame game;

	Skin skin;
	SpriteBatch batch;
	Texture texture1;
	Texture texture2;
	Actor root;
	Label fpsLabel;

	public IntroScreen(ShooterGame game) {
		//entities = new ArrayList<Entity>();
		stage = new Stage();
		stage.setViewport(800, 480, true);
		this.game = game;
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		startedTime = TimeUtils.millis();
		starter();	
	
	}
	public void setScreen() {
		game.setScreen(game.gameScreen);
	}

	public long startedTime = 0;
	private TextButtonStyle tbss;
	

	public void starter() {
		
		
		
		window = new Window("Dialog", skin);
		window.setWidth(stage.getWidth()/2);
		window.setHeight(stage.getHeight()/2);
		window.setPosition(stage.getWidth()/2 -window.getWidth()/2 , stage.getHeight()/2 - window.getHeight()/2);
		window.defaults().spaceBottom(10);
		window.setMovable(true);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/CONSOLA.TTF"));
		BitmapFont f = generator.generateFont(55);
		
		generator.dispose();
		l = new Label("PLAY", new LabelStyle(f, Color.WHITE));
		Skin aa = new Skin(Gdx.files.internal("packed.json"));
		
		
		//buttonMulti = new TextButton("ASFD", tbss);
		buttonMulti = new Button(aa, "default");
		buttonMulti.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setScreen();
			}
		});
	
		WidgetGroup w = new WidgetGroup();
		buttonMulti.setWidth(window.getWidth()/2);
		buttonMulti.setHeight(window.getHeight()/3);
		buttonMulti.setX(-buttonMulti.getWidth()/2);
		buttonMulti.setY(-buttonMulti.getHeight()/2);
		buttonMulti.addActor(l);
		l.addListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if(pointer == -1) {
					l.setColor(Color.BLACK);
				}
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if(pointer == -1) {
					l.setColor(Color.WHITE);
				}
			}
		});
		l.setPosition(buttonMulti.getWidth()/2-l.getWidth()/2, buttonMulti.getHeight()/2-l.getHeight()/2);
		w.addActor(buttonMulti);
		window.add(w);	
		stage.addActor(window);
		//window.toFront();
		
	}
	Button buttonMulti;
	private Label l;
	Window window;

	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(.1f, .4f, .1f, .8f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
		batch.begin();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
