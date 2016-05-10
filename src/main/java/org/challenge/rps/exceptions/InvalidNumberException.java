/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.exceptions;

/**
 * Exception for Console reading methods.
 *
 * @author mg
 */
public class InvalidNumberException extends RuntimeException {

    /**
     * The number, that is unrecognized.
     */
    private final int number;

    /**
     * The <code>InvalidNumberException</code> constructor with a problem
     * number.
     *
     * @param aNumber The unrecognized number.
     */
    public InvalidNumberException(final int aNumber) {
        super();
        number = aNumber;
    }

    /**
     * Unrecognized number getter.
     *
     * @return The unrecognized number, this exception is about.
     */
    public final int getNumber() {
        return number;
    }

}
