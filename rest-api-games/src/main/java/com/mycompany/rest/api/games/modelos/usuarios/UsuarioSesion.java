/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.usuarios;

/**
 *
 * @author edu      REPRESENTA UN USUARIO ENCONTRADO EN LA DB
 */
public class UsuarioSesion {
    private String correo;
    private String rol;

    public UsuarioSesion(String correo, String rol) {
        this.correo = correo;
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }
    
    
    
}
