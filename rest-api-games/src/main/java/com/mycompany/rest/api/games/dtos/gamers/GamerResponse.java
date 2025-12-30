/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.gamers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.rest.api.games.modelos.gamers.Gamer;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class GamerResponse extends GamerDTOSimple {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaNacimiento;

    public GamerResponse(Gamer gamer) {
        this.nombre = gamer.getNombre();
        this.contrasena = gamer.getConstraseña();
        this.correo = gamer.getCorreo();
        this.telefono= gamer.getTelefono();
        this.nickname = gamer.getNickname();
        this.fechaNacimiento = gamer.getFechaNacimiento();
        this.nombrePais = gamer.getNombrePais();
    }
    
    

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
