package com.apadigal.martianrobots.bean;

import com.apadigal.martianrobots.exception.MartianRobotsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MarsLimitsTest {

    @Test
    public void successConstructor(){
        MarsLimits marsLimits = new MarsLimits(10, 20);
        assertNotNull(marsLimits);
        assertEquals(10, marsLimits.getTop());
        assertEquals(20, marsLimits.getRight());
    }

    @ParameterizedTest
    @MethodSource("getValidLimitStringArgument")
    public void exceptionWhenOutOfLimitsSuppliedAsLimits(String limits, int top, int right){
        MarsLimits marsLimits = new MarsLimits(limits);
        assertEquals(top, marsLimits.getTop());
        assertEquals(right, marsLimits.getRight());
    }

    @ParameterizedTest
    @MethodSource("getInvalidLimitArguments")
    public void exceptionWhenOutOfLimitsSuppliedAsLimits(int top, int right){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> new MarsLimits(top, right));
        assertEquals("Co-ordinates should bin in (1-50) only", martianRobotsException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getInvalidLimitArgumentsAsString")
    public void exceptionWhenOutOfLimitsStringSuppliedAsLimits(String strLimits){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> new MarsLimits(strLimits));
        assertEquals("Co-ordinates should bin in (1-50) only", martianRobotsException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getInvalidArgumentsAsString")
    public void exceptionWhenInvalidStringSuppliedAsLimits(String strLimits){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> {
            new MarsLimits(strLimits);
        });

        assertEquals("Invalid co-ordinates:"+ strLimits, martianRobotsException.getMessage());
    }

    public static Stream<Arguments> getInvalidLimitArguments(){
        return Stream.of(Arguments.of(56, 2),
                Arguments.of(-2, 23),
                Arguments.of(45, 52)
        );
    }

    public static Stream<Arguments> getInvalidLimitArgumentsAsString(){
        return Stream.of(Arguments.of("56 2"),
                Arguments.of("-2 23"),
                Arguments.of("45 52")
        );
    }

    public static Stream<Arguments> getValidLimitStringArgument(){
        return Stream.of(Arguments.of("50 2", 2, 50),
                Arguments.of("2 23", 23, 2),
                Arguments.of("45 50", 50, 45)
        );
    }

    public static Stream<Arguments> getInvalidArgumentsAsString(){
        return Stream.of(Arguments.of("562"),
                Arguments.of(""),
                Arguments.of("45 52 23")
        );
    }

}