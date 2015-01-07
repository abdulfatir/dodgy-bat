package com.af.dodgybat.screens;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.utils.FlapAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class FlapMenuScreen implements Screen, InputProcessor {

	private TextureRegion bg;
	private TextureRegion play;
	private TextureRegion sound,info;
	private SpriteBatch sprite;
	private DodgyBat game;
	private Rectangle plrect;
	private Rectangle soundrect;


	public FlapMenuScreen(DodgyBat game) {
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if ((screenX >= plrect.x && screenX <= plrect.x+plrect.width)
				&& (screenY >= DodgyBat.SCREEN_HEIGHT - plrect.y - plrect.height && screenY <= DodgyBat.SCREEN_HEIGHT-plrect.y)) {
			game.setScreen(game.ready);
		}
		else if ((screenX >= soundrect.x && screenX <= soundrect.x+soundrect.width)
				&& (screenY >= DodgyBat.SCREEN_HEIGHT - soundrect.y - soundrect.height && screenY <= DodgyBat.SCREEN_HEIGHT-soundrect.y)) {
			boolean soundEff = DodgyBat.PREFS.getBoolean("SoundEffects", true);
			if(soundEff)
				DodgyBat.PREFS.putBoolean("SoundEffects", false);
			else
				DodgyBat.PREFS.putBoolean("SoundEffects", true);
			DodgyBat.PREFS.flush();
		}
		else if ((screenX >= 20 && screenX <= 20+soundrect.width)
				&& (screenY >= DodgyBat.SCREEN_HEIGHT - soundrect.y - soundrect.height && screenY <= DodgyBat.SCREEN_HEIGHT-soundrect.y)) {
			game.setScreen(game.about);
		}
		return false;
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
		// BG
		sprite.draw(bg, 0, 0, DodgyBat.SCREEN_WIDTH, DodgyBat.SCREEN_HEIGHT);
		// PlayButton
		if (play.getRegionWidth() < DodgyBat.SCREEN_WIDTH)
		{
			plrect = new Rectangle(DodgyBat.SCREEN_WIDTH / 2 - play.getRegionWidth()
					/ 2, DodgyBat.SCREEN_HEIGHT / 2 - play.getRegionHeight()
					/ 2, play.getRegionWidth(), play.getRegionHeight());
		}
		else
		plrect = new Rectangle(DodgyBat.SCREEN_WIDTH / 2 - 100,
						DodgyBat.SCREEN_HEIGHT / 2 - 50, 200, 100);
				sprite.draw(play, plrect.x,plrect.y,plrect.width,plrect.height);
		soundrect = new Rectangle(DodgyBat.SCREEN_WIDTH-70,DodgyBat.SCREEN_HEIGHT-70,50,50);
		
		boolean soundEff = DodgyBat.PREFS.getBoolean("SoundEffects", true);
		if(soundEff)
			sound = FlapAssetManager.getTexture("sound-on");
		else
			sound = FlapAssetManager.getTexture("sound-off");
		sprite.draw(info,20,soundrect.y,soundrect.width,soundrect.height);
		sprite.draw(sound,soundrect.x,soundrect.y,soundrect.width,soundrect.height);
		sprite.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

		bg = FlapAssetManager.getTexture("cloud-day");
		play = FlapAssetManager.getTexture("play");
		info = FlapAssetManager.getTexture("info");
		sprite = new SpriteBatch();
		DodgyBat.PREFS = Gdx.app.getPreferences("com.af.FlapFlap");
		Gdx.input.setCatchBackKey(false);
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
