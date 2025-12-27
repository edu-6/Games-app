/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.juegos;

/**
 *
 * @author edu
 */
public class BusquedaJuego {
    private String nombre;
    private double precioMaximo;
    private double precioMinimo;
    private String categoria;
    private String correoAdmin;

    public String getNombre() {
        return nombre;
    }

    public double getPrecioMaximo() {
        return precioMaximo;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }
    
    
    
    
    
    
}
