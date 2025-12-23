/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.EmpresasDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.empresas.Empresa;
import com.mycompany.rest.api.games.modelos.empresas.EmpresaEdicion;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class EmpresasCrudService {
    public void crearEmpresa(Empresa empresa) throws IdentidadRepetidaException, DatosInvalidosException, SQLException{
        EmpresasDB db = new EmpresasDB();
        
        if(!empresa.valido()){
            throw new DatosInvalidosException(" campos vacios o demasiado grandes");
        }
        
        if(db.existeEmpresa(empresa.getNombre())){
            throw new IdentidadRepetidaException("ya existe la empresa "+ empresa.getNombre());
        }
        
        db.crearEmpresa(empresa);
    }
    
    
    public void editarEmpresa(EmpresaEdicion edicion) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException, SQLException{
        EmpresasDB db = new EmpresasDB();
        if(!edicion.valido()){
            throw new DatosInvalidosException("datos invalidos");
        }
        
        if (!edicion.getNuevoNombre().equals(edicion.getNombre())) { // si no son mismos
            if (db.existeEmpresa(edicion.getNuevoNombre())) { // si ya existe algo con el nuevo nombre
                throw new IdentidadRepetidaException("ya existe la empresa " + edicion.getNuevoNombre());
            }
        }
        
        db.editarEmpresa(edicion);
    }
    
    
    public ArrayList<Empresa> obtenerEmpresas() throws SQLException{
         EmpresasDB db = new EmpresasDB();
         return db.obtenerEmpresas();
    }
    
    
    public Empresa buscarEmpresa(String nombre) throws DatosInvalidosException, NoEncontradoException, SQLException{
        EmpresasDB db = new EmpresasDB();
        if(nombre == null){
             throw new DatosInvalidosException("ingrese el nombre");
        }
        Empresa Empresa = db.buscarEmpresa(nombre);
        
        if(Empresa == null){
            throw new NoEncontradoException();
        }
        return Empresa;
    }
    
    
    public void eliminarEmpresa(String nombre) throws DatosInvalidosException, NoEncontradoException{
        EmpresasDB db = new EmpresasDB();
        if(nombre == null){
            throw new DatosInvalidosException("seleccione la empresa");
        }
        
        if(!db.existeEmpresa(nombre)){
            throw new NoEncontradoException();
        }
        
        db.eliminarEmpresa(nombre);
    }
    
    
    public void agregarImagen(AvatarEntidad avatar) {
        EmpresasDB db = new EmpresasDB();
        try {
            db.agregarImagen(avatar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StreamingOutput recuperarImagen(String correo) throws SQLException, NoEncontradoException {
        EmpresasDB db = new EmpresasDB();
        
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
