/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author mg
 */
public class Strategy {

    private final int[] success;
    private final int[] indicies;
    private final Tool[] tools;
    private Random uniForm = new Random();
    private static final double LAMBDA = 0.5d;

    public Strategy() {
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

    public void inc(Tool aTool, boolean aSuccess) {
        int oldIndex = indicies[aTool.ordinal()];
        success[oldIndex] += aSuccess ? 1 : -1;

        int newIndex = oldIndex;
        do {
            if (oldIndex < success.length - 1 && success[oldIndex] > success[oldIndex + 1]) {
                newIndex = oldIndex + 1;
                Tool newTool = tools[newIndex];
                swap(oldIndex, newIndex);
                indicies[aTool.ordinal()] = newIndex;
                indicies[newTool.ordinal()] = oldIndex;
            } else if (oldIndex > 0 && success[oldIndex] < success[oldIndex - 1]) {
                newIndex = oldIndex - 1;
                Tool newTool = tools[newIndex];
                swap(oldIndex, newIndex);
                indicies[aTool.ordinal()] = newIndex;
                indicies[newTool.ordinal()] = oldIndex;
            }
        } while (newIndex != oldIndex);
    }

    public Tool next() {
        // Exponential distribution
        double expForm = Math.log(1d - uniForm.nextDouble()) / -LAMBDA;
        long tIndex = Math.round(expForm * tools.length);
        return tools[tIndex >= tools.length ? tools.length - 1 : (int) tIndex];
    }
}
