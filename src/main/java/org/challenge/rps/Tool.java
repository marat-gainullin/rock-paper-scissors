/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

/**
 *
 * @author mg
 */
public enum Tool {
    ROCK(1, "Rock"),
    PAPER(2, "Paper"),
    SCISSORS(3, "Scissors");

    private final int id;
    private final String name;

    private Tool(int aId, String aName) {
        id = aId;
        name = aName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Tool as(int aId) {
        for (Tool tool : Tool.values()) {
            if (tool.getId() == aId) {
                return tool;
            }
        }
        return null;
    }

}
