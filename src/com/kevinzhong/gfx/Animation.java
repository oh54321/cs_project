package com.kevinzhong.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index, count;
    private BufferedImage[] frames;
    private BufferedImage currentFrame;

    public Animation(SpriteSheet ss, int x, int y, int w, int h, int frames) {
        this.frames = new BufferedImage[frames];
        for (int i = 0; i < this.frames.length; i++)
            this.frames[i] = ss.crop(x + w * i, y, w, h);
    }

    public void runAnimation() {
        index++;
        if (index > 8 - speed) {
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        count++;
        if (count >= frames.length)
            count = 1;
        currentFrame = frames[count];
    }

    public void setCurrentFrame(int current) {
        this.count = current;
        currentFrame = frames[current];
    }

    public void drawAnimation(Graphics2D g, int x, int y, int xScale, int yScale, boolean flipped) {
        if (flipped)
            g.drawImage(currentFrame, x, y, xScale, yScale, null);
        else
            g.drawImage(currentFrame, x + xScale, y, -xScale, yScale, null);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
