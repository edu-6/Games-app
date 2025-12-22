/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas.admin;

import java.sql.Date;

/**
 *
 * @author edu
 */
public class AdminEmpresa {
    
    // Representa la clase para la edición y la creación
    
    private String nombre;
    private String correo;
    private String contraseña;
    private Date fechaNacimiento;
    private String nombreEmpresa;

    public AdminEmpresa(String nombre, String correo, String contraseña, Date fechaNacimiento, String nombreEmpresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.nombreEmpresa = nombreEmpresa;
    }

    public AdminEmpresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    
    
    
    
}
