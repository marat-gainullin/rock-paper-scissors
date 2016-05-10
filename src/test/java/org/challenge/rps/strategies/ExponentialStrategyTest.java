package org.challenge.rps.strategies;

import org.junit.Test;

/**
 * Tests for <code>ExponentialStrategy</code> class.
 * @author mg
 */
public class ExponentialStrategyTest extends StrategyTest {

    /**
     * Test of gain with exponential strategy.
     */
    @Test
    public final void gainTest() {
        gain(new ExponentialStrategy());
    }

    /**
     * Test of penalty with exponential strategy.
     */
    @Test
    public final void penaltyTest() {
        penalty(new ExponentialStrategy());
    }

    /**
     * Test of uniform with exponential strategy.
     */
    @Test
    public final void uniformTest() {
        uniform(new ExponentialStrategy());
    }

}
