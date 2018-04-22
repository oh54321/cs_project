package com.kevinzhong.entity;

import com.kevinzhong.gfx.ImageLoader;

public class Player extends Mob {

	private int currentBlock = 0;
	private boolean placingBlocks = false;
	private boolean breakingBlocks = false;
	private static String playerSpriteLoc = "resources/sprites/player.png";
	private int totalIDs = 4;

	public Player() {

		super(28, 44); // fix this
		super.setSprite(ImageLoader.loadImage(playerSpriteLoc));
	}

	public void init() {

		setMoveSpeed(5);

	}

	public void setPlacingBlocks(boolean b) {
		placingBlocks = b;
	}

	public void setBreakingBlocks(boolean b) {
		breakingBlocks = b;
	}

	public boolean getPlacingBlocks() {
		return placingBlocks;
	}

	public boolean getBreakingBlocks() {
		return breakingBlocks;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void rotateBlockUp() {
		currentBlock++;
		if(currentBlock >= totalIDs)
			currentBlock = 0;
	}
	public void rotateBlockDown() {
		currentBlock--;
		if(currentBlock < 0)
			currentBlock = totalIDs - 1;
	}

}