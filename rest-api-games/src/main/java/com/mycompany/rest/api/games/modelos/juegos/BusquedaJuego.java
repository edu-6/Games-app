/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.modelos.juegos;

/**
 *
 * @author edu
 */
public class BusquedaJuego {
    
    private static final String BUSQUEDA_BASE = "select *from juego where 1=1";
    private static final String FILTRO_NOMBRE = " and juego_nombre = ?";
    private static final String FILTRO_PRECIO = " and juego_precio >  ? and juego_precio < ?";
    private static final String FILTRO_EMPRESA = " and juego_id_empresa = ?";
    
    
    private String nombre;
    private double precioMaximo;
    private double precioMinimo;
    private String categoria;
    private String correoAdmin; // sirve para filtar los juegos en base a empresa con el correo del admin
    private int idEmpresa;
    
    // final
    private String busquedaFinal;
    private int numeroFiltros;

    public BusquedaJuego() {
    }

    public String armarBusqueda(){
        numeroFiltros = 0; // reiniciar
        busquedaFinal = BUSQUEDA_BASE; // SI NO HAY FILTROS 
        
        if (nombre != null) {
            busquedaFinal += FILTRO_NOMBRE;
            numeroFiltros++;
        }
        
        if(precioMinimo > 0 && precioMaximo > precioMinimo){
            busquedaFinal += FILTRO_PRECIO;
            numeroFiltros++;
        }
        
        if(idEmpresa >0){
            busquedaFinal += FILTRO_EMPRESA;
            numeroFiltros++;
        }
        
        return busquedaFinal;

    }

}
