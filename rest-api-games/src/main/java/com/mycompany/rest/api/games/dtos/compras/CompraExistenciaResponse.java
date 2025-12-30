/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.compras;

/**
 *
 * @author edu
 */
public class CompraExistenciaResponse {
    
    private boolean existe;

    public CompraExistenciaResponse() {
    }
    
     

    public CompraExistenciaResponse(boolean existe) {
        this.existe = existe;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
