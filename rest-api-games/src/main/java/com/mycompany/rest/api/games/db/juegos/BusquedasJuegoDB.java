/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db.juegos;

/**
 *
 * @author edu
 */
public class BusquedasJuegoDB {
    
    // busquedas simples para los usuarios
    private final static String BUSQUEDA_NOMBRE = "SELECT * FROM juego where juego_nombre = ?";
    private final static String BUSQUEDA_PRECIO = "SELECT * FROM juego WHERE juego_precio >= ? and juego_precio <= ?";
    private final static String BUSQUEDA_CATEGORIA = "SELECT * FROM juego join categoria_juego on categoria_nombre = ?";
    private final static String BUSQUEDA_POR_EMPRESA = "SELECT * FROM juego where juego_codigo_empresa = ?";
    
    private final static String LIMIT = " LIMIT = ?";
    
    // busquedas para admins empresa
    private final static String BUSQUEDA_ADMIN = " AND juego_codigo_empresa = ?";
    private final static String BUSQUEDA_PRECIO_ADMIN = BUSQUEDA_PRECIO + BUSQUEDA_ADMIN;
    private final static String BUSQUEDA_CATEGORIA_ADMIN = BUSQUEDA_CATEGORIA + BUSQUEDA_ADMIN;
}
