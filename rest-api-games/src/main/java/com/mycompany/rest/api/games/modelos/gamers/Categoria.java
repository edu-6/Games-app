/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.gamers;

/**
 *
 * @author edu
 */
public class Categoria {
    private String categoria;

    public Categoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Categoria(){
        
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
    
    
}
