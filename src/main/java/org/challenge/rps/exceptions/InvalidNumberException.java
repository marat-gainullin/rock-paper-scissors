/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.exceptions;

/**
 * Exception for ConsoleUtils reading methods.
 * @author mg
 * @see ConsoleUtils#
 */
public class InvalidNumberException extends RuntimeException {

    private final int number;

    public InvalidNumberException(int aNumber) {
        super();
        number = aNumber;
    }

    public int getNumber() {
        return number;
    }

}
