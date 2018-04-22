package com.kevinzhong.entity;

import com.kevinzhong.gfx.Animation;

import java.awt.*;

public class Mob extends Entity {

    private boolean leftAccel, rightAccel;
    private double moveSpeed;
    private Animation playerWalk;
    private boolean facingRight, moonWalk;

    public Mob(int w, int h) {
        super(w, h);
        leftAccel = false;
        rightAccel = false;
        facingRight = true;
        moonWalk = false;
    }

    public void update() {
        move();

        if (rightAccel)
            super.setxVel(super.getxVel() + moveSpeed);
        if (leftAccel)
            super.setxVel(super.getxVel() - moveSpeed);

        if(getxVel() > 0)
            facingRight = true;
        else if(getxVel() < 0)
            facingRight = false;
        moonWalk = rightAccel && leftAccel;

        if (!(leftAccel && rightAccel)) {
            super.setxVel(super.getxVel() * DECELERATION_RATE);
        }
        if (Math.abs(super.getxVel()) < 1)
            playerWalk.setCurrentFrame(0);
        else
            playerWalk.runAnimation();

        playerWalk.setSpeed((int) Math.abs(super.getxVel()));

    }

    public void render(Graphics2D g) {
        if(moonWalk && !facingRight)
            playerWalk.drawAnimation(g, (int) super.getBounds().getX(), (int) super.getBounds().getY(),
                    (int) super.getBounds().getWidth(), (int) super.getBounds().getHeight(), false);
        if (moonWalk && facingRight)
            playerWalk.drawAnimation(g, (int) super.getBounds().getX(), (int) super.getBounds().getY(),
                    (int) super.getBounds().getWidth(), (int) super.getBounds().getHeight(), true);
            else
        playerWalk.drawAnimation(g, (int) super.getBounds().getX(), (int) super.getBounds().getY(),
                (int) super.getBounds().getWidth(), (int) super.getBounds().getHeight(), facingRight);
    }

    public void setWalkAnimation(Animation playerWalk) {
        this.playerWalk = playerWalk;
    }

    public void setLeftAccel(boolean b) {
        leftAccel = b;
    }

    public void setRightAccel(boolean b) {
        rightAccel = b;
    }

    public void setMoveSpeed(double x) {
        moveSpeed = x;
    }

}
