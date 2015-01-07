package com.af.dodgybat.screens;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.utils.FlapAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FlapAboutScreen implements Screen, InputProcessor {
	
	private SpriteBatch sprite;
	private DodgyBat game;
	private TextureRegion af;
	private TextureRegion name;
	private TextureRegion sol;

	
	public FlapAboutScreen(DodgyBat game)
	{
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Input.Keys.BACK)
		{
			game.setScreen(game.menu);
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//game.play.reinit();
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		game.setScreen(game.menu);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		sprite.begin();
		if(af.getRegionWidth()<DodgyBat.SCREEN_WIDTH)
		{
		sprite.draw(af,DodgyBat.SCREEN_WIDTH/2-af.getRegionWidth()/2,DodgyBat.SCREEN_HEIGHT-af.getRegionHeight()-20,af.getRegionWidth(),af.getRegionHeight());
		float W = DodgyBat.SCREEN_WIDTH;
		float tW = name.getRegionWidth();
		float tH = name.getRegionHeight();
		float H = (tH/tW)*W;
		sprite.draw(name,0,DodgyBat.SCREEN_HEIGHT-af.getRegionHeight()-20-H-10,W,H);
		 tW = sol.getRegionWidth();
		 tH = sol.getRegionHeight();
		float H2 = (tH/tW)*W;
		sprite.draw(sol,0,DodgyBat.SCREEN_HEIGHT-af.getRegionHeight()-20-H-20-H2,W,H2);
		}
		else
		{
			sprite.draw(af,0,DodgyBat.SCREEN_HEIGHT-DodgyBat.SCREEN_WIDTH,DodgyBat.SCREEN_WIDTH,DodgyBat.SCREEN_WIDTH);
			float W = DodgyBat.SCREEN_WIDTH;
			float tW = name.getRegionWidth();
			float tH = name.getRegionHeight();
			float H = (tH/tW)*W;
			sprite.draw(name,0,DodgyBat.SCREEN_HEIGHT-DodgyBat.SCREEN_WIDTH-H-5,W,H);
			 tW = sol.getRegionWidth();
			 tH = sol.getRegionHeight();
			float H2 = (tH/tW)*W;
			sprite.draw(sol,0,DodgyBat.SCREEN_HEIGHT-DodgyBat.SCREEN_WIDTH-H-10-H2,W,H2);
		}
		
		sprite.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		af  = FlapAssetManager.getTexture("af");
		name = FlapAssetManager.getTexture("af-name");
		sol = FlapAssetManager.getTexture("solitary");
		sprite = new SpriteBatch();
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(this);


	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() {
		sprite.dispose();
		Gdx.input.setInputProcessor(null);
	}

}
