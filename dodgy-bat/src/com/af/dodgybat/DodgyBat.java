package com.af.dodgybat;
import com.af.dodgybat.screens.FlapAboutScreen;
import com.af.dodgybat.screens.FlapMenuScreen;
import com.af.dodgybat.screens.FlapPlayScreen;
import com.af.dodgybat.screens.FlapReadyScreen;
import com.af.dodgybat.screens.FlapScoreScreen;
import com.af.dodgybat.screens.FlapSplashScreen;
import com.af.dodgybat.utils.FlapAssetManager;
import com.af.dodgybat.utils.SocialNetworkSharer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class DodgyBat extends Game {
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static Preferences PREFS;
	public FlapSplashScreen sp;
	public FlapMenuScreen menu;
	public FlapPlayScreen play;
	public FlapReadyScreen ready;
	public FlapScoreScreen score;
	public FlapAboutScreen about;
	private SocialNetworkSharer sharer;
	public DodgyBat(SocialNetworkSharer sharer) {
		this.sharer = sharer;
	}
	@Override
	public void create() {
		FlapAssetManager.init();
		SCREEN_WIDTH=Gdx.app.getGraphics().getWidth();
		SCREEN_HEIGHT=Gdx.app.getGraphics().getHeight();
		PREFS = Gdx.app.getPreferences("com.af.FlapFlap");
		sp = new FlapSplashScreen(this);
		about = new FlapAboutScreen(this);
		menu = new FlapMenuScreen(this);
		ready = new FlapReadyScreen(this);
		play = new FlapPlayScreen(this);
		score = new FlapScoreScreen(this,sharer);
		setScreen(sp);
	}
	
	@Override
	public void dispose()
	{
		FlapAssetManager.destroy();
	}

}
