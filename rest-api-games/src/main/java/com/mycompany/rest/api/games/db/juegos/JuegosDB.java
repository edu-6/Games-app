/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db.juegos;

import com.mycompany.rest.api.games.db.Crud;
import com.mycompany.rest.api.games.db.DBConnectionSingleton;
import com.mycompany.rest.api.games.db.ImagenesDB;
import static com.mycompany.rest.api.games.db.AdminEmpresaDB.RECUPERAR_IMAGEN;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.juegos.Juego;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class JuegosDB extends Crud {

    private static final String CREAR_JUEGO = "INSERT INTO juego (juego_nombre, clasificacion_edad, juego_descripcion, juego_requerimientos, juego_precio, fecha_lanzamiento, activo, juego_permite_comentarios, juego_codigo_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EDITAR_JUEGO = "UPDATE juego SET juego_nombre = ?, clasificacion_edad = ?, juego_descripcion = ?, juego_requerimientos = ?, juego_precio = ?, activo = ?, juego_permite_comentarios = ? WHERE juego_nombre = ?";
    private static final String ELIMINAR_JUEGO = "DELETE FROM juego WHERE juego_nombre = ?";
    private static final String EXISTE_JUEGO = "SELECT * FROM juego WHERE juego_nombre = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM juego";
    private static final String BUSCAR_JUEGO_POR_NOMBRE = "SELECT * FROM juego WHERE juego_nombre = ?";
    private static final String AGREGAR_IMAGEN = "UPDATE juego set juego_avatar = ? WHERE juego_nombre = ?";

    @Override
    public boolean existeEntidad(String nombre) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EXISTE_JUEGO)) {
            ps.setString(1, nombre);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException {
        Juego juego = (Juego) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_JUEGO)) {
            ps.setString(1, juego.getNombre());
            ps.setString(2, juego.getClasificacion());
            ps.setString(3, juego.getDescripcion());
            ps.setString(4, juego.getRequerimientos());
            ps.setDouble(5, juego.getPrecio());
            ps.setDate(6, java.sql.Date.valueOf(juego.getFechaLanzamiento()));
            ps.setBoolean(7, juego.isActivo());
            ps.setBoolean(8, juego.isPermiteComentarios());
            ps.setInt(9, juego.getIdEmpresa());
            ps.executeUpdate();
        }
    }

    @Override
    public void editarEntidad(Entidad entidad) throws SQLException {
         Juego juego = (Juego) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EDITAR_JUEGO)) {
            ps.setString(1, juego.getNombre());
            ps.setString(2, juego.getClasificacion());
            ps.setString(3, juego.getDescripcion());
            ps.setString(4, juego.getRequerimientos());
            ps.setDouble(5, juego.getPrecio());
            ps.setBoolean(6, juego.isActivo());
            ps.setBoolean(7, juego.isPermiteComentarios());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarEntidad(String id) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ELIMINAR_JUEGO)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public byte[] recuperarImagen(String id) throws SQLException, NoEncontradoException {
        ImagenesDB db = new ImagenesDB();
        return db.recuperarImagen(RECUPERAR_IMAGEN, id, "juego_avatar");
    }

    public void agregarImagen(AvatarEntidad avatar) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(AGREGAR_IMAGEN);) {
            query.setBlob(1, avatar.getImagen());
            query.setString(2, avatar.getIdIdentidad());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
