/*
 * Swash Tech Ltd.
 *
 * Movement.java
 *
 * © 2018 Swash Tech Ltd. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.apadigal.martianrobots.constant;
// ---- Import Statements -----------------------------------------------------

import com.apadigal.martianrobots.action.Action;
import com.apadigal.martianrobots.action.Forward;
import com.apadigal.martianrobots.action.Left;
import com.apadigal.martianrobots.action.Right;
import com.apadigal.martianrobots.bean.MarsLimits;
import com.apadigal.martianrobots.bean.Position;
import com.apadigal.martianrobots.bean.Robot;

import java.util.List;

public enum Movement {
    L(new Left()),
    R(new Right()),
    F(new Forward());
    final Action action;

    Movement(Action action) {
        this.action = action;
    }

    public void move(Robot robot, MarsLimits marsLimits, List<Position> scentList){
        action.move(robot, marsLimits,scentList);
    }
}