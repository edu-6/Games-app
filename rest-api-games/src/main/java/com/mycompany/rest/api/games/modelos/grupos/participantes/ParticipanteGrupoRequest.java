/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.grupos.participantes;

/**
 *
 * @author edu
 */
public class ParticipanteGrupoRequest {
    private String correo;
    private int idGrupo;
    
    public ParticipanteGrupoRequest() {
        
    }
    public ParticipanteGrupoRequest(String correo, int idGrupo) {
        this.correo = correo;
        this.idGrupo = idGrupo;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    
    public boolean valido() {
        return correo != null && !correo.isBlank() 
                && correo.length() <= 100 
                && idGrupo > 0;
    }
    
    
    
}
