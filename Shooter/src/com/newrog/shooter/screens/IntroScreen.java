
package com.newrog.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
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
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.newrog.shooter.ShooterGame;

public class IntroScreen implements Screen {

	public Stage stage;
	private ShooterGame game;

	private Skin skin;
	private SpriteBatch batch;
	//private Texture texture1;
	//private Texture texture2;
	//private Actor root;
	//private Label fpsLabel;
	//public long startedTime = 0;
	
	private Button buttonMulti;
	private Label label;
	private Button buttonMulti2;
	private Label label2;
	private Window window;
	
	public IntroScreen (ShooterGame game) {
		stage = new Stage();
		stage.setViewport(800, 480, true);
		this.game = game;
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		//startedTime = TimeUtils.millis();
		starter();
	}

	public void setScreen () {
		game.setScreen(game.gameScreen);
	}

	
	//private TextButtonStyle tbss;

	public void starter () {

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/CONSOLA.TTF"));
		BitmapFont f = generator.generateFont(22);
		window = new Window("Dialog", skin);
		WindowStyle ws = window.getStyle();
		ws.titleFont = f;
		
		f = generator.generateFont(55);
		generator.dispose();

		window.setStyle(ws);
		window.setWidth(stage.getWidth() / 2+stage.getWidth() / 4);
		window.setHeight(stage.getHeight() / 2+stage.getHeight() / 4);
		window.setPosition(stage.getWidth() / 2 - window.getWidth() / 2, stage.getHeight() / 2 - window.getHeight() / 2);
		window.setMovable(true);

		label = new Label("PLAY", new LabelStyle(f, Color.WHITE));
		Skin aa = new Skin(Gdx.files.internal("packed.json"));

		buttonMulti = new Button(aa, "default");
		buttonMulti.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				setScreen();
			}
		});

		WidgetGroup widGrp = new WidgetGroup();
		buttonMulti.setWidth(window.getWidth() / 2);
		buttonMulti.setHeight(window.getHeight() / 3);
		buttonMulti.setX(-buttonMulti.getWidth() / 2);
		buttonMulti.setY(+buttonMulti.getHeight() / 3);
		buttonMulti.addActor(label);
		label.addListener(new ClickListener() {
			@Override
			public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (pointer == -1) {
					label.setColor(Color.BLACK);
				}
			}
			@Override
			public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (pointer == -1) {
					label.setColor(Color.WHITE);
				}
			}
		});
		label.setPosition(buttonMulti.getWidth() / 2 - label.getWidth() / 2, buttonMulti.getHeight() / 2 - label.getHeight() / 2);
		widGrp.addActor(buttonMulti);
		
		
		buttonMulti2 = new Button(aa, "default");
		buttonMulti2.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				setScreen();
			}
		});

		
		
		label2 = new Label("INFO", new LabelStyle(f, Color.WHITE));
		WidgetGroup widGrp2 = new WidgetGroup();
		buttonMulti2.setWidth(window.getWidth() / 2);
		buttonMulti2.setHeight(window.getHeight() / 3);
		buttonMulti2.setX(-buttonMulti2.getWidth() / 2);
		buttonMulti2.setY(-buttonMulti2.getHeight() );
		buttonMulti2.addActor(label2);
		label2.addListener(new ClickListener() {
			@Override
			public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (pointer == -1) {
					label2.setColor(Color.BLACK);
				}
			}
			@Override
			public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (pointer == -1) {
					label2.setColor(Color.WHITE);
				}
			}
		});
		label2.setPosition(buttonMulti2.getWidth() / 2 - label2.getWidth() / 2, buttonMulti2.getHeight() / 2 - label2.getHeight() / 2);
		widGrp2.addActor(buttonMulti2);
		
		
		window.add(widGrp);
		window.add(widGrp2);
		stage.addActor(window);
	}



	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(.1f, .4f, .1f, .8f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act();
		
		batch.begin();
		batch.end();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void show () {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide () {

	}

	@Override
	public void pause () {

	}

	@Override
	public void resume () {

	}

	@Override
	public void dispose () {

	}

}
