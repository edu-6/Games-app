/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.CategoriasDB;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.modelos.gamers.Categoria;

/**
 *
 * @author edu
 */
public class CategoriasCrudService {
    
    public void crearCategoria(Categoria categoria) throws IdentidadRepetidaException{
        CategoriasDB db = new CategoriasDB();
        
        if(db.existeCategoria(categoria.getCategoria())){
            throw new IdentidadRepetidaException("ya existe la categoría "+ categoria.getCategoria());
        }
        
        db.crearCategoria(categoria);
    }
    
    
    public void editarCategoria(String anterior, String nueva) throws IdentidadRepetidaException{
        CategoriasDB db = new CategoriasDB();
        
        if(db.existeCategoria(categoria.getCategoria())){
            throw new IdentidadRepetidaException("ya existe la categoría "+ categoria.getCategoria());
        }
        
        db.editarCategoria(categoria);
    }
    
}
