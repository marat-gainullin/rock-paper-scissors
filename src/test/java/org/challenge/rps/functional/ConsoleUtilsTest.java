/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.functional;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.challenge.rps.Command;
import org.challenge.rps.ConsoleUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ConsoleUtils.
 *
 * @author mg
 */
public class ConsoleUtilsTest {

    /**
     * Tests wether correctg and known input leads to exepected command
     * creation.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void correctKnownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("0\n")) {
            assertSame(Command.COMP_COMP, ConsoleUtils.nextId(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests wether incorrect input is overcomed, and known command is created.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void incorrectInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("blah blah blah\n0\n")) {
            assertSame(Command.COMP_COMP, ConsoleUtils.nextId(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests wether correct, but unknown input is overcomed, and known command
     * is created.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void unknownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("10\n0\n")) {
            assertSame(Command.COMP_COMP, ConsoleUtils.nextId(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests reaction of <code>ConsoleUtils.nextId</code> on an exception while
     * text input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test(expected = IllegalStateException.class)
    public void interruptedInput() throws UnsupportedEncodingException {
        Integer expectedInput = 2;
        try (Scanner in = new Scanner(expectedInput.toString())) {
            assertNull(ConsoleUtils.nextId(in, System.out, "", (Integer aOrdinal) -> {
                assertEquals(expectedInput, aOrdinal);
                throw new IllegalStateException("Test input is interrupted.");
            }).get());
        }
    }

    /**
     * Tests reaction of <code>ConsoleUtils.nextId</code> on an empty text
     * input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test(expected = NoSuchElementException.class)
    public void emptyInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("")) {
            ConsoleUtils.nextId(in, System.out, "", null);
        }
    }
}
