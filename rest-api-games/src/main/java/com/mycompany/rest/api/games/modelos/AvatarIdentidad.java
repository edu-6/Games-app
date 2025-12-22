/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos;

import java.io.InputStream;

/**
 *
 * @author edu
 */
public class AvatarIdentidad {
    private String idIdentidad;
    private InputStream imagen;

    public AvatarIdentidad(String id, InputStream imagen) {
        this.idIdentidad = id;
        this.imagen = imagen;
    }

    public String getIdIdentidad() {
        return idIdentidad;
    }

    public InputStream getImagen() {
        return imagen;
    }
    
    public boolean valido(){
        return this.idIdentidad != null && this.imagen != null;
    }
}
