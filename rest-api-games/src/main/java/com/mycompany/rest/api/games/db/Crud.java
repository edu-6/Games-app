/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public abstract  class Crud {
    public abstract boolean existeEntidad(String id) throws SQLException;
    public abstract void crearEntidad(Entidad entidad) throws SQLException;
    public abstract void editarEntidad(Entidad  entidad) throws SQLException;
    public abstract void eliminarEntidad(String id) throws SQLException;
    
    public abstract byte [] recuperarImagen(String id) throws SQLException, NoEncontradoException;
    
    public abstract void agregarImagen(AvatarEntidad avatar);
}
