/*
 * Swash Tech Ltd.
 *
 * Coordinate.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.bean;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.exception.MartianRobotsException;

public class Coordinate {
    private int positionX;
    private int positionY;
    private Orientation orientation;
    private boolean lost;

    public Coordinate(int positionX, int positionY, Orientation orientation) {
        if(orientation == null){
            throw new MartianRobotsException("Invalid Orientation");
        }
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turnLeft(){
        this.orientation = this.orientation.turnLeft();
    }

    public void turnRight(){
        this.orientation = this.orientation.turnRight();
    }

    public boolean moveForward(MarsLimits marsLimits){
        int newPositionX = this.positionX;
        int newPositionY = this.positionY;
        switch (this.orientation){
            case N:
                newPositionY += 1;
                break;
            case E:
                newPositionX += 1;
                break;
            case W:
                newPositionX -= 1;
                break;
            case S:
                newPositionY -= 1;
                break;
        }
        if(newPositionX < marsLimits.getLeft() ||
           newPositionX > marsLimits.getRight() ||
           newPositionY < marsLimits.getBottom() ||
           newPositionY > marsLimits.getTop()){
            this.lost = true;
        }else
        {
            this.positionX = newPositionX;
            this.positionY = newPositionY;
        }
        return !this.lost;
    }

    public boolean isLost() {
        return lost;
    }

    @Override
    public String toString() {
        return positionX + " " + positionY + " " + orientation + (isLost() ? " LOST": "");
    }
}