/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.usuarios.UsuarioLogin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author edu
 */
public class LoginDB {
    
    private static final String ENCONTRAR_ADMIN_SISTEMA = "select *from admin_sistema where admin_correo = ? and admin_contraseña = ?";
    private static final String ENCONTRAR_ADMIN_EMPRESA = "select *from admin_empresa where admin_correo = ? and admin_contraseña =?";
    private static final String ENCONTRAR_GAMER = "select *from gamer where gamer_correo = ? and gamer_contraseña =?";
    
    
    private boolean existeUsuario(UsuarioLogin usuario, String consulta){
        try( Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(consulta)) {
            query.setString(1, usuario.getCorreo());
            query.setString(2, usuario.getContraseña());
            
            ResultSet rs = query.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean existeAdminSistema(UsuarioLogin usuario){
        return this.existeUsuario(usuario, ENCONTRAR_ADMIN_SISTEMA);
    }
    
    public boolean existeAdminEmpresa(UsuarioLogin usuario){
        return this.existeUsuario(usuario, ENCONTRAR_ADMIN_EMPRESA);
    }
    
    public boolean existeGamer(UsuarioLogin usuario){
        return this.existeUsuario(usuario, ENCONTRAR_GAMER);
    }
    
    
    
    
    
    
}
