package org.challenge.rps.strategies;

import java.util.Arrays;
import java.util.Random;
import org.challenge.rps.Tool;

/**
 * Strategy for selecting of tools with thiers success in mind. Uses non uniform
 * mapping of [0; 1) interval to [0; <code>Tool.values().length - 1</code>]
 * interval. Every tool is mapped to subintervals of different wideness.
 *
 * @author mg
 */
public class NonUniformRangeStrategy implements Strategy {

    /**
     * Array of tools to select from.
     */
    private final Tool[] tools = Tool.values();
    /**
     * Success counters array. Success counters are shifted every time they are
     * changed. The minimum value of success is 1, so distribution coefficients
     * are always non zero.
     */
    private final int[] success = new int[tools.length];
    /**
     * Uniform random numbers generator.
     */
    private final Random uniform = new Random();

    /**
     * The strategy constructor. fills <code>success</code> array with 1.
     */
    public NonUniformRangeStrategy() {
        super();
        Arrays.fill(success, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void gain(final Tool aTool) {
        success[aTool.ordinal()] += 1;
        shift();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void penalty(final Tool aTool) {
        success[aTool.ordinal()] -= 1;
        shift();
    }

    /**
     * Shifts <code>success</code> by theirs minimum value and then shifts them
     * to the right by 1 to avoid zero success counters. It leads to equal and
     * min counter to be weighted uniformly.
     */
    private void shift() {
        int min = Integer.MAX_VALUE;
        for (int s : success) {
            if (s < min) {
                min = s;
            }
        }
        for (int i = 0; i < success.length; i++) {
            success[i] = success[i] - min + 1;
        }
    }

    /**
     * Maps a uniform random number from [0; 1) interval to [0;
     * <code>Tool.values().length - 1</code>] interval and selects a ntool for
     * the next round.
     */
    @Override
    public final Tool next() {
        int sum = 0;
        for (int s : success) {
            sum += s;
        }
        float u = uniform.nextFloat();
        // Default uniform selection based on random number
        Tool selected = tools[(int) Math.round(Math.floor(u * tools.length))];
        // Non-uniform selection based on weighted success
        float left = 0;
        for (int i = 0; i < success.length; i++) {
            float range = success[i] / (float) sum;
            float right = left + range;
            if (u >= left && u < right) {
                selected = tools[i];
            }
            left = right;
        }
        return selected;
    }

}
