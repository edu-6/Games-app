/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.mycompany.rest.api.games.modelos.Entidad;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class UsuarioResponse extends Entidad {
    // Representa los datos minimos que se muestran al buscar a un usuario
    
    protected String nombre;
    protected String correo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String nombre, String correo, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    protected LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    
}
