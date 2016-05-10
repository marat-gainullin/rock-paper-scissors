package org.challenge.rps.strategies;

import org.challenge.rps.Tool;
import static org.junit.Assert.assertTrue;

/**
 * Base class for strategies tests.
 *
 * @author mg
 */
public abstract class StrategyTest {

    /**
     * Total number of cases for various tests.
     */
    private static final int TOTAL_CASES = 1000000;
    /**
     * Greastest gain vaue for various tests.
     */
    private static final int GREATEST_GAIN = 100;
    /**
     * Ordinary gain value for various tests.
     */
    private static final int ORDINARY_GAIN = 50;
    /**
     * Treshold used by various tests to ensure that more gained tools are
     * selected much more frequently.
     */
    private static final float GAIN_TRESHOLD = 0.1f;
    /**
     * Treshld used to ensure that uniformly gained tools are selected with
     * relativly equal frequency.
     */
    private static final float UNIFORM_TRESHOLD = 0.01f;

    /**
     * Test of gain with a strategy.
     *
     * @param aSubject A strategy the gain will be tested with.
     */
    protected final void gain(final Strategy aSubject) {
        int rockSuccess = GREATEST_GAIN;
        for (int s = 0; s < rockSuccess; s++) {
            aSubject.gain(Tool.ROCK);
        }
        int paperSuccess = ORDINARY_GAIN;
        for (int s = 0; s < paperSuccess; s++) {
            aSubject.gain(Tool.PAPER);
        }
        aSubject.gain(Tool.SCISSORS); // success = 1
        int totalCases = TOTAL_CASES;
        int rockCases = 0;
        int paperCases = 0;
        int scissCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (aSubject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissCases++;
                    break;
                default:
                    break;
            }
        }
        assertTrue(rockCases > paperCases);
        assertTrue(paperCases > scissCases);
        float rpDiff = Math.abs(rockCases - paperCases) / (float) totalCases;
        assertTrue(rpDiff > GAIN_TRESHOLD);
        float psDiff = Math.abs(paperCases - scissCases) / (float) totalCases;
        assertTrue(psDiff > GAIN_TRESHOLD);
    }

    /**
     * Test of penalty with a strategy.
     *
     * @param aSubject A strategy the penalty will be tested with.
     */
    protected final void penalty(final Strategy aSubject) {
        int rockSuccess = GREATEST_GAIN;
        for (int s = 0; s < rockSuccess; s++) {
            aSubject.penalty(Tool.ROCK);
        }
        int paperSuccess = ORDINARY_GAIN;
        for (int s = 0; s < paperSuccess; s++) {
            aSubject.penalty(Tool.PAPER);
        }
        aSubject.penalty(Tool.SCISSORS); // success = -1
        int totalCases = TOTAL_CASES;
        int rockCases = 0;
        int paperCases = 0;
        int scissCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (aSubject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissCases++;
                    break;
                default:
                    break;
            }
        }
        assertTrue(rockCases < paperCases);
        assertTrue(paperCases < scissCases);
        float rpDiff = Math.abs(rockCases - paperCases) / (float) totalCases;
        assertTrue(rpDiff > GAIN_TRESHOLD);
        float psDiff = Math.abs(paperCases - scissCases) / (float) totalCases;
        assertTrue(psDiff > GAIN_TRESHOLD);
    }

    /**
     * Test of uniform with a strategy.
     *
     * @param aSubject A strategy the uniform will be tested with.
     */
    protected final void uniform(final Strategy aSubject) {
        int totalCases = TOTAL_CASES;
        int rockCases = 0;
        int paperCases = 0;
        int scissCases = 0;
        for (int i = 0; i < totalCases; i++) {
            switch (aSubject.next()) {
                case ROCK:
                    rockCases++;
                    break;
                case PAPER:
                    paperCases++;
                    break;
                case SCISSORS:
                    scissCases++;
                    break;
                default:
                    break;
            }
        }
        float rpDiff = Math.abs(rockCases - paperCases) / (float) totalCases;
        float psDiff = Math.abs(paperCases - scissCases) / (float) totalCases;
        assertTrue(rpDiff < UNIFORM_TRESHOLD);
        assertTrue(psDiff < UNIFORM_TRESHOLD);
    }
}
