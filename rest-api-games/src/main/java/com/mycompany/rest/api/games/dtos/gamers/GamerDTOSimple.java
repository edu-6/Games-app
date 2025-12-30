/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.gamers;

/**
 *
 * @author edu
 */
public class GamerDTOSimple {
    protected String nombre;
    protected String nombrePais;
    protected String correo;
    protected String nickname;
    protected String contrasena;
    protected String telefono;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTelefono() {
        return telefono;
    }
    
    
    
}
