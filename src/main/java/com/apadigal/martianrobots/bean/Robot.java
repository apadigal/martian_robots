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
import java.util.stream.Collectors;

public class Robot {
    List<Movement> movements;
    private Position position;
    private Orientation orientation;
    private boolean lost;

    public Robot(String positionString, String strMovements) {
        if(positionString == null){
            throw new MartianRobotsException("Invalid Position data");
        }
        String[] positionDetails = positionString.split(" ");
        if(positionDetails.length != 3){
            throw new MartianRobotsException("Invalid Position data");
        }
        int positionX = Integer.parseInt(positionDetails[0]);
        int positionY = Integer.parseInt(positionDetails[1]);
        this.orientation = Orientation.valueOf(positionDetails[2]);
        this.position = Position.builder().positionX(positionX).positionY(positionY).build();
        if(strMovements == null || strMovements.trim().length() == 0){
            throw new MartianRobotsException("Invalid Movements data");
        }
        this.movements = Arrays.asList(strMovements.split("")).stream().map(Movement::valueOf).collect(Collectors.toList());
    }

    public List<Movement> getMovements() {
        return movements;
    }

/*
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void turnLeft(){
        this.coordinate.setOrientation(this.coordinate.getOrientation().turnLeft());
    }

    public void turnRight(){
        this.coordinate.setOrientation(this.coordinate.getOrientation().turnRight());
    }

    public void moveForward(Position position){
        this.coordinate.setPosition(position);
    }
*/

    public void turnLeft(){
        this.setOrientation(this.getOrientation().turnLeft());
    }

    public void turnRight(){
        this.setOrientation(this.getOrientation().turnRight());
    }

    public void moveForward(Position position){
        this.setPosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return position.getPositionX() + " " + position.getPositionY() + " " + orientation + (isLost() ? " LOST": "");
    }

}