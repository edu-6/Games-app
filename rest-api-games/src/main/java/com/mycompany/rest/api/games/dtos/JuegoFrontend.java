/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos;

import com.mycompany.rest.api.games.modelos.Entidad;

/**
 *
 * @author edu
 */
public class JuegoFrontend extends Entidad {
    private String nombre;
    private String clasificacion;
    private String descripcion;
    private String requerimientos;
    private Double precio;
    private boolean activo;
    private boolean permiteComentarios;
    private String correoCreador;

    public String getClasificacion() {
        return clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public Double getPrecio() {
        return precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isPermiteComentarios() {
        return permiteComentarios;
    }

    public String getCorreoCreador() {
        return correoCreador;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setPermiteComentarios(boolean permiteComentarios) {
        this.permiteComentarios = permiteComentarios;
    }

    public void setCorreoCreador(String correoCreador) {
        this.correoCreador = correoCreador;
    }
    
    
    
}
