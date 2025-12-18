/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.gamers.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class CategoriasDB {

    private static final String CREAR_CATEGORIA = "insert into categoria (categoria_nombre) values (?)";
    private static final String EDITAR_CATEGORIA = "update table categoria set categoria_nombre = ? where categoria_nombre = ?";
    private static final String BUSCAR_CATEGORIA = "select *from categoria where categoria_nombre = ?";
    private static final String ELIMINAR_CATEGORIA = "delete * form categoria where categoria_nombre = ?";

    private static final String OBTENER_TODAS_LAS_CATEGORIAS = "select *from categoria";
    
    
    public boolean existeCategoria(String nomnbre){
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREAR_CATEGORIA)) {
            insert.setString(1, BUSCAR_CATEGORIA);
            ResultSet result = insert.executeQuery();
            return result.next();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return false;
    }

    public void crearCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREAR_CATEGORIA)) {
            insert.setString(1, CREAR_CATEGORIA);
            insert.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    
    public void editarCategoria(String anterior, String nueva) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(EDITAR_CATEGORIA)) {
            insert.setString(1, nueva);
            insert.setString(2, anterior);
            insert.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public ArrayList<Categoria> obtenerCategorias () {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_TODAS_LAS_CATEGORIAS)) {
            ArrayList <Categoria> lista =  new ArrayList();
            ResultSet result = insert.executeQuery();
            
            while(result.next()){
                lista.add(new Categoria(result.getString("categoria_nombre")));
            }
            return lista;
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }
    
    
    public void eliminarCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(EDITAR_CATEGORIA)) {
            insert.setString(1, nombre);
            insert.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    
    
    

}
