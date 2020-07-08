/*
 * Swash Tech Ltd.
 *
 * Robot.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.bean;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.exception.MartianRobotsException;

import java.util.Arrays;
import java.util.List;

public class Robot {
    int positionX;
    int positionY;
    String orientation;
    List<String> movements;

    public Robot(String positionString, String strMovements) {
        if(positionString == null){
            throw new MartianRobotsException("Invalid Position data");
        }
        String[] positionDetails = positionString.split(" ");
        if(positionDetails.length != 3){
            throw new MartianRobotsException("Invalid Position data");
        }
        positionX = Integer.parseInt(positionDetails[0]);
        positionY = Integer.parseInt(positionDetails[1]);
        orientation = positionDetails[2];
        if(strMovements == null || strMovements.trim().length() == 0){
            throw new MartianRobotsException("Invalid Movements data");
        }
        this.movements = Arrays.asList(strMovements.split(""));
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<String> getMovements() {
        return movements;
    }

    public void setMovements(List<String> movements) {
        this.movements = movements;
    }
}