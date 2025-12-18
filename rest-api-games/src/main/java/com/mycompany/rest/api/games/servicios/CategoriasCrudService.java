/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.CategoriasDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.categorias.Categoria;
import com.mycompany.rest.api.games.modelos.categorias.EdicionCategoria;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class CategoriasCrudService {
    
    public void crearCategoria(Categoria categoria) throws IdentidadRepetidaException, DatosInvalidosException{
        CategoriasDB db = new CategoriasDB();
        
        if(!categoria.valido()){
            throw new DatosInvalidosException(" categoría vacía o demasiado grande");
        }
        
        if(db.existeCategoria(categoria.getCategoria())){
            throw new IdentidadRepetidaException("ya existe la categoría "+ categoria.getCategoria());
        }
        
        db.crearCategoria(categoria.getCategoria());
    }
    
    
    public void editarCategoria(EdicionCategoria edicion) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException{
        CategoriasDB db = new CategoriasDB();
        if(!edicion.valido()){
            throw new DatosInvalidosException("datos invalidos");
        }
        if(db.existeCategoria(edicion.getNuevoNombre())){
            throw new IdentidadRepetidaException("ya existe la categoría "+ edicion.getNombreAnterior());
        }
        
        int id = db.obtenerIdCategoria(edicion.getNombreAnterior());
        
        db.editarCategoria(edicion,id);
    }
    
    
    public ArrayList<Categoria> obtenerCategorias(){
         CategoriasDB db = new CategoriasDB();
         return db.obtenerCategorias();
    }
    
    
    public Categoria buscarCategoria(String nombre) throws DatosInvalidosException, NoEncontradoException{
        CategoriasDB db = new CategoriasDB();
        if(nombre == null){
             throw new DatosInvalidosException("ingrese el nombre");
        }
        Categoria categoria = db.buscarCategoria(nombre);
        
        if(categoria == null){
            throw new NoEncontradoException();
        }
        return categoria;
    }
    
}
