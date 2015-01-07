package com.af.dodgybat.screens;

import com.af.dodgybat.DodgyBat;
import com.af.dodgybat.utils.FlapAssetManager;
import com.af.dodgybat.utils.SocialNetworkSharer;
import com.af.dodgybat.view.WorldRenderer.Mode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class FlapScoreScreen implements Screen, InputProcessor {
	
	private TextureRegion bg;
	private TextureRegion medalpad;
	private TextureRegion fb;
	private Rectangle fbrect;
	private TextureRegion play;
	private Rectangle mprect;
	private TextureRegion[] snums; 
	private TextureRegion tw;
	private Rectangle twrect;
	private SpriteBatch sprite;
	private TextureRegion[] medals;
	private TextureRegion new_sc,sc,hs,md;
	private DodgyBat game;
	private int medalIndex = -1;
	private int score;
	private Mode mode;
	private SocialNetworkSharer sharer;
	private boolean touched = false;
	private float StextH;
	
	public FlapScoreScreen(DodgyBat game, SocialNetworkSharer sharer)
	{
		this.game = game;
		this.sharer = sharer;
		mode = null;
	}
	
	public void setScore(int s)
	{
		score = s;
	}
	
	public void setMode(Mode _mode)
	{
		mode = _mode;
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
		touched =true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(touched){
		if( (screenX >= DodgyBat.SCREEN_WIDTH/2 - 75 && screenX <= DodgyBat.SCREEN_WIDTH/2 + 75) && (screenY<=DodgyBat.SCREEN_HEIGHT-mprect.y+120 && screenY>=DodgyBat.SCREEN_HEIGHT-mprect.y+120-90))
		{
			game.setScreen(game.ready);
		}
		else if( (screenX >= fbrect.x && screenX <= fbrect.x+fbrect.width) && (screenY<=DodgyBat.SCREEN_HEIGHT - fbrect.y && screenY>=DodgyBat.SCREEN_HEIGHT - fbrect.y-fbrect.height))
		{
			sharer.shareOnSocialNetworks("facebook",score);
		}
		else if( (screenX >= twrect.x && screenX <= twrect.x+twrect.width) && (screenY<=DodgyBat.SCREEN_HEIGHT - twrect.y && screenY>=DodgyBat.SCREEN_HEIGHT - twrect.y-twrect.height))
		{
			sharer.shareOnSocialNetworks("twitter",score);
		}
		}
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
	
	public void setMedalIndex(int mi)
	{
		medalIndex = mi;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		sprite.begin();
		//BG
		sprite.draw(bg,0,0,DodgyBat.SCREEN_WIDTH,DodgyBat.SCREEN_HEIGHT);
		//Social
				if(fb.getRegionWidth() < DodgyBat.SCREEN_WIDTH/2)
				{
					float A = (DodgyBat.SCREEN_WIDTH/2 - 10 - fb.getRegionWidth())/2;
					fbrect = new Rectangle(DodgyBat.SCREEN_WIDTH/4-fb.getRegionWidth()/2+A,DodgyBat.SCREEN_HEIGHT-(fb.getRegionHeight()+10),fb.getRegionWidth(),fb.getRegionHeight());
					twrect = new Rectangle((DodgyBat.SCREEN_WIDTH*3)/4-tw.getRegionWidth()/2-A,DodgyBat.SCREEN_HEIGHT-(tw.getRegionHeight()+10),tw.getRegionWidth(),tw.getRegionHeight());
				}
				else
				{
					float W = DodgyBat.SCREEN_WIDTH/2-10;
					float tW = fb.getRegionWidth();
					float tH = fb.getRegionHeight();
					float H = (tH/tW)*W;
					fbrect =new Rectangle(5,DodgyBat.SCREEN_HEIGHT-10-H,W,H);
					twrect = new Rectangle(DodgyBat.SCREEN_WIDTH/2+5,DodgyBat.SCREEN_HEIGHT-10-H,W,H);
				}
				sprite.draw(fb,fbrect.x,fbrect.y,fbrect.width,fbrect.height);
				sprite.draw(tw,twrect.x,twrect.y,twrect.width,twrect.height);
		//MedalPad
		if(medalpad.getRegionWidth()<DodgyBat.SCREEN_WIDTH)
		{
			float W = DodgyBat.SCREEN_WIDTH-20;
			float tW = medalpad.getRegionWidth();
			float tH = medalpad.getRegionHeight();
			float H = (tH/tW)*W;
			mprect = new Rectangle(DodgyBat.SCREEN_WIDTH/2 - W/2,fbrect.y-H-20,W,H);
		}
		else
		{
			float W = DodgyBat.SCREEN_WIDTH-20;
			float tW = medalpad.getRegionWidth();
			float tH = medalpad.getRegionHeight();
			float H = (tH/tW)*W;
		mprect = new Rectangle(10,fbrect.y-H-10,W,H);
		}
		sprite.draw(medalpad, mprect.x,mprect.y,mprect.width,mprect.height);
		float W = mprect.width/3;
		float tW = md.getRegionWidth();
		float tH = md.getRegionHeight();
		StextH = (tH/tW)*W;
		sprite.draw(md,mprect.x+10,mprect.y+mprect.height-20-StextH,W,StextH);
		sprite.draw(sc,mprect.x+mprect.width-10-W,mprect.y+mprect.height-20-StextH,W,StextH);
		
		//Score
		drawScoreandHS();
		//Replay
		W = DodgyBat.SCREEN_WIDTH/2;
		tW = play.getRegionWidth();
		tH = play.getRegionHeight();
		float pH = (tH/tW)*W;
		sprite.draw(play,DodgyBat.SCREEN_WIDTH/2 - W/2,mprect.y-pH-10,W,pH);
		//Medal
		if(medalIndex!=-1)
		{
			sprite.draw(medals[medalIndex],mprect.x+15,mprect.y+mprect.height-20-StextH-10-(mprect.width/3-10),mprect.width/3-10,mprect.width/3-10);
		}
		sprite.end();
	}

	private void drawScoreandHS() {
		String score = Integer.toString(this.score);
		float W = mprect.width/15;
		float ScoreWidth = score.length()*W;
		float tW = snums[0].getRegionWidth();
		float tH = snums[0].getRegionHeight();
		float H = (tH/tW)*W;
		
		float X= mprect.x + mprect.width - ScoreWidth -15; 
		float Y= mprect.y+mprect.height-20-StextH - H;
		
		float hW = mprect.width/2;
		float htW = hs.getRegionWidth();
		float htH = hs.getRegionHeight();
		float hH = (htH/htW)*hW;
		sprite.draw(hs,mprect.x+mprect.width-10-hW,mprect.y+20+H,hW,hH);
		
		for(int i = 0; i<score.length();i++,X+=W)
		{
			sprite.draw(snums[Integer.parseInt(score.charAt(i)+"")],X,Y,W,H);
		}
		
		int HighScore = DodgyBat.PREFS.getInteger("highScore",0);
		//CheckHS
		if(this.score>=HighScore)
		{
			DodgyBat.PREFS.putInteger("highScore", this.score);
			DodgyBat.PREFS.flush();
			HighScore = this.score;
			score="";
			score = Integer.toString(HighScore);
			ScoreWidth = score.length()*W;
			X= mprect.x + mprect.width - ScoreWidth -15;
			
			sprite.draw(new_sc,X-new_sc.getRegionWidth()-5,mprect.y+10,50,20);
		}
		else
		{
		score="";
		score = Integer.toString(HighScore);
		ScoreWidth = score.length()*W;
		X= mprect.x + mprect.width - ScoreWidth -15;
		}
		for(int i = 0; i<score.length();i++)
		{
			sprite.draw(snums[Integer.parseInt(score.charAt(i)+"")],X,mprect.y+10,W,H);
			X+=W;
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		if(mode.equals(Mode.DAY))
		bg =  FlapAssetManager.getTexture("cloud-day");
		else if (mode.equals(Mode.NIGHT))
		bg =  FlapAssetManager.getTexture("cloud-night");	
		medalpad = FlapAssetManager.getTexture("medalpad");
		fb = FlapAssetManager.getTexture("fb");
		play = FlapAssetManager.getTexture("play");
		tw = FlapAssetManager.getTexture("tweet");
		sc = FlapAssetManager.getTexture("score");
		hs = FlapAssetManager.getTexture("highscore");
		md = FlapAssetManager.getTexture("medal");
		snums = new TextureRegion[10];
		for(int i=0;i<10;i++)
		{
			snums[i] = FlapAssetManager.getTexture("snum-"+i);
		}
		medals = new TextureRegion[3];
		medals[0] = FlapAssetManager.getTexture("medal-bronze");
		medals[1] = FlapAssetManager.getTexture("medal-silver");
		medals[2] = FlapAssetManager.getTexture("medal-gold");
		new_sc = FlapAssetManager.getTexture("new-im");
		sprite = new SpriteBatch();
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
