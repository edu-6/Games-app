/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.usuarios;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class UsuarioSimple {
    private String correo;
    private String nombre;
    
    // esta clase sirve para representar un usuario simple con nombre y correo
    
    public UsuarioSimple() {
    }

    public UsuarioSimple(String nombre, String correo) {
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
        this.correo=correo;
    }
    
    
    public boolean valido(){
        return StringUtils.isNotBlank(nombre)
                && StringUtils.isNotBlank(correo)
                && nombre.length() <= 100
                && correo.length() <= 50 
                && correo != null
                && nombre != null;
    }
    
}
