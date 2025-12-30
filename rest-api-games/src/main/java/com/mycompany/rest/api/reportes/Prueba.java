/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.reportes;

/**
 *
 * @author edu
 */
public class Prueba {
    
    private int precio;
    private String nombre;
    private String parametro;

    public Prueba(int precio, String nombre, String parametro) {
        this.precio = precio;
        this.nombre = nombre;
        this.parametro = parametro;
    }
    
    
    

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getParametro() {
        return parametro;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
}
