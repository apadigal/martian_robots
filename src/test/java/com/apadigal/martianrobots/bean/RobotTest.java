package com.apadigal.martianrobots.bean;

import com.apadigal.martianrobots.exception.MartianRobotsException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> {
           new Robot(positionString, strMovements);
        });
        assertEquals("Invalid Position data", martianRobotsException.getMessage());
    }
    @ParameterizedTest
    @MethodSource(value = "getInvalidMovementData")
    @Order(3)
    public void throwErrorWhenInvalidMovementDataSuppliedToConstruct(String strMovements){
        MartianRobotsException martianRobotsException = assertThrows(MartianRobotsException.class, () -> {
           new Robot("1 1 E",strMovements);
        });
        assertEquals("Invalid Movements data", martianRobotsException.getMessage());

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