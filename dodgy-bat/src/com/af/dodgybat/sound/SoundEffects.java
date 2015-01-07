package com.af.dodgybat.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundEffects {
	private Sound FLY;
	private Sound DIE;
	private Sound ADD_POINT;
	
	public SoundEffects() {
		FLY = Gdx.audio.newSound(Gdx.files.internal("fly.mp3"));
		DIE = Gdx.audio.newSound(Gdx.files.internal("die.mp3"));
		ADD_POINT = Gdx.audio.newSound(Gdx.files.internal("point.mp3"));
	}
	
	public void playSound(int ID)
	{
		switch(ID)
		{
		case 1:
			FLY.play(1f);
			break;
		case 2:
			DIE.play(1f);
			break;
		case 3:
			ADD_POINT.play(1f);
			break;
		}
	}
	
	public void destroySounds()
	{
		FLY.dispose();
		DIE.dispose();
		ADD_POINT.dispose();
	}
}
