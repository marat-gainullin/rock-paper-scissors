package org.challenge.rps.strategies;

import org.junit.Test;

/**
 *
 * @author mg
 */
public class NonUniformRangeStrategyTest extends StrategyTest {

    /**
     * Test of gain with non uniform range strategy.
     */
    @Test
    public final void gainTest() {
        gain(new NonUniformRangeStrategy());
    }

    /**
     * Test of penalty with non uniform range strategy.
     */
    @Test
    public final void penaltyTest() {
        penalty(new NonUniformRangeStrategy());
    }

    /**
     * Test of uniform with non uniform range strategy.
     */
    @Test
    public final void uniformTest() {
        uniform(new NonUniformRangeStrategy());
    }
}
