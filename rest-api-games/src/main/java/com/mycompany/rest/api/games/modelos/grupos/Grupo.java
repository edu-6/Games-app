/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.grupos;

import com.mycompany.rest.api.games.modelos.EntidadBackend;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class Grupo extends EntidadBackend {

    protected String nombre;
    protected String correoAdmin;

    public Grupo() {
    }
    
    

    public Grupo(String nombre, String correoAdmin) {
        this.nombre = nombre;
        this.correoAdmin = correoAdmin;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    @Override
    public boolean valido() {
        return StringUtils.isNotBlank(nombre) 
                && StringUtils.isNotBlank(correoAdmin)
                && nombre.length() <= 100
                && correoAdmin.length() <= 100;
    }
    
}
