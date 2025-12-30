/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.gamers;

/**
 *
 * @author edu
 */
public class GamerSimple {
    private String nombre;
    private String correo;

    public GamerSimple(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
