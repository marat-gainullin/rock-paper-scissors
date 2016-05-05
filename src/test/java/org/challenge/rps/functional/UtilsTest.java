/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.functional;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.challenge.rps.Command;
import org.challenge.rps.Utils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mg
 */
public class UtilsTest {

    @Test
    public void correctInput() throws UnsupportedEncodingException {
        Integer expectedInput = 0;
        assertSame(Command.EXIT, Utils.nextOrdinal(new Scanner(new ByteArrayInputStream(expectedInput.toString().getBytes(Utils.UTF8)), Utils.UTF8), (Integer aOrdinal) -> {
            assertEquals(expectedInput, aOrdinal);
            return Command.EXIT;
        }));
    }

    @Test
    public void incorrectInput() throws UnsupportedEncodingException {
        assertSame(Command.EXIT, Utils.nextOrdinal(new Scanner(new ByteArrayInputStream("blah blah blah\n0".getBytes(Utils.UTF8)), Utils.UTF8), (Integer aOrdinal) -> {
            return Command.EXIT;
        }));
    }

    @Test(expected = IllegalStateException.class)
    public void interruptedInput() throws UnsupportedEncodingException {
        Integer expectedInput = 2;
        assertNull(Utils.nextOrdinal(new Scanner(new ByteArrayInputStream(expectedInput.toString().getBytes(Utils.UTF8)), Utils.UTF8), (Integer aOrdinal) -> {
            assertEquals(expectedInput, aOrdinal);
            throw new IllegalStateException("Test input is interrupted.");
        }));
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyInput() throws UnsupportedEncodingException {
        Utils.nextOrdinal(new Scanner(new ByteArrayInputStream(new byte[]{}), Utils.UTF8), null);
    }
}
