package com.apadigal.martianrobots.constant;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.apadigal.martianrobots.constant.Orientation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {

    @ParameterizedTest
    @MethodSource("getTurnLeftOrientationData")
    public void successWhenTurnLeft(Orientation orientation, Orientation expected){
        assertEquals(expected, orientation.turnLeft());
    }

    @ParameterizedTest
    @MethodSource("getTurnRightOrientationData")
    public void successWhenTurnRight(Orientation orientation, Orientation expected){
        assertEquals(expected, orientation.turnRight());
    }

    public static Stream<Arguments> getTurnLeftOrientationData(){
        return Stream.of(Arguments.of(N, W),
                Arguments.of(E, N),
                Arguments.of(W, S),
                Arguments.of(S, E)
                );
    }

    public static Stream<Arguments> getTurnRightOrientationData(){
        return Stream.of(Arguments.of(N, E),
                Arguments.of(E, S),
                Arguments.of(W, N),
                Arguments.of(S, W)
                );
    }

}