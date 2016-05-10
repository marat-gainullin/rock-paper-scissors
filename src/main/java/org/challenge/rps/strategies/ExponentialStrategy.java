package org.challenge.rps.strategies;

import java.util.Arrays;
import java.util.Random;
import org.challenge.rps.Tool;

/**
 * Strategy for selecting of tools with thiers success in mind. Uses sorted
 * array of exponentioal.
 *
 * @author mg
 */
public class ExponentialStrategy implements Strategy {

    /**
     * Sorted array of tools' success counters.
     */
    private final int[] success;
    /**
     * Array of indicies in <code>success</code> array.
     */
    private final int[] indicies;
    /**
     * Array of tools, the strategy selects from.
     */
    private final Tool[] tools;
    /**
     * Uniformly distributed random numbers generator.
     */
    private final Random random = new Random();
    /**
     * Exponential distribution parameter.
     */
    private static final double LAMBDA = 4d;
    /**
     * Uniform treshold. Before number of <code>gainPenalty()</code> calls
     * increases and overcomes this treshold, simple uniform distribution is
     * used for tools selection.
     */
    private int treshold;

    /**
     * Default constructor of the strategy.
     */
    public ExponentialStrategy() {
        super();
        tools = Tool.values();
        treshold = tools.length;
        indicies = new int[tools.length];
        Arrays.setAll(indicies, (int anIndex) -> {
            return anIndex;
        });
        success = new int[tools.length];
    }

    /**
     * Swap method used to put <code>success</code> array element in right place
     * according to its success counter value.
     *
     * @param i One index has to be swapped.
     * @param j Another index has to be swapped.
     */
    private void swap(final int i, final int j) {
        Tool tmpTool = tools[i];
        tools[i] = tools[j];
        tools[j] = tmpTool;
        int tmpSuccess = success[i];
        success[i] = success[j];
        success[j] = tmpSuccess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void gain(final Tool aTool) {
        gainPenalty(aTool, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void penalty(final Tool aTool) {
        gainPenalty(aTool, 1);
    }

    /**
     * Increases or decreases success counter of the <code>aTool</code>.
     *
     * @param aTool A tool success of wich should be modified.
     * @param aValue A value by wich the tool success should be modified.
     */
    private void gainPenalty(final Tool aTool, final int aValue) {
        if (treshold > 0) {
            treshold--;
        }

        int oldIndex = indicies[aTool.ordinal()];
        success[oldIndex] += aValue;

        int newIndex;
        while (true) {
            if (oldIndex < success.length - 1
                    && success[oldIndex] > success[oldIndex + 1]) {
                newIndex = oldIndex + 1;
                Tool newTool = tools[newIndex];
                swap(oldIndex, newIndex);
                indicies[aTool.ordinal()] = newIndex;
                indicies[newTool.ordinal()] = oldIndex;
                oldIndex = newIndex;
            } else if (oldIndex > 0
                    && success[oldIndex] < success[oldIndex - 1]) {
                newIndex = oldIndex - 1;
                Tool newTool = tools[newIndex];
                swap(oldIndex, newIndex);
                indicies[aTool.ordinal()] = newIndex;
                indicies[newTool.ordinal()] = oldIndex;
                oldIndex = newIndex;
            } else {
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tool next() {
        if (treshold > 0) {
            return tools[random.nextInt(tools.length)];
        } else {
            // Uniform distribution
            double u = random.nextDouble();
            // Exponential distribution
            double e = -Math.log(1d - u) / LAMBDA;
            long tIndex = Math.round(e * tools.length);
            if (tIndex >= tools.length) {
                return tools[tools.length - 1];
            } else {
                return tools[(int) tIndex];
            }
        }
    }
}
