/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.adminSistema;

import org.apache.commons.lang3.StringUtils;

/**
 * @author edu
 */
public class AdminSistema {
    private String nombre;
    private String correo;
    private String contraseña;
    
    public AdminSistema() {
    }

    public AdminSistema(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }
    
    public String getNombre() {
        return nombre;
    }


    public String getCorreo() {
        return correo;
    }


    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo=correo;
    }
    
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    
    public boolean valido(){
        return StringUtils.isNotBlank(nombre)
                && StringUtils.isNotBlank(correo)
                && StringUtils.isNotBlank(contraseña)
                && nombre.length() <= 100
                && correo.length() <= 50 
                && contraseña.length() <= 100
                && correo != null
                && nombre != null
                && contraseña != null;
    }
}
