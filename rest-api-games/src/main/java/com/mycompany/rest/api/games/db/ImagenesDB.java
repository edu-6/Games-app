/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ImagenesDB {
    
    public byte [] recuperarImagen(String peticion, String id, String nombreColumna) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(peticion);) {
            query.setString(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                return rs.getBytes(nombreColumna);
            }
            return null;
        }
    }
    
}
