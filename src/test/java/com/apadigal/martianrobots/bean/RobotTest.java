package com.apadigal.martianrobots.bean;

import com.apadigal.martianrobots.action.Forward;
import com.apadigal.martianrobots.exception.MartianRobotsException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static com.apadigal.martianrobots.constant.Orientation.E;
import static com.apadigal.martianrobots.constant.Orientation.N;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RobotTest {

    @Test
    @Order(1)
    public void testConstructor(){
        Robot robot = new Robot("1 1 E", "RFRFRFRF");
        assertNotNull(robot);
    }

    @ParameterizedTest
    @MethodSource(value = "getInvalidPositionData")
    @Order(2)
    public void throwErrorWhenInvalidPositionDataSuppliedToConstruct(String positionString, String strMovements){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> new Robot(positionString, strMovements));
        assertEquals("Invalid Position data", martianRobotsException.getMessage());
    }
    @ParameterizedTest
    @MethodSource(value = "getInvalidMovementData")
    @Order(3)
    public void throwErrorWhenInvalidMovementDataSuppliedToConstruct(String strMovements){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> new Robot("1 1 E",strMovements));
        assertEquals("Invalid Movements data", martianRobotsException.getMessage());
    }


    @Test
    void turnLeft() {
        Robot robot = new Robot("1 1 E", "RFRFRFRF");
        robot.turnLeft();
        assertEquals(E.turnLeft(), robot.getOrientation());
    }

    @Test
    void turnRight() {
        Robot robot = new Robot("1 1 N", "RFRFRFRF");
        robot.turnRight();
        assertEquals(N.turnRight(), robot.getOrientation());
    }


    @Test
    void moveForward() {
        Robot robot = new Robot("2 3 N", "RFRFRFRF");
        MarsLimits marsLimits = new MarsLimits(5, 3);
        Forward forward = new Forward();
        forward.move(robot, marsLimits, Collections.emptyList());
        assertEquals(2, robot.getPosition().getPositionX());
    }

    @Test
    void lostWhenMovingAtTheEndOfTheLimits(){
        Robot robot = new Robot("2 3 N", "RFRFRFRF");
        MarsLimits marsLimits = new MarsLimits(2, 3);
        Forward forward = new Forward();
        ArrayList<Position>  scentsList= new ArrayList<>();
        forward.move(robot, marsLimits, scentsList);
        assertTrue(robot.isLost());
    }

    @Test
    void notLostWhenMovingAtTheEndOfTheLimitsDueToPreviousRobotLostAndHasTheScent(){
        Robot robot = new Robot("2 3 N", "RFRFRFRF");
        MarsLimits marsLimits = new MarsLimits(2, 3);
        Forward forward = new Forward();
        ArrayList<Position>  scentsList= new ArrayList<>();
        forward.move(robot, marsLimits, scentsList);
        assertEquals(1, scentsList.size());
        Robot robot2 = new Robot(robot.getPosition().toString() + " "+ robot.getOrientation(), "FRFRFRF");
        assertFalse(robot2.isLost());
    }

    public static Stream<Arguments> getInvalidPositionData(){
        return Stream.of(Arguments.of("", ""),
                Arguments.of(null, ""),
                Arguments.of("null", "FRT"),
                Arguments.of("1 2", ""),
                Arguments.of("1 2", "RFLR")
        );
    }

    public static Stream<String> getInvalidMovementData(){
        return Stream.of("",null);
    }
}