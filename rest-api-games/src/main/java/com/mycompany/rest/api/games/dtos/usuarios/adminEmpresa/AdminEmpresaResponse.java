/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.usuarios.adminEmpresa;

import com.mycompany.rest.api.games.dtos.usuarios.UsuarioResponse;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresa;
import java.time.LocalDate;

/**
 *
 * @author edu
 */
public class AdminEmpresaResponse extends UsuarioResponse {
    private String contrasena;

    public AdminEmpresaResponse(AdminEmpresa admin) {
        super(admin.getNombre(), admin.getCorreo(), admin.getFechaNacimiento());
        this.contrasena  = admin.getContraseña();
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    
}
