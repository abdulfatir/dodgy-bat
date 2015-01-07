package com.af.dodgybat.utils;

import java.util.HashMap;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FlapAssetManager {
	static HashMap<String, TextureRegion> map;
	static AssetManager manager;
	
public static void init()
{
	
	map = new HashMap<String,TextureRegion>();
	manager = new AssetManager();
	manager.load("data/textures.pack", TextureAtlas.class);
	manager.finishLoading();
	TextureAtlas atlas = manager.get("data/textures.pack",TextureAtlas.class);
	map.put("cloud-day", atlas.findRegion("cloud-day"));
	map.put("cloud-night", atlas.findRegion("cloud-night"));
	map.put("play", atlas.findRegion("play"));
	map.put("instruct", atlas.findRegion("instruct"));
	map.put("ready", atlas.findRegion("ready"));
	map.put("sound-off", atlas.findRegion("sound-off"));
	map.put("sound-on", atlas.findRegion("sound-on"));
	map.put("pipe-0", atlas.findRegion("pipe-0"));
	map.put("pipe-1", atlas.findRegion("pipe-1"));
	map.put("pipe-2", atlas.findRegion("pipe-2"));
	map.put("pipe-3", atlas.findRegion("pipe-3"));
	for(int i=0;i<10;i++)
	{
		map.put("num-"+i,atlas.findRegion("num-"+i));
	}
	map.put("medalpad", atlas.findRegion("medalpad"));
	map.put("fb", atlas.findRegion("fb"));
	map.put("tweet", atlas.findRegion("tweet"));
	for(int i=0;i<10;i++)
	{
		map.put("snum-"+i,atlas.findRegion("snum-"+i));
	}
	for(int i=0;i<=4;i++)
	{
		map.put("bat"+i,atlas.findRegion("bat"+i));
	}
	for(int i=0;i<=4;i++)
	{
		map.put("bat"+i+"-n",atlas.findRegion("bat"+i+"-n"));
	}
		map.put("ground",atlas.findRegion("ground"));
	map.put("medal-gold", atlas.findRegion("medal-gold"));
	map.put("medal-bronze", atlas.findRegion("medal-bronze"));
	map.put("medal-silver", atlas.findRegion("medal-silver"));
	map.put("splash", atlas.findRegion("splash"));
	map.put("new-im", atlas.findRegion("new-im"));
	map.put("medal", atlas.findRegion("medal"));
	map.put("highscore", atlas.findRegion("highscore"));
	map.put("score", atlas.findRegion("score"));
	map.put("taw", atlas.findRegion("taw"));
	map.put("info", atlas.findRegion("info"));
	map.put("af", atlas.findRegion("af"));
	map.put("af-name", atlas.findRegion("af-name"));
	map.put("solitary", atlas.findRegion("solitary"));
}

public static TextureRegion getTexture(String key)
{
	return map.get(key);
}
public static void destroy()
{
	manager.dispose();
}
}
