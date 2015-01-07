package com.af.dodgybat.screens;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.utils.FlapAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FlapSplashScreen implements Screen {

	
	private TextureRegion logo;
	private SpriteBatch sprite;
	private DodgyBat game;
	private float time = 0.0f;

	
	public FlapSplashScreen(DodgyBat game)
	{
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		if(time >= 6.0f)
		{
			game.setScreen(game.menu);
		}
		else if(time >= 3.0f)
		{
			logo  = FlapAssetManager.getTexture("taw");
		}
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		sprite.begin();
		if(logo.getRegionWidth() < DodgyBat.SCREEN_WIDTH)
			sprite.draw(logo,DodgyBat.SCREEN_WIDTH/2-logo.getRegionWidth()/2,DodgyBat.SCREEN_HEIGHT/2-logo.getRegionHeight()/2,logo.getRegionWidth(),logo.getRegionHeight());
		else
			sprite.draw(logo,DodgyBat.SCREEN_WIDTH/2-logo.getRegionWidth()/2,DodgyBat.SCREEN_HEIGHT/2-logo.getRegionHeight()/2,DodgyBat.SCREEN_WIDTH,DodgyBat.SCREEN_WIDTH);
		sprite.end();
		time += delta;
	}

	@Override
	public void resize(int width, int height) {
		

	}

	@Override
	public void show() {
		logo  = FlapAssetManager.getTexture("splash");
		sprite = new SpriteBatch();
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
