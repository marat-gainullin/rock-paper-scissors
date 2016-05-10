package org.challenge.rps;

import org.challenge.rps.exceptions.InvalidNumberException;
import org.junit.Test;

/**
 * Tests for <code>Tool</code> enum.
 * @author mg
 */
public class ToolTest {

    /**
     * Tests for <code>Tool.as()</code> predicate. Tests wrong number outcome.
     */
    @Test(expected = InvalidNumberException.class)
    public final void asTest() {
        Tool.as().apply(Tool.values().length);
    }
}
