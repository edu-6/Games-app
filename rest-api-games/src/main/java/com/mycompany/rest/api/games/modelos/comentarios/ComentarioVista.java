/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.comentarios;

import java.time.LocalDateTime;

/**
 *
 * @author edu
 */
public class ComentarioVista {
    private int idComentario;
    private String nickname;
    private String texto;
    private LocalDateTime fecha;

    public ComentarioVista(int idComentario, String nickname, String texto, LocalDateTime fecha) {
        this.idComentario = idComentario;
        this.nickname = nickname;
        this.texto = texto;
        this.fecha = fecha;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    
    
    
}
