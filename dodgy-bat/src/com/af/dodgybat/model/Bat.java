package com.af.dodgybat.model;

import com.af.dodgybat.DodgyBat;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Bat {
	public enum State {
		FLYING, FALLING, DYING, DEAD, IDLE
	}

	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getVelocity() { 
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public static final float SIZE = DodgyBat.SCREEN_HEIGHT / 15;
	private float animTime = 0.0f;
	
	public float getAnimTime() {
		return animTime;
	}

	public static final Vector2 GRAVITY = new Vector2(0,
			-DodgyBat.SCREEN_HEIGHT*1.5f);//1.5
	public static final Vector2 DOUB_JUMP_ACC = new Vector2(0,
			DodgyBat.SCREEN_HEIGHT*4.5f);

	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	private State state;
	private float Distance = 0f;
	private boolean DoubleTap = false;
	

	public float getDistance() {
		return Distance;
	}

	public void setDistance(float distance) {
		if(position.y + distance >= DodgyBat.SCREEN_HEIGHT - SIZE)
			Distance += (DodgyBat.SCREEN_HEIGHT - SIZE - position.y);
		else
		Distance += distance;
	}

	public boolean isDoubleTap() {
		return DoubleTap;
	}

	public void setDoubleTap(boolean doubleTap) {
		DoubleTap = doubleTap;
	}

	public Bat(Vector2 position) {
		this.position = position;
		this.bounds.x = position.x;
		this.bounds.y = position.y;
		state = State.IDLE;
		velocity.set(0, 0);
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}

	public void update(float delta) {
		animTime += delta;
		if (state.equals(State.FLYING)) {
			if(DoubleTap)
			{
				//velocity.add(0, -(GRAVITY.y*4.0f*delta)/1.5f);
				velocity.add(0, DOUB_JUMP_ACC.y*delta);
			}
			float d = velocity.y * delta;
			if(Distance - d > 0)
			{
				position.add(0, d);
				Distance-=d;
			}
			else
			{
				Distance = 0;
				velocity.set(0,0);
				state = State.FALLING;
				DoubleTap = false;
			}
			if (position.y < DodgyBat.SCREEN_HEIGHT / 12 - SIZE) {
				setState(State.DEAD);
			}

		} else if (state.equals(State.FALLING)) {
			velocity.add(0, GRAVITY.y*delta);
			position.add(0, velocity.y * delta);
			bounds.x = position.x;
			bounds.y = position.y;
			if (position.y < DodgyBat.SCREEN_HEIGHT / 12 - SIZE) {
				setState(State.DEAD);
			}
		} else if (state.equals(State.DYING)) {
			velocity.add(0, GRAVITY.y * delta*2);
			position.add(0, velocity.y * delta);
			bounds.x = position.x;
			bounds.y = position.y;
			if (position.y < DodgyBat.SCREEN_HEIGHT / 12 - SIZE) {
				setState(State.DEAD);
			}
		}
	}
}
