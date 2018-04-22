package com.kevinzhong.entity;

import com.kevinzhong.gfx.Animation;
import com.kevinzhong.gfx.ImageLoader;
import com.kevinzhong.gfx.SpriteSheet;

public class Player extends Mob {

	private int currentBlock = 0;
	private boolean placingBlocks = false;
	private boolean breakingBlocks = false;
	private static String playerSpriteLoc = "resources/spritesheets/mobsprites.png";
	private int totalIDs = 4;

	public Player() {

		super(32, 48); // fix this
		super.setWalkAnimation(new Animation(new SpriteSheet(ImageLoader.loadImage(playerSpriteLoc)), 0, 0, 16, 24, 11  ));
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