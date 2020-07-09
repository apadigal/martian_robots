/*
 * Swash Tech Ltd.
 *
 * MartianRobotsProcessor.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.bean.MarsLimits;
import com.apadigal.martianrobots.bean.Movement;
import com.apadigal.martianrobots.bean.Position;
import com.apadigal.martianrobots.bean.Robot;
import com.apadigal.martianrobots.exception.MartianRobotsException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanetMars {
    private MarsLimits marsLimits;
    private List<Robot> robotList;
    private List<Position> scents = new ArrayList<>();

    public PlanetMars(MarsLimits marsLimits, List<Robot> robotList) {
        this.marsLimits = marsLimits;
        this.robotList = robotList;
    }

    public boolean startMission(){
        robotList.forEach(this::processRobot);
        return true;
    }

    private void processRobot(Robot robot){
        if(robot == null){
            throw new MartianRobotsException("Invalid Robot");
        }

        robot.getMovements().forEach(movement -> {
            if(robot.isLost())
                return;
            execute(movement, robot);
        });
    }

    private void execute(Movement movement, Robot robot){
        if(!robot.isLost()) {
            switch (movement) {
                case L:
                    robot.turnLeft();
                    break;
                case R:
                    robot.turnRight();
                    break;
                case F:
                    Position position = forwardPosition(robot);
                    if(isWithInLimits(position)) {
                        robot.moveForward(position);
                    }else if(!scents.contains(robot.getPosition())){
                        robot.setLost(true);
                        scents.add(robot.getPosition());
                    }
                    break;

            }
        }
    }

    @Override
    public String toString() {
        if(this.robotList != null ){
            return robotList.stream()
                    .map(Robot::toString)
                    .collect(Collectors.joining("\n"));
        }
        return "";
    }

    public Position forwardPosition(Robot robot){
        int newPositionX = robot.getPosition().getPositionX();
        int newPositionY = robot.getPosition().getPositionY();

        switch (robot.getOrientation()){
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

        return Position.builder().positionX(newPositionX).positionY(newPositionY).build();
    }

    public boolean isWithInLimits(Position position){
        return !(position.getPositionX() < marsLimits.getLeft() ||
                position.getPositionX() > marsLimits.getRight() ||
                position.getPositionY() < marsLimits.getBottom() ||
                position.getPositionY() > marsLimits.getTop());
    }
}