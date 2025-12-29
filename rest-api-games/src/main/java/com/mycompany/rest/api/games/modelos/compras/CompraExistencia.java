/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.compras;

/**
 *
 * @author edu
 */
public class CompraExistencia {
    private String correo;
    private String nombreJuego;

    public CompraExistencia() {
    }

    public CompraExistencia(String correo, String nombreJuego) {
        this.correo = correo;
        this.nombreJuego = nombreJuego;
    }
    
    

    public String getCorreo() {
        return correo;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }
        
}
