package com.af.dodgybat.model;

import com.af.dodgybat.DodgyBat;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pipe {
private Rectangle bounds;
private Vector2 position;
//public static final Vector2 VELOCITY = new Vector2(-DodgyBat.SCREEN_WIDTH*0.33f,0);
public static final Vector2 VELOCITY = new Vector2(-DodgyBat.SCREEN_WIDTH*0.38f,0);
public Pipe(float x, float y, float width,float height) {
	position = new Vector2(x,y);
	bounds = new Rectangle(x,y,width,height);
}

public Rectangle getBounds() {
	return bounds;
}

public Vector2 getPosition() {
	return position;
}

public void setPosition(Vector2 position) {
	this.position = position;
}

public void update(float delta) {
	
	position.add(VELOCITY.cpy().scl(delta,0));
	bounds.x = position.x;
	bounds.y = position.y;
}
}
