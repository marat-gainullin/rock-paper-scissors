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

    private int[] success;
    private int[] indicies;
    private Random uniForm = new Random();
    private static final double LAMBDA = 0.5d;

    public Strategy() {
        super();
        success = new int[Tool.count()];
        indicies = new int[success.length];
        Arrays.setAll(indicies, (int anIndex) -> {
            return anIndex;
        });
    }

    private void swap(int i, int j) {
        indicies[i] = j;
        indicies[j] = i;
        int tmp = success[i];
        success[i] = success[j];
        success[j] = tmp;
    }

    public void inc(Tool aTool, boolean aSuccess) {
        int oldIndex = indicies[aTool.ordinal()];
        success[oldIndex] += aSuccess ? 1 : -1;

        int newIndex = oldIndex;
        do {
            if (oldIndex  < success.length - 1 && success[oldIndex] > success[oldIndex + 1]) {
                newIndex = oldIndex + 1;
                swap(oldIndex, newIndex);
            } else if (oldIndex > 0 && success[oldIndex] < success[oldIndex - 1]) {
                newIndex = oldIndex - 1;
                swap(oldIndex, newIndex);
            }
        } while (newIndex != oldIndex);
    }
    
    public Tool next(){
        // Exponential distribution
        double expForm = Math.log(1f - uniForm.nextDouble())/-LAMBDA;
        long tIndex = Math.round(expForm * Tool.count());
        return Tool.at(tIndex >= Tool.count() ? Tool.count() - 1 : (int)tIndex);
    }
}
