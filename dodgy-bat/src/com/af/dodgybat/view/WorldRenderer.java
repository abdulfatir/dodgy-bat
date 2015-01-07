package com.af.dodgybat.view;

import java.util.LinkedList;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.model.Bat;
import com.af.dodgybat.model.Pipe;
import com.af.dodgybat.model.World;
import com.af.dodgybat.model.Bat.State;
import com.af.dodgybat.sound.SoundEffects;
import com.af.dodgybat.utils.FlapAssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {
	public enum Mode {
		DAY, NIGHT
	}

	private Mode mode;
	private World world;
	private OrthographicCamera cam;
	private LinkedList<Pipe> pipes;
	private Bat mew;
	private SpriteBatch sprite;
	private TextureRegion env;
	private TextureRegion bat_frame, ground_frame;
	private TextureRegion pipe_down;
	private TextureRegion pipe_up;
	private TextureRegion[] nums;
	private DodgyBat game;
	public int SCORE = 0;
	private SoundEffects sfx;
	public static boolean SCORED;
	private static final float FRAME_DURATION = 0.1f;
	private Animation batAnimation;
	private TextureRegion[] bat_frames;
	private String PiPeCoLoR;

	public WorldRenderer(World world, DodgyBat game) {
		this.world = world;
		this.sfx = world.getSFX();
		this.game = game;
		java.util.Random r = new java.util.Random();
		int color_index = r.nextInt(4);
		this.PiPeCoLoR = "pipe-" + color_index;
		SCORED = false;
		pipes = world.getPipes();
		mew = world.getCat();
		sprite = new SpriteBatch();
		loadTextures();
		this.cam = new OrthographicCamera(DodgyBat.SCREEN_WIDTH,
				DodgyBat.SCREEN_HEIGHT);
		this.cam.position.set(DodgyBat.SCREEN_WIDTH / 2,
				DodgyBat.SCREEN_HEIGHT / 2, 0);
		this.cam.update();
	}

	private void loadTextures() {
		java.util.Random r = new java.util.Random();
		if (r.nextBoolean())
			mode = Mode.DAY;
		else
			mode = Mode.NIGHT;
		bat_frame = null;
		ground_frame = null;
		bat_frames = new TextureRegion[5];
		if (mode.equals(Mode.DAY)) {
			env = FlapAssetManager.getTexture("cloud-day");
			// Bat Animation
			for (int i = 0; i <= 4; i++) {
				bat_frames[i] = FlapAssetManager.getTexture("bat" + i);
			}
		} else if (mode.equals(Mode.NIGHT)) {
			env = FlapAssetManager.getTexture("cloud-night");
			// Bat Animation
			for (int i = 0; i <= 4; i++) {
				bat_frames[i] = FlapAssetManager.getTexture("bat" + i + "-n");
			}
		}
		// Pipes
		pipe_down = FlapAssetManager.getTexture(PiPeCoLoR);
		pipe_up = new TextureRegion(pipe_down);
		pipe_up.flip(false, true);
		// Ground
		ground_frame = FlapAssetManager.getTexture("ground");
		// Font
		nums = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			nums[i] = FlapAssetManager.getTexture("num-" + i);
		}

		TextureRegion anim_frames[] = { bat_frames[1], bat_frames[2] };
		batAnimation = new Animation(FRAME_DURATION, anim_frames);

	}

	public void render(float delta) {
		paintPlayWorld();
		checkCollisionsAndUpdateScore();
		updateElements(delta);
	}

	public void dispose() {
		sprite.dispose();
		sfx.destroySounds();
	}

	private void checkCollisionsAndUpdateScore() {
		if (!(mew.getState().equals(State.DYING))
				&& !(mew.getState().equals(State.DEAD))) {
			Pipe pipe = pipes.getFirst();
			Rectangle rect = pipe.getBounds();
			float x1 = pipe.getPosition().x;
			float LowerPipeHeight = (4 * DodgyBat.SCREEN_HEIGHT) / 5
					- rect.height;
			Rectangle lowerpipe = new Rectangle(x1, 0, rect.width,
					LowerPipeHeight);

			if (rect.overlaps(mew.getBounds())
					|| lowerpipe.overlaps(mew.getBounds())) {
				mew.setState(State.DYING);
				if (DodgyBat.PREFS.getBoolean("SoundEffects", true))
					sfx.playSound(2);
			} else if (pipes.getFirst().getPosition().x < mew.getPosition().x) {
				if (!SCORED) {
					SCORE++;
					SCORED = true;
					if (DodgyBat.PREFS.getBoolean("SoundEffects", true))
						sfx.playSound(3);
				}
			}
		} else if (mew.getState().equals(State.DEAD)) {
			game.score.setScore(SCORE);
			if (SCORE >= 50) {
				game.score.setMedalIndex(2);
			} else if (SCORE >= 30) {
				game.score.setMedalIndex(1);
			} else if (SCORE >= 15) {
				game.score.setMedalIndex(0);
			} else {
				game.score.setMedalIndex(-1);
			}
			game.score.setMode(mode);
			game.setScreen(game.score);
		}

	}

	private void updateElements(float delta) {
		if (!mew.getState().equals(State.IDLE)) {
			for (Pipe pipe : pipes) {
				pipe.update(delta);
			}
			if (pipes.getFirst().getPosition().x < -DodgyBat.SCREEN_WIDTH / 10) {
				world.addNewPipe();
			}
		}
		mew.update(delta);
	}

	private void paintPlayWorld() {
		sprite.begin();
		// Background Environment
		sprite.draw(env, 0, 0, DodgyBat.SCREEN_WIDTH, DodgyBat.SCREEN_HEIGHT);
		// Pipes
		for (Pipe pipe : pipes) {
			Rectangle rect = pipe.getBounds();
			float x1 = pipe.getPosition().x;
			float y1 = pipe.getPosition().y;
			sprite.draw(pipe_down, x1, y1, rect.width, rect.height);
			float LowerPipeHeight = (4 * DodgyBat.SCREEN_HEIGHT) / 5
					- rect.height;
			sprite.draw(pipe_up, x1, 0, rect.width, LowerPipeHeight);
		}
		// Bat
		if (mew.getState().equals(State.FLYING))
			bat_frame = batAnimation.getKeyFrame(mew.getAnimTime(), true);
		else if (mew.getState().equals(State.FALLING))
			bat_frame = bat_frames[0];
		else if (mew.getState().equals(State.DYING))
			bat_frame = bat_frames[4];
		else
			bat_frame = bat_frames[3];
		Rectangle rect = mew.getBounds();
		float x1 = mew.getPosition().x;
		float y1 = mew.getPosition().y;
		sprite.draw(bat_frame, x1, y1, rect.width, rect.height);
		// Ground
		sprite.draw(ground_frame, 0, 0, DodgyBat.SCREEN_WIDTH,
				DodgyBat.SCREEN_HEIGHT / 12);
		// Score
		drawScore();
		sprite.end();

	}

	private void drawScore() {
		String score = Integer.toString(SCORE);
		float ScoreWidth = score.length() * (DodgyBat.SCREEN_WIDTH / 10);
		float ScoreHeight = (110.0f / 70.0f) * (DodgyBat.SCREEN_WIDTH / 10);
		float X = DodgyBat.SCREEN_WIDTH / 2 - ScoreWidth / 2;
		for (int i = 0; i < score.length(); i++, X += (DodgyBat.SCREEN_WIDTH / 10)) {
			sprite.draw(nums[Integer.parseInt(score.charAt(i) + "")], X,
					(DodgyBat.SCREEN_HEIGHT - ScoreHeight) - 10,
					DodgyBat.SCREEN_WIDTH / 10, ScoreHeight);
		}

	}

}
