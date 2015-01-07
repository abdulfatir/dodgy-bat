package com.af.dodgybat.screens;


import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.model.Bat;
import com.af.dodgybat.model.World;
import com.af.dodgybat.model.Bat.State;
import com.af.dodgybat.sound.SoundEffects;
import com.af.dodgybat.view.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class FlapPlayScreen implements Screen, InputProcessor {

	private World world;
	private WorldRenderer renderer;
	private Bat _mew;
	private long touchDelta = -1;
	private long lastTouchTime = 0;
	private DodgyBat game;
	private SoundEffects sfx;

	public FlapPlayScreen(DodgyBat game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		init();
	}

	private void init() {
		world = new World();
		renderer = new WorldRenderer(world, game);
		_mew = world.getCat();
		sfx = world.getSFX();
		Gdx.input.setCatchBackKey(false);
		Gdx.input.setInputProcessor(this);

	}

	public void reinit() {
		init();
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
		renderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (_mew.getState().equals(State.IDLE)) {
			_mew.setState(State.FALLING);
			return true;
		} else if (_mew.getState().equals(State.DYING))
			return true;
		if (!(_mew.getState().equals(State.DYING))
				&& !(_mew.getState().equals(State.DEAD))) {
			long now = System.currentTimeMillis();
			touchDelta = now - lastTouchTime;
			lastTouchTime = now;
			if (touchDelta >= 0 && touchDelta <= 200) {
				_mew.setDoubleTap(true);
				_mew.setDistance(DodgyBat.SCREEN_HEIGHT/11);
				// SH/15
			} else {
				_mew.getVelocity().y = DodgyBat.SCREEN_HEIGHT / 3;
				_mew.setDistance(DodgyBat.SCREEN_HEIGHT/14);
				// SH/15
			}
			if (!(_mew.getState().equals(State.DYING))) {
				_mew.setState(State.FLYING);
				if(DodgyBat.PREFS.getBoolean("SoundEffects", true))
				sfx.playSound(1);
			}

		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// _mew.setState(State.FALLING);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
