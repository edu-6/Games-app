/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.usuarios.UsuarioLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class ValidadorUsuarioUnico {
    
    private static final String ENCONTRAR_ADMIN_SISTEMA = "select *from admin_sistema where admin_correo = ?";
    private static final String ENCONTRAR_ADMIN_EMPRESA = "select *from admin_empresa where admin_correo = ?";
    private static final String ENCONTRAR_GAMER = "select *from gamer where gamer_correo = ?";
    
    
    private boolean existeUsuario(String correo, String consulta){
        try( Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(consulta)) {
            query.setString(1, correo);
            ResultSet rs = query.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    private boolean existeAdminSistema(String correo){
        return this.existeUsuario(correo, ENCONTRAR_ADMIN_SISTEMA);
    }
    
    private boolean existeAdminEmpresa(String correo){
        return this.existeUsuario(correo, ENCONTRAR_ADMIN_EMPRESA);
    }
    
    private boolean existeGamer(String correo){
        return this.existeUsuario(correo, ENCONTRAR_GAMER);
    }
    
    
    public boolean usuarioYaExiste(String correo){
        return existeAdminSistema(correo) || existeAdminEmpresa(correo) || existeGamer(correo);
    }
    
}
