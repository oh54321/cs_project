package com.kevinzhong.gfx;

import com.kevinzhong.handlers.Camera;

import java.awt.*;

public class ParallaxEngine {

    private ParallaxLayer[] layers;

    public ParallaxEngine(ParallaxLayer... layers) {
        this.layers = layers;
    }

    public void setLeft() {
        for (ParallaxLayer layer : layers)
            layer.setLeft();
    }

    public void setRight() {
        for (ParallaxLayer layer : layers)
            layer.setRight();
    }

    public void setUp() {
        for (ParallaxLayer layer : layers)
            layer.setUp();
    }

    public void setDown() {
        for (ParallaxLayer layer : layers)
            layer.setDown();
    }

    public void stopHorizontal() {
        for (ParallaxLayer layer : layers)
            layer.stopHorizontal();
    }

    public void stopVertical() {
        for (ParallaxLayer layer : layers)
            layer.stopVertical();
    }

    public void update() {
        for (ParallaxLayer layer : layers)
            layer.update();
    }

    public void render(Graphics2D g, Camera cam) {
        for (ParallaxLayer layer : layers)
            layer.render(g, cam);
    }
}
