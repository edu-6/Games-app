/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mycompany.rest.api.games.modelos.juegos.Juego;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class JuegoResponse extends JuegoFrontend {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fechaLanzamiento;

    public JuegoResponse(Juego juego) {
        this.nombre = juego.getNombre();
        this.clasificacion = juego.getClasificacion();
        this.descripcion = juego.getDescripcion();
        this.precio = juego.getPrecio();
        this.fechaLanzamiento = juego.getFechaLanzamiento();
        this.permiteComentarios = juego.isPermiteComentarios();
        this.activo =  juego.isActivo();
        this.requerimientos = juego.getRequerimientos();
    }
    
    

}
