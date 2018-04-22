package com.kevinzhong.entity;

public class Mob extends Entity{

    private boolean leftAccel, rightAccel;
    private double moveSpeed;

    public Mob(int w, int h) {
        super(w, h);
        leftAccel = false;
        rightAccel = false;

    }

    public void update() {
        move();

        if (leftAccel)
            super.setxVel(super.getxVel() - moveSpeed);
        if (rightAccel)
            super.setxVel(super.getxVel() + moveSpeed);


        if (!(leftAccel && rightAccel))
            super.setxVel(super.getxVel() * DECELERATION_RATE );
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
