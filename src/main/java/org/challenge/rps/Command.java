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
public enum Command {

    EXIT(1, "Exit"),
    COMP_COMP(2, "Computer vs. Computer"),
    PLAYER_COMP(3, "Player vs. Computer");

    private final int id;
    private final String name;

    private Command(int aId, String aName) {
        id = aId;
        name = aName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Command as(int aId) {
        for (Command cmd : Command.values()) {
            if (cmd.getId() == aId) {
                return cmd;
            }
        }
        return null;
    }

}
