/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.func;

import org.challenge.rps.Tool;
import org.challenge.rps.exceptions.InvalidNumberException;
import org.junit.Test;

/**
 *
 * @author mg
 */
public class ToolTest {

    @Test(expected = InvalidNumberException.class)
    public void asTest() {
        Tool.as().apply(Tool.values().length);
    }
}
