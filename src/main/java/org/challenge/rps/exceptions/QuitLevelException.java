/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.exceptions;

/**
 *
 * @author mg
 */
public class QuitLevelException extends RuntimeException {

    public QuitLevelException(String anInput) {
        super(anInput);
    }

}
