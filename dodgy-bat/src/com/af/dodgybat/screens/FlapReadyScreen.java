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

public class FlapReadyScreen implements Screen, InputProcessor {
	
	private TextureRegion bg;
	private TextureRegion ready;
	private TextureRegion info;
	private SpriteBatch sprite;
	private DodgyBat game;

	
	public FlapReadyScreen(DodgyBat game)
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
		game.setScreen(game.play);
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
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		sprite.begin();
		//BG
		sprite.draw(bg,0,0,DodgyBat.SCREEN_WIDTH,DodgyBat.SCREEN_HEIGHT);
		//Ready
		float RH=ready.getRegionHeight();
		
		if (ready.getRegionWidth() < DodgyBat.SCREEN_WIDTH)
			sprite.draw(ready, DodgyBat.SCREEN_WIDTH / 2 - ready.getRegionWidth()
					/ 2, DodgyBat.SCREEN_HEIGHT - 30 - ready.getRegionHeight(),
					ready.getRegionWidth(), ready.getRegionHeight());
		else
		{
			float TW=ready.getRegionWidth();
			RH = (RH/TW)*(DodgyBat.SCREEN_WIDTH-20);
			
		sprite.draw(ready, 10,DodgyBat.SCREEN_HEIGHT-30-RH,DodgyBat.SCREEN_WIDTH-20,RH);
		}
		//Info
		if (info.getRegionWidth() < DodgyBat.SCREEN_WIDTH)
			sprite.draw(info, DodgyBat.SCREEN_WIDTH / 2 - info.getRegionWidth()
					/ 2, DodgyBat.SCREEN_HEIGHT - 50 - ready.getRegionHeight() - info.getRegionHeight(),
					info.getRegionWidth(), info.getRegionHeight());
		else
		{
			float th=info.getRegionHeight();
			float tw=info.getRegionWidth();
			float H = (th/tw)*(DodgyBat.SCREEN_WIDTH-20);
		sprite.draw(info, 10,DodgyBat.SCREEN_HEIGHT-50-RH-H,DodgyBat.SCREEN_WIDTH-20,H);
		}
		sprite.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		bg =  FlapAssetManager.getTexture("cloud-day");
		ready =FlapAssetManager.getTexture("ready");
		info  = FlapAssetManager.getTexture("instruct");
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
