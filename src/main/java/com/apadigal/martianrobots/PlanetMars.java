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

    /**
     * Process the given robot and it's movement.
     *    Simply call each movement's move method until it's lost.
     * @param robot Robot as argument to process
     */
    private void processRobot(Robot robot){
        if(robot == null){
            throw new MartianRobotsException("Invalid Robot");
        }

        robot.getMovements().forEach(movement -> {
            if(!robot.isLost())
                movement.move(robot, marsLimits, scents);
        });
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
}