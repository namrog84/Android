package com.newrog.shooter;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;

public class ShooterGameOld implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	Player player;

	public ArrayList<Actor> entities = new ArrayList<Actor>();
	public ArrayList<Actor> killList = new ArrayList<Actor>();
public Stage s;
	@Override
	public void create()
	{

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		
		
		batch = new SpriteBatch();
		s= new Stage(w, h, true);
		//player = new Player(this);
		Gdx.input.setInputProcessor(s);
		
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		player.dispose();
	}

	float leftControlX = 150;
	float leftControlY = 150;
	float rightControlX = 150;
	float rightControlY = 150;
	private TextureAtlas ta;
	public Touchpad tp;
	
	@Override
	public void render()
	{
	//	System.out.println(tp.isTouched());
		
		
		if(Gdx.input.isTouched()) {
			
					//0,75);
			
			s.draw();
			//leftControlX = rightControlX = Gdx.input.getX();
			rightControlY = -Gdx.input.getY()+Gdx.graphics.getHeight();
			float distance = (float) ((Math.pow(Gdx.input.getX()-700,2) + Math.pow(rightControlY-150,2))/100f);
			if(distance > 75)
				distance = 75;
			//System.out.println(distance);
			//System.out.println((rightControlY-150) + " "+ (rightControlX-700));
			//System.out.println(rightControlX);
			rightControlX =  700f +	distance
					* MathUtils.cos(MathUtils.atan2(rightControlY-150, Gdx.input.getX()-700f));
			rightControlY =  150f +	distance
					* MathUtils.sin(MathUtils.atan2(rightControlY-150, Gdx.input.getX()-700f));
			//System .out.println(rightControlX + " " + rightControlY);
			
		//	System.out.println((Math.pow(Gdx.input.getX()-700,2) + Math.pow(rightControlY-150,2))/100);
						//700f);// + 75f * MathUtils.cos(MathUtils.atan2(150f-rightControlY, -Gdx.input.getX()+700f)));// + 75*MathUtils.cos(MathUtils.atan2(rightControlY-150, rightControlX-700)));
		//	System.out.println(MathUtils.radiansToDegrees*MathUtils.atan2(rightControlY-150, Gdx.input.getX()-700f));
		//	rightControlY = MathUtils.clamp(rightControlY, 
		//		  700f - 75f * MathUtils.sin(MathUtils.atan2(rightControlY-150, -rightControlX+700)), 
		//			700f + 75f * MathUtils.sin(MathUtils.atan2(rightControlY-150, -rightControlX+700)));
		//	rightControlY = MathUtils.clamp(rightControlY, 
		//			150 - 75 * MathUtils.sin(MathUtils.atan2(150-rightControlY, rightControlX-700)), 
		//			150 + 75 * MathUtils.sin(MathUtils.atan2(150-rightControlY, rightControlX-700)));
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		
		//camera.zoom=1f;
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//tp.draw(batch, 1);
		
	
	
		//for (Actor a : entities)
	//	{
	//		((Bullet) a).render(batch);
		//	if (((Bullet) a).isKill())
		//	{
	//			killList.add((Bullet) a);
		//	}
	//	}

		player.render(batch);
		s.draw();
		
		
		batch.end();

		if (!killList.isEmpty())
		{
			entities.removeAll(killList);
		}
	}

	
	@Override
	public void resize(int width, int height)
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
}
