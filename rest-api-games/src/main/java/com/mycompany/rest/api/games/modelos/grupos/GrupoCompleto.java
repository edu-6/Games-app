/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.grupos;

/**
 *
 * @author edu
 */
public class GrupoCompleto extends Grupo {
    private int id;
    private int numeroIntegrantes;

    public GrupoCompleto(int id, String nombre, String correoAdmin, int numeroIntegrantes) {
        super(nombre, correoAdmin);
        this.id = id;
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public GrupoCompleto() {
    }
    
    

    public int getId() {
        return id;
    }

    public int getNumeroIntegrantes() {
        return numeroIntegrantes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroIntegrantes(int numeroIntegrantes) {
        this.numeroIntegrantes = numeroIntegrantes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }
    
    
}
