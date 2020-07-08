package com.apadigal.martianrobots.bean;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.apadigal.martianrobots.bean.Orientation.E;
import static com.apadigal.martianrobots.bean.Orientation.N;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void turnLeft() {
        Coordinate coordinate = new Coordinate(2, 3, E);
        coordinate.turnLeft();
        assertEquals(E.turnLeft(), coordinate.getOrientation());
    }

    @Test
    void turnRight() {
        Coordinate coordinate = new Coordinate(2, 3, N);
        coordinate.turnRight();
        assertEquals(N.turnRight(), coordinate.getOrientation());
    }

    @Test
    void moveForward() {
        Coordinate coordinate = new Coordinate(2, 3, N);
        MarsLimits marsLimits = Mockito.spy(new MarsLimits("2 4"));
        assertTrue(coordinate.moveForward(marsLimits));
    }

    @Test
    void falseWhenMovingAtTheEndOfTheLimits(){
        Coordinate coordinate = new Coordinate(2, 3, N);
        MarsLimits marsLimits = Mockito.spy(new MarsLimits("2 3"));
        assertFalse(coordinate.moveForward(marsLimits));
    }

    @Test
    void isLost() {
        Coordinate coordinate = new Coordinate(2, 3, N);
        MarsLimits marsLimits = Mockito.spy(new MarsLimits("2 3"));
        coordinate.moveForward(marsLimits);
        assertTrue(coordinate.isLost());
    }
}