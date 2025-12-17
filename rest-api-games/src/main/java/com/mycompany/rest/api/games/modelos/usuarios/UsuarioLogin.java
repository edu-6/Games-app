/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.usuarios;

/**
 *
 * @author edu  REPRESENTA EL INTENTO DE LOGIN DESDE EL FRONTEND
 */
public class UsuarioLogin {
    
    private String correo;
    private String contraseña;
    
    public UsuarioLogin(){
        
    }

    public UsuarioLogin(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public boolean valido(){
        return this.correo != null && this.contraseña != null;
    }
    
    
    
}
