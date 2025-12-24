/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas.admin;

import com.mycompany.rest.api.games.modelos.EntidadBackend;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author edu
 */
public class AdminEmpresa extends EntidadBackend{
    
    // Representa la clase para la edición y la creación
    private String nombre;
    private String correo;
    private String contraseña;
    private LocalDate fechaNacimiento;
    private int idEmpresa;

    public AdminEmpresa(String nombre, String correo, String contraseña, LocalDate fechaNacimiento, int idEmpresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.idEmpresa = idEmpresa;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
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

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    

    @Override
    public boolean valido() {
        return this.nombre != null
                && this.correo != null
                && this.contraseña != null
                && this.fechaNacimiento != null
                && this.idEmpresa  > 0
                && StringUtils.isNotBlank(nombre)
                && StringUtils.isNotBlank(contraseña)
                &&  nombre.length()  <=  100
                &&  contraseña.length()  <= 100;
    }
    
}
