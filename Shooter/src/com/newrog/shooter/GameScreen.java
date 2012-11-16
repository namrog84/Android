package com.newrog.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

public class GameScreen implements Screen{

	Stage stage;
	ShooterGame game;
	private Touchpad tp;
	private Touchpad tp2;
	Player p;
	FPSLogger fps;
	
	public GameScreen(ShooterGame game) {
		this.game = game;
		stage = new Stage();
		
		fps = new FPSLogger();
		stage.setViewport(853, 853*Gdx.graphics.getHeight()/Gdx.graphics.getWidth(), true);
		//theArt = new TextureAtlas("arty.txt");
		
		
		p = new Player(game);
		p.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		stage.addActor(p);
		
		
		TextureAtlas ta = new TextureAtlas("dataa.txt");
		Skin stag = new Skin(ta);
		TouchpadStyle tps = new TouchpadStyle();
		tps.background = stag.getDrawable("outer");
		tps.knob = stag.getDrawable("path3769-6");
		tp = new Touchpad(0, tps);
		tp.setBounds(50, 50,200, 200);
		stage.addActor(tp);
		tp2 = new Touchpad(0, tps);
		tp2.setBounds(stage.getWidth()-250, 50,200, 200);
		stage.addActor(tp2);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta)
	{
		update(delta);
		
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
		
		if(sixty++ > 20) {
			if(MathUtils.randomBoolean())
				stage.addActor(new Airplane(game));
			else
				stage.addActor(new Cessna(game));
			sixty = 0;
		}
	}
	int sixty = 0;
	
	private void update(float delta)
	{
		tp.toFront();
		tp2.toFront();
		if(tp.isTouched()) {
			p.translate(tp.getKnobPercentX()*150*delta, tp.getKnobPercentY()*150*delta);	
		}
		if(tp2.isTouched()) {
			float getR = p.getRotation();
			p.setRotation(-getR);
			p.setRotation(MathUtils.radiansToDegrees*MathUtils.atan2(tp2.getKnobPercentY(), tp2.getKnobPercentX()));
			p.shoot();
		}		
		
		
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void show()
	{
		
	}

	@Override
	public void hide()
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void dispose()
	{
		
	}

}
