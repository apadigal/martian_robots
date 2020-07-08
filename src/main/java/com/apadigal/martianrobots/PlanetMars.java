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
import com.apadigal.martianrobots.bean.Robot;
import com.apadigal.martianrobots.exception.MartianRobotsException;

import java.util.List;
import java.util.stream.Collectors;

public class PlanetMars {
    private MarsLimits marsLimits;
    private List<Robot> robotList;
    private List<String> scents;

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
            if(robot.getCoordinate().isLost())
                return;
            execute(movement, robot);
        });
    }

    private void execute(Movement movement, Robot robot){
        if(!robot.getCoordinate().isLost()) {
            switch (movement) {
                case L:
                    robot.getCoordinate().turnLeft();
                    break;
                case R:
                    robot.getCoordinate().turnRight();
                    break;
                case F:
                    boolean moved = robot.getCoordinate().moveForward(marsLimits);
                    break;

            }
        }
    }

    @Override
    public String toString() {
        if(this.robotList != null ){
            return robotList.stream()
                    .map(robot -> robot.getCoordinate().toString())
                    .collect(Collectors.joining("\n"));
        }
        return "";
    }
}