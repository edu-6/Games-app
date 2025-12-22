/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistema;
import com.mycompany.rest.api.games.modelos.adminSistema.AvatarAdminSistema;
import com.mycompany.rest.api.games.modelos.usuarios.UsuarioSimple;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class AdminSistemaDB {

    private static final String CREAR_ADMIN = "INSERT INTO admin_sistema (admin_nombre, admin_correo, admin_contraseña) VALUES (?, ?, ?)";
    private static final String EDITAR_ADMIN = "UPDATE admin_sistema SET admin_nombre = ?, admin_contraseña = ? WHERE admin_correo = ?";
    private static final String BUSCAR_POR_CORREO = "SELECT * FROM admin_sistema WHERE admin_correo = ?";
    private static final String ELIMINAR_ADMIN = "DELETE FROM admin_sistema WHERE admin_correo = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM admin_sistema";
    private static final String AGREGAR_IMAGEN = "update admin_sistema set admin_avatar = ? where admin_correo = ? ";
    private static final String BUSCAR_ADMINS = "select * from admin_sistema where admin_correo = ? or admin_nombre = ?";
    private static final String RECUPERAR_IMAGEN = "select admin_avatar from admin_sistema where admin_correo = ? ";

    public void crearAdmin(AdminSistema admin) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getCorreo());
            ps.setString(3, admin.getContraseña());

            ps.executeUpdate();
        }
    }

    public boolean existeAdmin(String parametro) throws SQLException {
        ValidadorUsuarioUnico validador = new ValidadorUsuarioUnico();
        return validador.usuarioYaExiste(parametro);
    }

    public ArrayList<UsuarioSimple> buscarAdmins(String parametro) throws SQLException {
        ArrayList<UsuarioSimple> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_ADMINS)) {
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                UsuarioSimple admin = new UsuarioSimple();
                admin.setNombre(result.getString("admin_nombre"));
                admin.setCorreo(result.getString("admin_correo"));
                lista.add(admin);
            }
        }
        return lista;
    }

    public ArrayList<UsuarioSimple> obtenerAdmins() throws SQLException {
        ArrayList<UsuarioSimple> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(OBTENER_TODOS)) {

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                UsuarioSimple admin = new UsuarioSimple();
                admin.setNombre(result.getString("admin_nombre"));
                admin.setCorreo(result.getString("admin_correo"));
                lista.add(admin);
            }
        }
        return lista;
    }
    
    
    public AdminSistema buscarAdminCompleto(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_POR_CORREO)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                AdminSistema admin = new AdminSistema();
                admin.setNombre(result.getString("admin_nombre"));
                admin.setCorreo(result.getString("admin_correo"));
                admin.setContraseña(result.getString("admin_contraseña"));
                return admin;
            }
        }
        return null;
    }

    public void editarAdmin(AdminSistema admin) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EDITAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getContraseña());
            ps.setString(3, admin.getCorreo());

            ps.executeUpdate();
        }
    }

    public void eliminarAdmin(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ELIMINAR_ADMIN)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }

    public void agregarImagen(AvatarAdminSistema avatar) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(AGREGAR_IMAGEN);) {
            query.setBlob(1, avatar.getImagen());
            query.setString(2, avatar.getCorreo());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public byte [] recuperarImage(String correo) throws SQLException {
        ImagenesDB db = new ImagenesDB();
        return  db.recuperarImagen(RECUPERAR_IMAGEN, correo, "admin_avatar");
    }
}
