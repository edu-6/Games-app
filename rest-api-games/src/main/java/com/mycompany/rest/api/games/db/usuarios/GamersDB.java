/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db.usuarios;

import com.mycompany.rest.api.games.db.DBConnectionSingleton;
import com.mycompany.rest.api.games.modelos.gamers.Gamer;
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
    private static final String CREATE_GAMER_QUERY = "insert into gamer (gamer_nombre, nickname, gamer_correo,gamer_contraseña,gamer_edad, gamer_fecha_nacimiento,nombre_pais, gamer_telefono) values "
            + "(?,?,?,?,?,?,?,?)";
    
    private static final String BUSCAR_GAMER_POR_CORREO = "select *from gamer where gamer_correo = ?";
    private static final String BUSCAR_GAMER_POR_NICKNAME = "select *from gamer where nickname = ?";
    
    
    public void crearNuevoGamer(Gamer gamer){
        
        try( Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREATE_GAMER_QUERY)) {
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
    
    
    public boolean existeGamerConCorreo(String correo) {
        
        try ( Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(BUSCAR_GAMER_POR_CORREO);) {
            query.setString(1, correo);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean existeGamerConNickname(String nickname) {
        try ( Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(BUSCAR_GAMER_POR_NICKNAME);) {
            query.setString(1, nickname);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
