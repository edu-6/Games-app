/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.cartera.RecargoTarjeta;
import com.mycompany.rest.api.games.modelos.gamers.AvatarGamer;
import com.mycompany.rest.api.games.modelos.gamers.Gamer;
import com.mycompany.rest.api.games.modelos.gamers.GamerSimple;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class GamersDB {
    
    private static final String RECUPERAR_IMAGEN = "SELECT avatar from gamer where gamer_correo = ?";
    private static final String ELIMINAR_GAMER = "DELETE from gamer where gamer_correo = ?";
    private static final String CREATE_GAMER_QUERY = "insert into gamer (gamer_nombre, nickname, gamer_correo,gamer_contraseña,gamer_edad, gamer_fecha_nacimiento,nombre_pais, gamer_telefono) values "
            + "(?,?,?,?,?,?,?,?)";

    private static final String EDITAR_GAMER = "update gamer set"
            + " gamer_nombre = ?, nickname = ?, gamer_contraseña = ?, gamer_fecha_nacimiento = ?,"
            + " nombre_pais = ?,  gamer_telefono = ? where gamer_correo = ?";
    private static final String BUSCAR_GAMER_POR_CORREO = "select *from gamer where gamer_correo = ?";
    private static final String BUSCAR_GAMER_POR_NICKNAME = "select *from gamer where nickname = ?";
    private static final String AGREGAR_IMAGEN = "update gamer set avatar = ? where gamer_correo = ? ";
    private static final String CREAR_CARTERA_GAMER = "insert into cartera_gamer (correo_gamer, saldo) values (?, '0')";
    private static final String OBTENER_SALDO_TARJETA = "select saldo from cartera_gamer where correo_gamer = ?";
    private static final String RECARGAR_TARJETA = "update cartera_gamer set saldo = ? where correo_gamer = ? ";
    
    
    public void crearNuevoGamer(Gamer gamer) {

        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREATE_GAMER_QUERY)) {
            insert.setString(1, gamer.getNombre());
            insert.setString(2, gamer.getNickname());
            insert.setString(3, gamer.getCorreo());
            insert.setString(4, gamer.getConstraseña());
            insert.setInt(5, gamer.getEdad());
            insert.setDate(6, Date.valueOf(gamer.getFechaNacimiento()));
            insert.setString(7, gamer.getNombrePais());
            insert.setString(8, gamer.getTelefono());

            insert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarGamer(Gamer gamer) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(EDITAR_GAMER)) {
            insert.setString(1, gamer.getNombre());
            insert.setString(2, gamer.getNickname());
            insert.setString(3, gamer.getConstraseña());
            insert.setDate(4, Date.valueOf(gamer.getFechaNacimiento()));
            insert.setString(5, gamer.getNombrePais());
            insert.setString(6, gamer.getTelefono());
            insert.setString(7, gamer.getCorreo());
            insert.executeUpdate();
        } 
        
    }
    
    public void eliminarGamer(String correo) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ELIMINAR_GAMER)) {
            insert.setString(1, correo);
            insert.executeUpdate();
        }
        
    }

    public void crearCarteraGamer(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREAR_CARTERA_GAMER)) {
            insert.setString(1, correo);
            insert.executeUpdate();
        }
    }
    
    
    public GamerSimple buscarGamerSimplePorCorreo(String correo) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_GAMER_POR_CORREO)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                return new GamerSimple(result.getString("gamer_nombre"), result.getString("gamer_correo"));
            }
        }
        return null;
    }
    
    public Gamer buscarGamerPorCorreo(String correo) throws SQLException{
         try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_GAMER_POR_CORREO)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                return new Gamer(result.getString("gamer_nombre"),
                        result.getString("nombre_pais"),
                        result.getString("gamer_correo"),
                        result.getString("nickname"),
                        result.getString("gamer_contraseña"),
                        result.getString("gamer_telefono"),
                        result.getDate("gamer_fecha_nacimiento").toLocalDate()
                    );
            }
        }
        return null;
        
    }

    public void recargarTarjetaGamer(RecargoTarjeta recargo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(RECARGAR_TARJETA)) {
            insert.setDouble(1, recargo.getDeposito());
            insert.setString(2, recargo.getCorreoGamer());
            insert.executeUpdate();
        }
    }

    public double obtenerSaldoTarjeta(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_SALDO_TARJETA)) {
            insert.setString(1, correo);
            ResultSet result = insert.executeQuery();
            if(result.next()){
                return result.getDouble("saldo");
            }
        }
        return -1;
    }

    public boolean existeGamerConCorreo(String correo) {
        ValidadorUsuarioUnico validador = new ValidadorUsuarioUnico();
        return validador.usuarioYaExiste(correo);
    }

    public boolean existeGamerConNickname(String nickname) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(BUSCAR_GAMER_POR_NICKNAME);) {
            query.setString(1, nickname);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void agregarImagenGamer(AvatarGamer avatar) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(AGREGAR_IMAGEN);) {
            query.setBlob(1, avatar.getImagen());
            query.setString(2, avatar.getCorreo());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public byte [] recuperarImagen(String id) throws SQLException {
        ImagenesDB db = new ImagenesDB();
        return  db.recuperarImagen(RECUPERAR_IMAGEN, id, "avatar");
    }

}
