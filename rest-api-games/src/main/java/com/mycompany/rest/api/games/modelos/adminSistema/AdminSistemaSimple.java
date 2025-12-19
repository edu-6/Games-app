/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.adminSistema;

import org.apache.commons.lang3.StringUtils;

/**
 * @author edu   esta versión sirve para  no regresar contraseñas
 */
public class AdminSistemaSimple {
    private String nombre;
    private String correo;
    
    public AdminSistemaSimple() {
    }

    public AdminSistemaSimple(String nombre, String correo) {
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
