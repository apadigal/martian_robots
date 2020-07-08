/*
 * Swash Tech Ltd.
 *
 * Coordinates.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.bean;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.exception.MartianRobotsException;

public class MarsLimits {
    private int top;
    private int right;
    private int bottom = 0;
    private int left = 0;

    public MarsLimits(int top, int right) {
        if(top < 0 || top > 50 || right < 0 || right > 50 )
            throw new MartianRobotsException("Co-ordinates should bin in (1-50) only");
        this.top = top;
        this.right = right;
    }

    public MarsLimits(String strLimits) {
        try {
            String[] coordinates =  strLimits.split(" ");
            if(coordinates.length != 2)
                throw new MartianRobotsException("Invalid co-ordinates:"+ strLimits);

            this.right = Integer.parseInt(coordinates[0]);
            this.top = Integer.parseInt(coordinates[1]);
            if(top < 0 || top > 50 || right < 0 || right > 50 )
                throw new MartianRobotsException("Co-ordinates should bin in (1-50) only");
        }
        catch (NumberFormatException e){
            throw new MartianRobotsException("Invalid co-ordinates string");
        }
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}