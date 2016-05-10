/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Optional;

/**
 * Round of a game session.
 *
 * @author mg
 */
public class Round {

    /**
     * First player tool for this round;
     */
    private final Tool firstPlayerTool;
    /**
     * Second player tool for this round;
     */
    private final Tool secondPlayerTool;

    /**
     * Constructs a round with a pair of tools, selected by players.
     *
     * @param aFirstPlayerTool A tool, selected by a first player.
     * @param aSecondPlayerTool A tool, selected by a second player.
     */
    public Round(final Tool aFirstPlayerTool, final Tool aSecondPlayerTool) {
        firstPlayerTool = aFirstPlayerTool;
        secondPlayerTool = aSecondPlayerTool;
    }

    /**
     * Getter method for first player's tool.
     * @return First player's tool.
     */
    public final Tool getFirstPlayerTool() {
        return firstPlayerTool;
    }

    /**
     * Getter method for second player's tool.
     * @return Second player's tool.
     */
    public final Tool getSecondPlayerTool() {
        return secondPlayerTool;
    }

    /**
     * Detemines a winner of this round. In case of tools(weapons) set
     * extension, this method should be re-implemented.
     *
     * @return A winner of this round.
     */
    public Optional<Tool> winner() {
        return firstPlayerTool == secondPlayerTool ? Optional.empty() : Optional.of(firstPlayerTool.ordinal() - secondPlayerTool.ordinal() == -1 || firstPlayerTool.ordinal() - secondPlayerTool.ordinal() == 2 ? firstPlayerTool : secondPlayerTool);
    }

}
