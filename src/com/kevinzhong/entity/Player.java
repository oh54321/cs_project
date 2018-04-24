//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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
    private int health = 100;

    public Player() {
        super(32, 48);
        super.setWalkAnimation(new Animation(new SpriteSheet(ImageLoader.loadImage(playerSpriteLoc)), 0, 0, 16, 24, 11));
    }

    public void init() {
        this.setMoveSpeed(5.0D);
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public int getHealth() {
        return this.health;
    }

    public void setPlacingBlocks(boolean b) {
        this.placingBlocks = b;
    }

    public void setBreakingBlocks(boolean b) {
        this.breakingBlocks = b;
    }

    public boolean getPlacingBlocks() {
        return this.placingBlocks;
    }

    public boolean getBreakingBlocks() {
        return this.breakingBlocks;
    }

    public int getCurrentBlock() {
        return this.currentBlock;
    }

    public void rotateBlockUp() {
        ++this.currentBlock;
        if (this.currentBlock >= this.totalIDs) {
            this.currentBlock = 0;
        }

    }

    public void rotateBlockDown() {
        --this.currentBlock;
        if (this.currentBlock < 0) {
            this.currentBlock = this.totalIDs - 1;
        }

    }
}
