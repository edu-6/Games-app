/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas.admin;

import java.sql.Date;

/**
 *
 * @author edu
 */
public class AdminEmpresaSimple {
    private String nombre;
    private String correo;

    public AdminEmpresaSimple(String nombre, String correo, Date fechaNacimiento) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public AdminEmpresaSimple() {
       
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
