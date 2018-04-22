package com.kevinzhong.tile;

public class Tile {
    private int type;
    private int active;

    public Tile() {
        this.type = 0;
        this.active = 0;
    }

    public Tile(Tile src) {
        this.type = src.getType();
        this.active = src.getActive();
    }

    public void copy(Tile from) {
        this.type = from.getType();
        this.active = from.getActive();
    }

    public void clear() {
        type = 0;
        active = 0;
    }

    public void setType(int i) {
        type = i;
    }

    public void setActive(int i) {
        active = i;
    }

    public int getType() {
        return type;
    }

    public int getActive() {
        return active;
    }

    public static int TILE_SIZE = 16;

    public static int getTileSize() {
        return TILE_SIZE;
    }

}
