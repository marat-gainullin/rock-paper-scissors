/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.challenge.rps.strategies;

import org.challenge.rps.Tool;

/**
 *
 * @author mg
 */
public interface Strategy {

    public void used(Tool aTool, boolean aSuccess);
    
    public Tool next();
}
