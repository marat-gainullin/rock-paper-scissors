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
public class NonUniformRangeStrategy implements Strategy {

    private final Tool[] tools = Tool.values();
    private final int[] success = new int[tools.length];
    private final Random uniform = new Random();

    public NonUniformRangeStrategy() {
        super();
        Arrays.fill(success, 1);
    }

    @Override
    public void gain(Tool aTool) {
        success[aTool.ordinal()] += 1;
        shift();
    }

    @Override
    public void penalty(Tool aTool) {
        success[aTool.ordinal()] -= 1;
        shift();
    }

    private void shift() {
        int min = Integer.MAX_VALUE;
        for (int s : success) {
            min = s < min ? s : min;
        }
        for (int i = 0; i < success.length; i++) {
            success[i] = success[i] - min + 1; // 1 shift leads to equal and min counter to be weighted uniformly
        }
    }

    @Override
    public Tool next() {
        int sum = 0;
        for (int s : success) {
            sum += s;
        }
        float u = uniform.nextFloat();
        // Default uniform selection based on random number
        Tool selected = tools[(int)Math.round(Math.floor(u * tools.length))];
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
