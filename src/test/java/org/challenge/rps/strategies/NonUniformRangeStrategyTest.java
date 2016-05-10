/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.strategies;

import org.junit.Test;

/**
 *
 * @author mg
 */
public class NonUniformRangeStrategyTest extends StrategyTest {

    @Test
    public void gainTest() {
        gain(new NonUniformRangeStrategy());
    }

    @Test
    public void penaltyTest() {
        penalty(new NonUniformRangeStrategy());
    }

    @Test
    public void uniformTest() {
        uniform(new NonUniformRangeStrategy());
    }
}
