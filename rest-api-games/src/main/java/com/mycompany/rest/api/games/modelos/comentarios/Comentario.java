/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.comentarios;

import com.mycompany.rest.api.games.dtos.comentarios.ComentarioRequest;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class Comentario {
    
    private String correoUsuario;
    private String nombreJuego;
    private String texto;
    private LocalDateTime fecha;
    private int idJuego;

    
    public Comentario(ComentarioRequest request) {
        this.correoUsuario = request.getCorreoUsuario();
        this.nombreJuego = request.getNombreJuego();
        this.texto = request.getTexto();
        this.fecha = request.getFecha();
    }
    
    public boolean valido() {
        return StringUtils.isNotBlank(correoUsuario)
                && StringUtils.isNotBlank(texto)
                && texto.length() <= 500
                && nombreJuego != null
                && fecha != null;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }
    
    public String getTexto() {
        return texto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
    
}
