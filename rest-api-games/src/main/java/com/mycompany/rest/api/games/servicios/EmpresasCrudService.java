/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.EmpresasDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.empresas.Empresa;
import com.mycompany.rest.api.games.modelos.empresas.EmpresaEdicion;
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
        if(db.existeEmpresa(edicion.getNuevoNombre())){
            throw new IdentidadRepetidaException("ya existe la empresa "+ edicion.getNombre());
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
}
