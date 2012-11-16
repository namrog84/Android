package com.newrog.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveActorAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.TimeUtils;
import com.newrog.shooter.ShooterGame;
import com.newrog.shooter.units.Player;

public class IntroScreen implements Screen {

	public Stage stage;
	private ShooterGame game;

	public IntroScreen(ShooterGame game)
	{
		stage = new Stage();

		this.game = game;
		starter();
		
		startedTime = TimeUtils.millis();
	}
	public long startedTime = 0;
	
	public int count = 0;

	public void starter()
	{
		count = stage.getActors().size;
		for (int i = count; i < 10; ++i)
		{
			Player p = new Player(game);
			p.setPosition(MathUtils.random(10)*80, MathUtils.random(600,1200));
			p.rotate(-90);
			stage.addActor(p);
			SequenceAction saa = new SequenceAction();
			ParallelAction pa = new ParallelAction();
			MoveToAction a = new MoveToAction();
			a.setPosition(p.getX(), -50);
			if(MathUtils.random(20) == 1) {
				RotateToAction rta = new RotateToAction();
				rta.setRotation(990);
				rta.setDuration(5);
				pa.addAction(rta);
			}
			
			a.setDuration(MathUtils.random(5, 10));
			pa.addAction(a);
			saa.addAction(pa);
			
			RemoveActorAction raa = new RemoveActorAction();
			saa.addAction(raa);
			p.addAction(saa);
		}
	}

	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(.1f, .4f, .1f, .8f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		starter();
		stage.draw();
		stage.act();
		
		if(startedTime + 4000 < TimeUtils.millis() || Gdx.input.isTouched()|| Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			game.setScreen(game.gameScreen);
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
