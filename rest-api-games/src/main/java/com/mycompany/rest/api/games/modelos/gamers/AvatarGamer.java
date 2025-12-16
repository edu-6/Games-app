/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.gamers;

import java.io.InputStream;

/**
 *
 * @author edu
 */
public class AvatarGamer {
    
    private String correo;
    private InputStream imagen;

    public AvatarGamer(String correo, InputStream imagen) {
        this.correo = correo;
        this.imagen = imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public InputStream getImagen() {
        return imagen;
    }
    
    
    public boolean valido(){
        return this.correo != null && this.imagen != null;
    }
    
    
    
}
