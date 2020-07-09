/*
 * Swash Tech Ltd.
 *
 * Forward.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.action;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.bean.MarsLimits;
import com.apadigal.martianrobots.bean.Position;
import com.apadigal.martianrobots.bean.Robot;

import java.util.List;

public class Forward implements Action {
    /**
     * Move the
     *
     * @param robot Robot to move forward
     * @param marsLimits Limits of the Mars
     * @param scentList Current available scents
     */
    @Override
    public void move(Robot robot, MarsLimits marsLimits, List<Position> scentList) {
        Position position = forwardPosition(robot);
        if(isWithInLimits(position, marsLimits)) {
            robot.setPosition(position);
        }else if(!scentList.contains(robot.getPosition())){
            robot.setLost(true);
            scentList.add(robot.getPosition());
        }

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

    private boolean isWithInLimits(Position position, MarsLimits marsLimits){
        return !(position.getPositionX() < marsLimits.getLeft() ||
                position.getPositionX() > marsLimits.getRight() ||
                position.getPositionY() < marsLimits.getBottom() ||
                position.getPositionY() > marsLimits.getTop());
    }

}