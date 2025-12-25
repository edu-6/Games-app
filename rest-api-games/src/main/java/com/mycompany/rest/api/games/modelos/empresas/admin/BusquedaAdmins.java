/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.empresas.admin;

/**
 *
 * @author edu
 */ 

// representa  una busqueda de  admins-empresa
// contiene el correo del admin quien busca y el parametro de la busqueda en sí 
public class BusquedaAdmins {
    private String correoDelBuscador;
    private String parametroDeBusqueda;

    public BusquedaAdmins() {
    }
    
    
    
    public String getCorreoDelBuscador() {
        return correoDelBuscador;
    }

    public String getParametroDeBusqueda() {
        return parametroDeBusqueda;
    }

    public void setCorreoDelBuscador(String correoDelBuscador) {
        this.correoDelBuscador = correoDelBuscador;
    }

    public void setParametroDeBusqueda(String parametroDeBusqueda) {
        this.parametroDeBusqueda = parametroDeBusqueda;
    }
    
    
    
}
