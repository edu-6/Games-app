/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.categorias;

/**
 *
 * @author edu
 */
public class EdicionCategoria {
    private String nombreAnterior;
    private String nuevoNombre;

    public String getNombreAnterior() {
        return nombreAnterior;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNombreAnterior(String nombreAnterior) {
        this.nombreAnterior = nombreAnterior;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public boolean valido() {
        return this.nombreAnterior != null && this.nuevoNombre != null
               && this.nombreAnterior.length() <= 50
               && this.nuevoNombre.length() <= 50;
    }
    
    
    
}
