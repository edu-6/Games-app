/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.dtos.usuarios.adminEmpresa;

import com.mycompany.rest.api.games.dtos.usuarios.UsuarioRequest;

/**
 *
 * @author edu
 */
public class AdminEmpresaRequest extends UsuarioRequest{
    private String correoAdminCreador;

    public String getCorreoAdminCreador() {
        return correoAdminCreador;
    }

    public void setCorreoAdminCreador(String correoAdminCreador) {
        this.correoAdminCreador = correoAdminCreador;
    }
}
