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
    private String correoUsuario;
    private String nombreJuego;

    public CompraExistencia() {
    }

    public CompraExistencia(String correoUsuario, String nombreJuego) {
        this.correoUsuario = correoUsuario;
        this.nombreJuego = nombreJuego;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }
        
}
