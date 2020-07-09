/*
 * Swash Tech Ltd.
 *
 * Left.java
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

public class Left implements Action {
    @Override
    public void move(Robot robot, MarsLimits marsLimits, List<Position> scentList) {
        robot.turnLeft();
    }
}