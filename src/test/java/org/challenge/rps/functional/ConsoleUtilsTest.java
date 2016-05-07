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
import org.challenge.rps.Console;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Console.
 *
 * @author mg
 */
public class ConsoleUtilsTest {

    /**
     * Tests wether correctg and known from leads to exepected command
 creation.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void correctKnownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("0\n")) {
            assertSame(Command.COMP_COMP, Console.from(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests wether incorrect from is overcomed, and known command is created.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void incorrectInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("blah blah blah\n0\n")) {
            assertSame(Command.COMP_COMP, Console.from(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests wether correct, but unknown from is overcomed, and known command
 is created.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void unknownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("10\n0\n")) {
            assertSame(Command.COMP_COMP, Console.from(in, System.out, "", Command.as()).get());
        }
    }

    /**
     * Tests reaction of <code>Console.from</code> on an exception while
 text from.
     *
     * @throws UnsupportedEncodingException
     */
    @Test(expected = IllegalStateException.class)
    public void interruptedInput() throws UnsupportedEncodingException {
        Integer expectedInput = 2;
        try (Scanner in = new Scanner(expectedInput.toString())) {
            assertNull(Console.from(in, System.out, "", (Integer aOrdinal) -> {
                assertEquals(expectedInput, aOrdinal);
                throw new IllegalStateException("Test input is interrupted.");
            }).get());
        }
    }

    /**
     * Tests reaction of <code>Console.from</code> on an empty text
 from.
     *
     * @throws UnsupportedEncodingException
     */
    @Test(expected = NoSuchElementException.class)
    public void emptyInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("")) {
            Console.from(in, System.out, "", null);
        }
    }
}
