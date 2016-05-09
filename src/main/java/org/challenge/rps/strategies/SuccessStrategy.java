/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.strategies;

import java.util.Arrays;
import java.util.Random;
import org.challenge.rps.Tool;

/**
 *
 * @author mg
 */
public class SuccessStrategy implements Strategy {

    private final int[] success;
    private final int[] indicies;
    private final Tool[] tools;
    private final Random random = new Random();
    private static final double LAMBDA = 4d;
    private int treshold = Tool.count();

    public SuccessStrategy() {
        super();
        tools = Tool.values();
        indicies = new int[tools.length];
        Arrays.setAll(indicies, (int anIndex) -> {
            return anIndex;
        });
        success = new int[tools.length];
    }

    private void swap(int i, int j) {
        Tool tmpTool = tools[i];
        tools[i] = tools[j];
        tools[j] = tmpTool;
        int tmpSuccess = success[i];
        success[i] = success[j];
        success[j] = tmpSuccess;
    }

    @Override
    public void used(Tool aTool, boolean aSuccess) {
        if (treshold > 0) {
            treshold--;
        }

        int oldIndex = indicies[aTool.ordinal()];
        success[oldIndex] += aSuccess ? -1 : 1;

        int newIndex;
        while (true) {
            if (oldIndex < success.length - 1 && success[oldIndex] > success[oldIndex + 1]) {
                newIndex = oldIndex + 1;
                Tool newTool = tools[newIndex];
                swap(oldIndex, newIndex);
                indicies[aTool.ordinal()] = newIndex;
                indicies[newTool.ordinal()] = oldIndex;
                oldIndex = newIndex;
            } else if (oldIndex > 0 && success[oldIndex] < success[oldIndex - 1]) {
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

    @Override
    public Tool next() {
        if (treshold > 0) {
            return tools[random.nextInt(tools.length)];
        } else {
            // Uniform distribution
            double u = random.nextDouble();
            // Exponential distribution
            double e = -Math.log(1d - u) / LAMBDA;
            long tIndex = Math.round(e * tools.length);
            return tools[tIndex >= tools.length ? tools.length - 1 : (int) tIndex];
        }
    }
}
