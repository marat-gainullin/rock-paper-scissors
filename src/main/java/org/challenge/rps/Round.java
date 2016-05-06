/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import java.util.Optional;

/**
 * History entry for made rounds.
 *
 * @author mg
 */
public class Round {

    private final Tool player1Tool;
    private final Tool player2Tool;

    public Round(Tool aPlayer1Tool, Tool aPlayer2Tool) {
        player1Tool = aPlayer1Tool;
        player2Tool = aPlayer2Tool;
    }

    public Optional<Tool> winner() {
        return Optional.ofNullable(player1Tool == player2Tool ? null : player1Tool.ordinal() - player2Tool.ordinal() == -1 || player1Tool.ordinal() - player2Tool.ordinal() == 2 ? player1Tool : player2Tool);
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append(player1Tool.getName()).append(" - ").append(player2Tool.getName()).append(".");
        return content.toString();
    }

}
