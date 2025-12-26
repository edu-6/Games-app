/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.juegos.JuegosDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.juegos.Juego;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class JuegosCrudService extends CrudService {

    @Override
    public void crearEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, SQLException {
        Juego juego = (Juego) entidad;
        JuegosDB db = new JuegosDB();
        if(!juego.valido()){
            throw new DatosInvalidosException("Datos invalidos o campos demasiado largos");
        }
        if(db.existeEntidad(juego.getNombre())){
            throw new IdentidadRepetidaException("ya existe el juego con nombre: "+ juego.getNombre());
        }
        db.crearEntidad(entidad);
    }

    @Override
    public void editarEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException, SQLException {
        Juego juego = (Juego) entidad;
        JuegosDB db = new JuegosDB();
        if(!db.existeEntidad(juego.getNombre())){
            throw new NoEncontradoException();
        }
        
        db.editarEntidad(entidad);
    }

    @Override
    public void eliminarEntidad(String parametro) throws DatosInvalidosException, NoEncontradoException, SQLException {
        JuegosDB db = new JuegosDB();
        if(!db.existeEntidad(parametro)){
            throw new NoEncontradoException();
        }
        
        db.eliminarEntidad(parametro);
    }
    
}
