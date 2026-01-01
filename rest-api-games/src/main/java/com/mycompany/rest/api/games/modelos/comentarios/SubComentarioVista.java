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
public class SubComentarioVista  {
   private int idSubcomentario;
    private String nickname; 
    private String texto;
    private LocalDateTime fecha;

    public SubComentarioVista(int idSubcomentario, String nickname, String texto, LocalDateTime fecha) {
        this.idSubcomentario = idSubcomentario;
        this.nickname = nickname;
        this.texto = texto;
        this.fecha = fecha;
        
    }

    public void setIdSubcomentario(int idSubcomentario) {
        this.idSubcomentario = idSubcomentario;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
