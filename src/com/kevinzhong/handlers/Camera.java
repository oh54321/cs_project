package com.kevinzhong.handlers;

import com.kevinzhong.entity.Entity;
import com.kevinzhong.state.GameState;
import com.kevinzhong.tile.Tile;

public class Camera {

	private float x, y;
	private int width, height;

	public Camera(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(Entity player) {

		if (x >= 0 && x <= GameState.getMaxX() * Tile.getTileSize() - width)
			x = (float) (player.getBounds().getCenterX() - width / 2);

		if (y >= 0 - height && y <= GameState.getMaxY() * Tile.getTileSize())
			y = (float) (player.getBounds().getCenterY() - height * .6);

		if (x < 0)
			x = 0;
		else if (x > GameState.getMaxX() * Tile.getTileSize() - width) {
			x = GameState.getMaxX() * Tile.getTileSize() - width;
		}
		
		if( y < 0 )
			y = 0;
		else if (y > GameState.getMaxY() * Tile.getTileSize() - height)
			y = GameState.getMaxY() * Tile.getTileSize() - height;

		//System.out.println(x + " " + y + "  " + GameState.getMaxX() + " " + GameState.getMaxY());
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
