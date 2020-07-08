package com.apadigal.martianrobots;

import com.apadigal.martianrobots.bean.MarsLimits;
import com.apadigal.martianrobots.bean.Robot;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PlanetMarsTest {


    @Test
    public void constructorValid(){
        MarsLimits marsLimits = new MarsLimits(2, 3);

        PlanetMars planetMars = new PlanetMars(marsLimits, Collections.EMPTY_LIST);
        assertNotNull(planetMars);
    }

    @Test
    public void successMarsMissionWhenValidPlantMars(){
        MarsLimits marsLimits = new MarsLimits(2, 3);
        PlanetMars planetMars = new PlanetMars(marsLimits, Collections.EMPTY_LIST);
        assertTrue(planetMars.startMission());
        assertEquals("", planetMars.toString());
    }

    @Test
    public void successMarsMissionWhenValidRobotList(){
        MarsLimits marsLimits = new MarsLimits(3, 5);
        PlanetMars planetMars = new PlanetMars(marsLimits, Arrays.asList(new Robot("2 2 E", "RFRFRFRF"),
                new Robot("3 2 N", "FRRFLLFFRRFLL")));
        assertTrue(planetMars.startMission());
        assertEquals("2 2 E\n3 3 N LOST", planetMars.toString());
    }

}