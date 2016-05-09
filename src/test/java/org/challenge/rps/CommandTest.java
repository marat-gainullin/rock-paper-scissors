/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps;

import org.challenge.rps.Command;
import org.challenge.rps.exceptions.InvalidNumberException;
import org.junit.Test;

/**
 *
 * @author mg
 */
public class CommandTest {

    @Test(expected = InvalidNumberException.class)
    public void asTest() {
        Command.as().apply(Command.values().length);
    }
}