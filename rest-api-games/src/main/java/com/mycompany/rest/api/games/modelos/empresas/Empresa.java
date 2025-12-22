/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class Empresa {

    private String nombre;
    private boolean aceptaComentarios;

    public Empresa(String nombre, boolean aceptaComentarios) {
        this.nombre = nombre;
        this.aceptaComentarios = aceptaComentarios;
    }

    public Empresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAceptaComentarios() {
        return aceptaComentarios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAceptaComentarios(boolean aceptaComentarios) {
        this.aceptaComentarios = aceptaComentarios;
    }

    public boolean valido() {
        return this.nombre != null
                && StringUtils.isNotBlank(nombre)
                &&  nombre.length()  <= 40;
    }

}
