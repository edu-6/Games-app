/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.exceptions;

/**
 *
 * @author edu
 */
public class MenorDeEdadException extends Exception{

    public MenorDeEdadException() {
        super(" eres menor de edad, no puedes realizar la compra");
    }
    
}
