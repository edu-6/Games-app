/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.comentarios;

import com.mycompany.rest.api.games.dtos.comentarios.SubcomentarioRequest;
import java.time.LocalDateTime;

/**
 *
 * @author edu
 */
public class Subcomentario {
    private int idComentarioPadre;
    private String correoUsuario;
    private String texto;
    private LocalDateTime fecha;
    
    
    public Subcomentario(SubcomentarioRequest request) {
        this.idComentarioPadre = request.getIdComentarioPadre();
        this.correoUsuario = request.getCorreoUsuario();
        this.texto = request.getTexto();
        this.fecha = request.getFecha();
    }
    
    
    public boolean valido() {
        return idComentarioPadre > 0 &&
               correoUsuario != null && !correoUsuario.trim().isEmpty() &&
               texto != null && !texto.trim().isEmpty() && texto.length() <= 500 &&
               fecha != null;
    }

    public int getIdComentarioPadre() {
        return idComentarioPadre;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setIdComentarioPadre(int idComentarioPadre) {
        this.idComentarioPadre = idComentarioPadre;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
    
}
