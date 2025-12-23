/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.AdminSistemaDB;
import com.mycompany.rest.api.games.db.Crud;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public abstract class CrudService {
    
    public abstract void crearEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, SQLException;
    public abstract void editarEntidad(Entidad  entidad) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException, SQLException;
    public abstract void eliminarEntidad(String parametro)throws DatosInvalidosException, NoEncontradoException, SQLException;
   
    
    public void agregarImagen(AvatarEntidad avatarEntidad, Crud db) {
            db.agregarImagen(avatarEntidad);
    }
    
    public StreamingOutput recuperarImagen(String correo, Crud db) throws SQLException, NoEncontradoException {
        byte [] imagen = db.recuperarImagen(correo);
        if(imagen == null){
            throw new NoEncontradoException();
        }
        // retornar un straming output
        return new StreamingOutput() {
        @Override
        public void write(OutputStream os) throws IOException, WebApplicationException {
            os.write(imagen);
            os.flush();
        }
    };
    }
    
    
    
    
}
