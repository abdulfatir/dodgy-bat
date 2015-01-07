package com.af.dodgybat.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Block {
	static final float SIZE = 1f;

	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();

	public Block(Vector2 pos) {
		this.position = pos;
		this.bounds.x = pos.x;
		this.bounds.y = pos.y;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
	}
}
