/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.grupos.participantes;

/**
 *
 * @author edu
 */
public class ParticipanteGrupoResponse extends ParticipanteGrupoRequest {
    private String nombre;

    public ParticipanteGrupoResponse(String correo, int idGrupo, String nombre) {
        super(correo, idGrupo);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
