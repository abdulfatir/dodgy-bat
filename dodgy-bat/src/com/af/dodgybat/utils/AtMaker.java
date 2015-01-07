package com.af.dodgybat.utils;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class AtMaker {

	public static void main(String[] args) {
		TexturePacker2.process("/images/", "/images/", "textures.pack");
	}
}
