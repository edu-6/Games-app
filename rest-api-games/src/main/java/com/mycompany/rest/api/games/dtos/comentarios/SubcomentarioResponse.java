/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.comentarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.rest.api.games.modelos.comentarios.SubComentarioVista;
import java.time.LocalDateTime;

/**
 *
 * @author edu
 */
public class SubcomentarioResponse {
    protected int idSubcomentario;
    protected String nickname;
    protected String texto;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime fecha;

    public SubcomentarioResponse(SubComentarioVista vista) {
        this.idSubcomentario = vista.getIdSubcomentario();
        this.nickname = vista.getNickname();
        this.texto = vista.getTexto();
        this.fecha = vista.getFecha();
    }

    public int getIdSubcomentario() {
        return idSubcomentario;
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
