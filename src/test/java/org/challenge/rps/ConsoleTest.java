package org.challenge.rps;

import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Tests for Console.
 *
 * @author mg
 */
public class ConsoleTest {

    /**
     * Tests weither correctg and known from leads to exepected command
     * creation.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test
    public final void correctKnownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("1\n")) {
            assertSame(Mode.COMP, Console.from(
                    in, System.out,
                    "", Mode.as()).get());
        }
    }

    /**
     * Tests wether incorrect from is overcomed, and known command is created.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test
    public final void incorrectInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("blah blah blah\n1\n")) {
            assertSame(Mode.COMP, Console.from(
                    in, System.out, "",
                    Mode.as()).get());
        }
    }

    /**
     * Tests wether correct, but unknown from is overcomed, and known command is
     * created.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test
    public final void unknownInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("10\n1\n")) {
            assertSame(Mode.COMP, Console.from(in, System.out,
                    "", Mode.as()).get());
        }
    }

    /**
     * Tests reaction of <code>Console.from</code> on an exception while text
     * from.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test(expected = IllegalStateException.class)
    public final void interruptedInput() throws UnsupportedEncodingException {
        String expectedInput = "2";
        try (Scanner in = new Scanner(expectedInput)) {
            assertNull(Console.from(in, System.out, "", (String aText) -> {
                assertEquals(expectedInput, aText);
                throw new IllegalStateException("Test input is interrupted.");
            }).get());
        }
    }

    /**
     * Tests reaction of <code>Console.from</code> on an empty text from.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test(expected = NoSuchElementException.class)
    public final void emptyInput() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("")) {
            Console.from(in, System.out, "", null);
        }
    }

    /**
     * Tests reaction of <code>Console.from</code> on an empty text from.
     *
     * @throws UnsupportedEncodingException From <code>Console.from()</code>
     * @see Console#from(java.util.Scanner, java.io.PrintStream,
     * java.lang.String, java.util.function.Function)
     */
    @Test(expected = NoSuchElementException.class)
    public final void emptyInputWithLf() throws UnsupportedEncodingException {
        try (Scanner in = new Scanner("\n")) {
            Console.from(in, System.out, "", Mode.as());
        }
    }

    /**
     * Tests colorful output feature.
     */
    @Test
    public final void colorful() {
        Round round = new Round(Tool.PAPER, Tool.SCISSORS);
        String colored = Console.to(round, true);
        assertEquals("[32mPaper[0m - [36mScissors[0m.", colored);
    }
}
