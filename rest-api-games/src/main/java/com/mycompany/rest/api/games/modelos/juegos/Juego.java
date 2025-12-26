/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.juegos;

import com.mycompany.rest.api.games.modelos.EntidadBackend;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class Juego extends EntidadBackend {

    private int id;
    private String nombre;
    private String clasificacion;
    private String descripcion;
    private String requerimientos;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private boolean activo;
    private boolean permiteComentarios;
    private int codigoEmpresa;

    public Juego() {

    }

    public Juego(int id, String nombre, String clasificacion, String descripcion, String requerimientos, Double precio, LocalDate fechaLanzamiento, boolean activo, boolean permiteComentarios, int codigoEmpresa) {
        this.id = id;
        this.nombre = nombre;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
        this.requerimientos = requerimientos;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
        this.activo = activo;
        this.permiteComentarios = permiteComentarios;
        this.codigoEmpresa = codigoEmpresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setPermiteComentarios(boolean permiteComentarios) {
        this.permiteComentarios = permiteComentarios;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public Double getPrecio() {
        return precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean isPermiteComentarios() {
        return permiteComentarios;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    @Override
    public boolean valido() {
        return this.nombre != null
                && this.clasificacion != null
                && this.descripcion != null
                && this.requerimientos != null
                && this.precio != null
                && this.fechaLanzamiento != null
                && this.codigoEmpresa > 0
                && StringUtils.isNotBlank(this.nombre)
                && StringUtils.isNotBlank(this.clasificacion)
                && StringUtils.isNotBlank(this.descripcion)
                && this.nombre.length() <= 100
                && this.clasificacion.length() <= 20
                && this.descripcion.length() <= 200
                && this.requerimientos.length() <= 200;
    }
}
