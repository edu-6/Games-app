/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.comentarios.ComentarioVista;
import com.mycompany.rest.api.games.modelos.comentarios.Comentario;
import com.mycompany.rest.api.games.modelos.comentarios.SubComentarioVista;
import com.mycompany.rest.api.games.modelos.comentarios.Subcomentario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edu
 */
public class ComentariosDB {

    private static final String REGISTRAR_SUB_COMENTARIO = "INSERT INTO subcomentario_juego "
            + "(id_comentario_padre, correo_usuario, texto_subcomentario, fecha_subcomentario) "
            + "VALUES (?, ?, ?, ?)";

    private static final String OBTENER_SUB_COMENTARIOS_VISTA = "SELECT s.id_subcomentario, g.nickname, "
            + "s.texto_subcomentario, s.fecha_subcomentario "
            + "FROM subcomentario_juego s "
            + "INNER JOIN gamer g ON s.correo_usuario = g.gamer_correo "
            + "WHERE s.id_comentario_padre = ? "
            + "ORDER BY s.fecha_subcomentario ASC";
    private static final String OBTENER_JUEGO_ID_CON_NOMBRE = "SELECT juego_id from juego where juego_nombre = ?";
    private static final String REGISTRAR_COMENTARIO = "INSERT INTO comentario_juego "
            + "(id_juego, correo_usuario, texto_comentario, fecha_comentario) VALUES (?, ?, ?, ?)";

    private static final String OBTENER_COMENTARIOS_VISTA = "SELECT c.id_comentario, g.nickname, c.texto_comentario, c.fecha_comentario "
            + "FROM comentario_juego c "
            + "INNER JOIN gamer g ON c.correo_usuario = g.gamer_correo "
            + "WHERE c.id_juego = ? ORDER BY c.fecha_comentario DESC";

    public void registrarComentario(Comentario comentario) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(REGISTRAR_COMENTARIO)) {
            insert.setInt(1, comentario.getIdJuego());
            insert.setString(2, comentario.getCorreoUsuario());
            insert.setString(3, comentario.getTexto());
            insert.setTimestamp(4, Timestamp.valueOf(comentario.getFecha()));
            insert.executeUpdate();
        }
    }

    public List<ComentarioVista> obtenerComentariosVista(int idJuego) throws SQLException {
        List<ComentarioVista> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement select = connection.prepareStatement(OBTENER_COMENTARIOS_VISTA)) {

            select.setInt(1, idJuego);
            ResultSet result = select.executeQuery();

            while (result.next()) {
                lista.add(new ComentarioVista(
                        result.getInt("id_comentario"),
                        result.getString("nickname"),
                        result.getString("texto_comentario"),
                        result.getTimestamp("fecha_comentario").toLocalDateTime()
                ));
            }
        }
        return lista;
    }

    public int obtenerIdJuegoPorNombre(String nombreJuego) throws SQLException {
        int id = -1;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement select = connection.prepareStatement(OBTENER_JUEGO_ID_CON_NOMBRE)) {
            select.setString(1, nombreJuego);
            try (ResultSet result = select.executeQuery()) {
                if (result.next()) {
                    id = result.getInt("juego_id");
                }
            }
        }
        return id;
    }
    
    
    public void registrarSubcomentario(Subcomentario sub) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); 
             PreparedStatement insert = connection.prepareStatement(REGISTRAR_SUB_COMENTARIO)) {
            
            insert.setInt(1, sub.getIdComentarioPadre());
            insert.setString(2, sub.getCorreoUsuario());
            insert.setString(3, sub.getTexto());
            insert.setTimestamp(4, Timestamp.valueOf(sub.getFecha()));
            
            insert.executeUpdate();
        }
    }

    public List<SubComentarioVista> obtenerSubcomentariosVista(int idComentarioPadre) throws SQLException {
        List<SubComentarioVista> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); 
             PreparedStatement select = connection.prepareStatement(OBTENER_SUB_COMENTARIOS_VISTA)) {
            select.setInt(1, idComentarioPadre);
            try (ResultSet result = select.executeQuery()) {
                while (result.next()) {
                    lista.add(new SubComentarioVista(
                        result.getInt("id_subcomentario"),
                        result.getString("nickname"),
                        result.getString("texto_subcomentario"),
                        result.getTimestamp("fecha_subcomentario").toLocalDateTime()
                    ));
                }
            }
        }
        return lista;
    }

}
