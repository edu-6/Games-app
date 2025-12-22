/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas;

/**
 *
 * @author edu
 */
public class EmpresaEdicion extends Empresa {
    private String nuevoNombre;

    public EmpresaEdicion(String nuevoNombre, String nombre, boolean aceptaComentarios) {
        super(nombre, aceptaComentarios);
        this.nuevoNombre = nuevoNombre;
    }

    public EmpresaEdicion() {
    }
    

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAceptaComentarios() {
        return aceptaComentarios;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAceptaComentarios(boolean aceptaComentarios) {
        this.aceptaComentarios = aceptaComentarios;
    }
}
