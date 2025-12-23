/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.Crud;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.Entidad;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public abstract class CrudService {
    
    public abstract void crearEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, SQLException;
    public abstract void editarEntidad(Entidad  entidad) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException, SQLException;
    public abstract void eliminarEntidad(String parametro)throws DatosInvalidosException, NoEncontradoException, SQLException;
    
    
    
    
}
