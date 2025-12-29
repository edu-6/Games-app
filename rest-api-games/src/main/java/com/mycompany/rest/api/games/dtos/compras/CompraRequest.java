/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.compras;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class CompraRequest{
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaCompra;
    private String nombreJuego;
    private String correoUsuario;

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
    
    
}
