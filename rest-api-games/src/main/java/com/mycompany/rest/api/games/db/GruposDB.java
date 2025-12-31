/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.grupos.Grupo;
import com.mycompany.rest.api.games.modelos.grupos.GrupoCompleto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class GruposDB extends Crud {

    private static final String CREAR_GRUPO = "INSERT INTO grupo (grupo_nombre, grupo_correo_admin, grupo_numero_integrantes) VALUES (?, ?, ?)";
    private static final String EDITAR_GRUPO = "UPDATE grupo SET grupo_nombre = ?, grupo_correo_admin = ?, grupo_numero_integrantes = ? WHERE grupo_id = ?";
    private static final String BUSCAR_GRUPO = "SELECT * FROM grupo WHERE grupo_id = ?";
    private static final String ELIMINAR_GRUPO = "DELETE FROM grupo WHERE grupo_id = ?";
    private static final String EXISTE_GRUPO = "SELECT * FROM grupo WHERE grupo_nombre = ?";

    @Override
    public boolean existeEntidad(String nombre) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EXISTE_GRUPO)) {
            ps.setString(1,nombre);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException {
        Grupo grupo = (Grupo) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_GRUPO)) {
            ps.setString(1, grupo.getNombre());
            ps.setString(2, grupo.getCorreoAdmin());
            ps.setInt(3, 1);
            ps.executeUpdate();
        }
    }

    @Override
    public void editarEntidad(Entidad entidad) throws SQLException {
        GrupoCompleto grupo = (GrupoCompleto) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EDITAR_GRUPO)) {
            ps.setString(1, grupo.getNombre());
            ps.setString(2, grupo.getCorreoAdmin());
            ps.setInt(3, grupo.getNumeroIntegrantes());
            ps.setInt(4, grupo.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarEntidad(String id) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ELIMINAR_GRUPO)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        }
    }

    public GrupoCompleto buscarGrupo(String id) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_GRUPO)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return extraerGrupo(result);
            }
        }
        return null;
    }

    private GrupoCompleto extraerGrupo(ResultSet result) throws SQLException {
        return new GrupoCompleto(
                result.getInt("grupo_id"),
                result.getString("grupo_nombre"),
                result.getString("grupo_correo_admin"),
                result.getInt("grupo_numero_integrantes")
        );
    }

    @Override
    public byte[] recuperarImagen(String id) throws SQLException, NoEncontradoException {
        return null;
    }

    @Override
    public void agregarImagen(AvatarEntidad avatar) {
        
    }
}
