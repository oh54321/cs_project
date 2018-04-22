package com.kevinzhong.state;

import java.awt.Graphics2D;

public abstract class State {

	private static State currentState = null;

	public static State getState() {
		return currentState;
	}

	public static void setState(State s) {
		currentState = s;
	}

	public abstract void init();

	public abstract void render(Graphics2D g);

	public abstract void update();

}