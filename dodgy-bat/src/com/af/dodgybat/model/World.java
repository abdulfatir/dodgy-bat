package com.af.dodgybat.model;

import java.util.LinkedList;
import java.util.Random;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.sound.SoundEffects;
import com.af.dodgybat.view.WorldRenderer;
import com.badlogic.gdx.math.Vector2;

public class World {

	private LinkedList<Pipe> pipes = new LinkedList<Pipe>();
	private Bat meow;
	private SoundEffects sfx;
	private float[] pipeHeights = {DodgyBat.SCREEN_HEIGHT / 5, DodgyBat.SCREEN_HEIGHT / 4,
			DodgyBat.SCREEN_HEIGHT / 3, DodgyBat.SCREEN_HEIGHT / 2,
			DodgyBat.SCREEN_HEIGHT * 0.6f };
	private Random randomHindex;

	public LinkedList<Pipe> getPipes() {
		return pipes;
	}

	public Bat getCat() {
		return meow;
	}
	
	public SoundEffects getSFX()
	{
		return sfx;
	}
	public World() {
		randomHindex = new Random();
		sfx = new SoundEffects();
		createWorld();
	}

	private void createWorld() {

		meow = new Bat(new Vector2(DodgyBat.SCREEN_WIDTH / 5,
				DodgyBat.SCREEN_HEIGHT*2/3));
		makePipes();
	}

	public void addNewPipe() {
		// Logger.getGlobal().info("");
		Pipe last = pipes.getLast();
		float PH = pipeHeights[randomHindex.nextInt(5)];
		Pipe newpipe = new Pipe(last.getPosition().x + DodgyBat.SCREEN_WIDTH*0.6f, DodgyBat.SCREEN_HEIGHT - PH, DodgyBat.SCREEN_WIDTH / 8,
				PH);
		pipes.add(newpipe);
		pipes.removeFirst();
		WorldRenderer.SCORED = false;
	}

	private void makePipes() {
		float PH = pipeHeights[randomHindex.nextInt(5)];
		pipes.add(new Pipe(DodgyBat.SCREEN_WIDTH, DodgyBat.SCREEN_HEIGHT - PH,
				DodgyBat.SCREEN_WIDTH / 8, PH));
		PH = pipeHeights[randomHindex.nextInt(5)];
		pipes.add(new Pipe(DodgyBat.SCREEN_WIDTH + DodgyBat.SCREEN_WIDTH*0.6f,
				DodgyBat.SCREEN_HEIGHT - PH, DodgyBat.SCREEN_WIDTH / 8, PH));
	}
}