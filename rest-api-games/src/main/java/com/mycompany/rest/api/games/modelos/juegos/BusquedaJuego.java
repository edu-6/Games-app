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
    private int precioMaximo;
    private int precioMinimo;
    private String categoria;
    private String correoAdmin; // sirve para filtar los juegos en base a empresa con el correo del admin

    public BusquedaJuego() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioMaximo(int precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public void setPrecioMinimo(int precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecioMaximo() {
        return precioMaximo;
    }

    public int getPrecioMinimo() {
        return precioMinimo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }
    
    
    public boolean valido(){
        return false;
    }


    
}
