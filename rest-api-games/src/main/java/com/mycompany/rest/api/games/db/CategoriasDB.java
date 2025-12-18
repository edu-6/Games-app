/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.categorias.Categoria;
import com.mycompany.rest.api.games.modelos.categorias.EdicionCategoria;
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
    private static final String EDITAR_CATEGORIA = "update  categoria set categoria_nombre = ? where id = ?";
    private static final String BUSCAR_CATEGORIA = "select *from categoria where categoria_nombre = ?";
    private static final String ELIMINAR_CATEGORIA = "delete * form categoria where categoria_nombre = ?";

    private static final String OBTENER_TODAS_LAS_CATEGORIAS = "select *from categoria order by categoria_nombre desc";
    
    
    public int obtenerIdCategoria(String nombre) throws NoEncontradoException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_CATEGORIA)) {
            insert.setString(1, nombre);
            ResultSet result = insert.executeQuery();
            if(result.next()){
                 return result.getInt("id");
            }
            throw new NoEncontradoException();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoEncontradoException();
        }
    }

    public boolean existeCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_CATEGORIA)) {
            insert.setString(1, nombre);
            ResultSet result = insert.executeQuery();
            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Categoria buscarCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_CATEGORIA)) {
            insert.setString(1, nombre);
            ResultSet result = insert.executeQuery();
            result.next();
            return new Categoria(result.getString("categoria_nombre"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void crearCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREAR_CATEGORIA)) {
            insert.setString(1, nombre);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarCategoria(EdicionCategoria edicion, int id) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(EDITAR_CATEGORIA)) {
            insert.setString(1, edicion.getNuevoNombre());
            insert.setInt(2, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCategoria(String nombre) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ELIMINAR_CATEGORIA)) {
            insert.setString(1, nombre);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Categoria> obtenerCategorias() {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_TODAS_LAS_CATEGORIAS)) {
            ArrayList<Categoria> lista = new ArrayList();
            ResultSet result = insert.executeQuery();

            while (result.next()) {
                lista.add(new Categoria(result.getString("categoria_nombre")));
            }
            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
