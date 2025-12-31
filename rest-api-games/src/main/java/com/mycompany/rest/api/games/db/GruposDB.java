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
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoRequest;
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edu
 */
public class GruposDB extends Crud {

    private static final String OBTENER_ID_GRUPO_POR_NOMBRE = "select grupo_id from grupo where grupo_nombre = ?";
    private static final String DISMINUIR_INTEGRANTES_EN_UNO = "UPDATE grupo SET grupo_numero_integrantes = grupo_numero_integrantes - 1 WHERE grupo_id = ?;";
    private static final String EXISTE_GAMER = "SELECT 1 FROM gamer WHERE gamer_correo = ?";
    private static final String ACTUALIZAR_CONTADOR = "UPDATE grupo SET grupo_numero_integrantes = grupo_numero_integrantes + 1 WHERE grupo_id = ?";
    private static final String EXISTE_PARTICIPANTE = "SELECT * FROM participante_grupo WHERE correo_participante = ? AND id_grupo = ?";
    private static final String INSERTAR_PARTICIPANTE = "INSERT INTO participante_grupo (correo_participante, id_grupo) VALUES (?, ?)";
    private static final String ELIMINAR_PARTICIPANTE = "DELETE FROM participante_grupo WHERE correo_participante = ? AND id_grupo = ?";
    private static final String LISTAR_PARTICIPANTES = "SELECT p.correo_participante, g.gamer_nombre, p.id_grupo FROM participante_grupo p "
            + "JOIN gamer g ON p.correo_participante = g.gamer_correo WHERE p.id_grupo = ?";
    private static final String BUSCAR_POR_ADMIN = "SELECT * FROM grupo WHERE grupo_correo_admin = ?";
    private static final String CREAR_GRUPO = "INSERT INTO grupo (grupo_nombre, grupo_correo_admin, grupo_numero_integrantes) VALUES (?, ?, ?)";
    private static final String EDITAR_GRUPO = "UPDATE grupo SET grupo_nombre = ?, grupo_correo_admin = ?, grupo_numero_integrantes = ? WHERE grupo_id = ?";
    private static final String BUSCAR_GRUPO = "SELECT * FROM grupo WHERE grupo_id = ?";
    private static final String ELIMINAR_GRUPO = "DELETE FROM grupo WHERE grupo_id = ?";
    private static final String EXISTE_GRUPO = "SELECT * FROM grupo WHERE grupo_nombre = ?";

    @Override
    public boolean existeEntidad(String nombre) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EXISTE_GRUPO)) {
            ps.setString(1, nombre);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }
    
    private int buscarIdGrupo(String nombre) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(OBTENER_ID_GRUPO_POR_NOMBRE)) {
            ps.setString(1, nombre);
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getInt("grupo_id");
        }
        
    }

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException {
        Grupo grupo = (Grupo) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_GRUPO)) {
            ps.setString(1, grupo.getNombre());
            ps.setString(2, grupo.getCorreoAdmin());
            ps.setInt(3, 0);// o participantes
            ps.executeUpdate();
            
            int id_grupo = buscarIdGrupo(grupo.getNombre());
            this.insertarParticipante(new ParticipanteGrupoRequest(grupo.getCorreoAdmin(), id_grupo));
            this.sumarIntegrante(id_grupo);
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

    public List<GrupoCompleto> buscarGruposPorAdmin(String correo) throws SQLException {
        List<GrupoCompleto> grupos = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_POR_ADMIN)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                grupos.add(extraerGrupo(result));
            }
        }
        return grupos;
    }

    @Override
    public byte[] recuperarImagen(String id) throws SQLException, NoEncontradoException {
        return null;
    }

    @Override
    public void agregarImagen(AvatarEntidad avatar) {

    }

    public void insertarParticipante(ParticipanteGrupoRequest req) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(INSERTAR_PARTICIPANTE)) {
            ps.setString(1, req.getCorreo());
            ps.setInt(2, req.getIdGrupo());
            ps.executeUpdate();
        }
    }

    public void eliminarParticipante(String correo, int idGrupo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ELIMINAR_PARTICIPANTE)) {
            ps.setString(1, correo);
            ps.setInt(2, idGrupo);
            ps.executeUpdate();
            this.disminuirIntegrantesEnUno(idGrupo);
        }
       
    }
    
    private void disminuirIntegrantesEnUno(int idGrupo) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(DISMINUIR_INTEGRANTES_EN_UNO)) {
                ps.setInt(1, idGrupo);
                ps.executeUpdate();
            }
    }

    public boolean existeParticipante(String correo, int idGrupo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EXISTE_PARTICIPANTE)) {
            ps.setString(1, correo);
            ps.setInt(2, idGrupo);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }

    public boolean existeGamer(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EXISTE_GAMER)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public void sumarIntegrante(int idGrupo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ACTUALIZAR_CONTADOR)) {
            ps.setInt(1, idGrupo);
            ps.executeUpdate();
        }
    }

    public List<ParticipanteGrupoResponse> obtenerParticipantes(int idGrupo) throws SQLException {
        List<ParticipanteGrupoResponse> participantes = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(LISTAR_PARTICIPANTES)) {
            ps.setInt(1, idGrupo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                participantes.add(new ParticipanteGrupoResponse(
                        rs.getString("correo_participante"),
                        rs.getInt("id_grupo"),
                        rs.getString("gamer_nombre")
                ));
            }
        }
        return participantes;
    }
}
