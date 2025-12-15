/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.gamers;

import java.time.LocalDate;
import java.time.Period;


/**
 *
 * @author edu
 */
public class Gamer {
    private String nombre;
    private String nombrePais;
    private String correo;
    private String nickname;
    private String constraseña;
    private int telefono;
    private LocalDate fechaNacimiento;
    private int edad;

    public Gamer(String nombre, String nombrePais, String correo, String nickname, String constraseña, int telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.nombrePais = nombrePais;
        this.correo = correo;
        this.nickname = nickname;
        this.constraseña = constraseña;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getConstraseña() {
        return constraseña;
    }

    public int getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
    
    public boolean valido(){
        return true;
    }
}
