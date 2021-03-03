package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhysicsTest {
    Physics physics;
    double tolerance = .00001;

    @Before
    public void setUp(){
        physics = new Physics();
    }

    @Test
    public void shouldReturnTheProductOfAnyTwoNumbers(){
        //This test tests calculateForce, and calculateDistance because they have the same formula

        assertEquals(12, physics.calculateForce(3.0, 4.0), tolerance);
        assertEquals(-15, physics.calculateForce(-3.0, 5.0), tolerance);
        assertEquals(-16, physics.calculateForce(4.0, -4.0), tolerance);
        assertEquals(0, physics.calculateForce(0.0, 4.0), tolerance);
        assertEquals(0, physics.calculateForce(3.0, 0.0), tolerance);

        assertEquals(12, physics.calculateDistance(3.0, 4.0), tolerance);
        assertEquals(-15, physics.calculateDistance(-3.0, 5.0), tolerance);
        assertEquals(-16, physics.calculateDistance(4.0, -4.0), tolerance);
        assertEquals(0, physics.calculateDistance(0.0, 4.0), tolerance);
        assertEquals(0, physics.calculateDistance(3.0, 0.0), tolerance);
    }

    @Test
    public void shouldReturnTheQuotientOfAnyTwoDifferences(){
        //This test tests calculateVelocity and CalculateAcceleration because they have the same formula.
        assertEquals(4, physics.calculateVelocity(3.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(-6, physics.calculateVelocity(3.0, -15.0, 2.0, 5.0), tolerance);
        assertEquals(6, physics.calculateVelocity(-3.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(2, physics.calculateVelocity(1.0, 15.0, -2.0, 5.0), tolerance);
        assertEquals(-2, physics.calculateVelocity(1.0, 15.0, 2.0, -5.0), tolerance);
        assertEquals(0, physics.calculateVelocity(15.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(0, physics.calculateVelocity(0.0, 0.0, 2.0, 5.0), tolerance);
        assertEquals(0, physics.calculateVelocity(15.0, 15.0, 2.0, -5.0), tolerance);
        assertEquals(0, physics.calculateVelocity(0.0, 0.0, 2.0, -5.0), tolerance);

        assertEquals(4, physics.calculateAcceleration(3.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(-6, physics.calculateAcceleration(3.0, -15.0, 2.0, 5.0), tolerance);
        assertEquals(6, physics.calculateAcceleration(-3.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(2, physics.calculateAcceleration(1.0, 15.0, -2.0, 5.0), tolerance);
        assertEquals(-2, physics.calculateAcceleration(1.0, 15.0, 2.0, -5.0), tolerance);
        assertEquals(0, physics.calculateAcceleration(15.0, 15.0, 2.0, 5.0), tolerance);
        assertEquals(0, physics.calculateAcceleration(0.0, 0.0, 2.0, 5.0), tolerance);
        assertEquals(0, physics.calculateAcceleration(15.0, 15.0, 2.0, -5.0), tolerance);
        assertEquals(0, physics.calculateAcceleration(0.0, 0.0, 2.0, -5.0), tolerance);


    }

    @Test
    public void shouldReturnTheQuotientOfTwoDifferencesWithAZeroParameterInDenominator(){
        //This test tests calculateVelocity and CalculateAcceleration because they have the same formula.

        assertEquals(3, physics.calculateVelocity(1.0, 16.0, 0.0, 5.0), tolerance);
        assertEquals(-8, physics.calculateVelocity(1.0, 17.0, 2.0, 0.0), tolerance);

        assertEquals(3, physics.calculateAcceleration(1.0, 16.0, 0.0, 5.0), tolerance);
        assertEquals(-8, physics.calculateAcceleration(1.0, 17.0, 2.0, 0.0), tolerance);
    }

    @Test
    public void shouldReturnTheQuotientOfTwoDifferencesWhenDenominatorIsZero(){
        //This test tests calculateVelocity and CalculateAcceleration because they have the same formula.

        assertEquals(Integer.MIN_VALUE, physics.calculateVelocity(15.0, 1.0, 5.0, 5.0), tolerance);
        assertEquals(Integer.MIN_VALUE, physics.calculateVelocity(15.0, 1.0, 0.0, 0.0), tolerance);

        assertEquals(Integer.MIN_VALUE, physics.calculateAcceleration(15.0, 1.0, 5.0, 5.0), tolerance);
        assertEquals(Integer.MIN_VALUE, physics.calculateAcceleration(15.0, 1.0, 0.0, 0.0), tolerance);
    }

    @Test
    public void shouldReturnTheQuotientOfAnyTwoNumbers(){
        assertEquals(42, physics.calculateMass(126.0, 3.0), tolerance);
        assertEquals(-42, physics.calculateMass(126.0, -3.0), tolerance);
        assertEquals(-42, physics.calculateMass(-126.0, 3.0), tolerance);
        assertEquals(0, physics.calculateMass(0.0, 3.0), tolerance);
    }

    @Test
    public void shouldReturnTheQuotientWhenDenominatorIsZero(){
        assertEquals(Integer.MIN_VALUE, physics.calculateMass(126, 0), tolerance);
    }



}
