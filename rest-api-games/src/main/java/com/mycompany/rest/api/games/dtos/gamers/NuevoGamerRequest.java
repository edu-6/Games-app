/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.gamers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class NuevoGamerRequest {
    
    private String nombre;
    private String nombrePais;
    private String correo;
    private String nickname;
    private String contrasena;
    private int telefono;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
}
