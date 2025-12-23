/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistema;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresa;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresaSimple;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class AdminEmpresaDB extends Crud{
    public static final String CREAR_ADMIN = "INSERT INTO admin_empresa (admin_nombre, admin_correo, admin_contraseña, admin_fecha_nacimiento, nombre_empresa) VALUES (?, ?, ?, ?, ?)";
    public static final String EDITAR_ADMIN = "UPDATE admin_empresa SET admin_nombre = ?, admin_contraseña = ?, admin_fecha_nacimiento = ? WHERE admin_correo = ?"; 
    public static final String ELMINAR_ADMIN= "DELETE FROM admin_empresa WHERE admin_correo = ?";
    public static final String BUSCAR_ADMIN_POR_CORREO = "SELECT * FROM admin_empresa WHERE admin_correo = ?";
    public static final String BUSCAR_ADMIN_DENTRO_EMPRESA = "SELECT * FROM admin_empresa WHERE admin_correo = ? and  nombre_empresa = ?";
    public static final String BUSCAR_TODOS = "SELECT * FROM admin_empresa where nombre_empresa = ?";
    public static final String RECUPERAR_IMAGEN = "select admin_avatar form admin_empresa where admin_correo = ?";
    public static final String AGREGAR_IMAGEN = "update admin_empresa set admin_avatar = ? where admin_correo = ?";
    public static final String OBTENER_NOMBRE_EMPRESA = "select nombre_empresa from admin_empresa where admin_correo = ? "; 
    

    @Override
    public boolean existeEntidad(String correo) throws SQLException {
        ValidadorUsuarioUnico validador = new ValidadorUsuarioUnico();
        return validador.usuarioYaExiste(correo);
    }

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException {
        AdminEmpresa admin = (AdminEmpresa) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getCorreo());
            ps.setString(3, admin.getContraseña());
            ps.setDate(4, Date.valueOf(admin.getFechaNacimiento()));
            ps.setString(5, admin.getNombreEmpresa());
            ps.executeUpdate();
        }
    }

    @Override
    public void editarEntidad(Entidad entidad) throws SQLException {
        AdminEmpresa admin = (AdminEmpresa) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EDITAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getContraseña());
            ps.setString(3, admin.getCorreo());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarEntidad(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(ELMINAR_ADMIN)) {
            ps.setString(1, correo);
            ps.executeUpdate();
        }
    }


    @Override
    public byte [] recuperarImagen(String correo) throws SQLException, NoEncontradoException {
        ImagenesDB db = new ImagenesDB();
        return  db.recuperarImagen(RECUPERAR_IMAGEN, correo, "admin_avatar");
    }
    
    
    public String obtenerNombreEmpresa(String correo) throws SQLException{
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(OBTENER_NOMBRE_EMPRESA)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                return result.getString("nombre_empresa");
            }
        }
        return null;
    }

    /**
     * Sirve para buscar admins dentro deuna empresa con correo o nombre
     * @param parametro
     * @param nombreEmpresa
     * @return
     * @throws SQLException 
     */
    public ArrayList<AdminEmpresaSimple> buscarAdminsEmpresa(String parametro, String nombreEmpresa) throws SQLException {
        ArrayList<AdminEmpresaSimple> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_TODOS)) {
            ps.setString(1,parametro);
            ps.setString(2, parametro);
            ps.setString(3, nombreEmpresa);
            ResultSet result = ps.executeQuery();
            return  construirListaAdmins(result);
        }
    }

    /**
     * Sirve para traer todos los admins dentro de una empresa con vista simple
     * @param nombreEmpresa
     * @return
     * @throws SQLException 
     */
    public ArrayList<AdminEmpresaSimple> obtenerAdmins(String nombreEmpresa) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_TODOS)) {
            ps.setString(1, nombreEmpresa);
            ResultSet result = ps.executeQuery();
            return  construirListaAdmins(result);
        }
    }
    
    /**
     * Para constuir un admin  simple, sin contraseña etcc
     * @param result
     * @return
     * @throws SQLException 
     */
    private ArrayList<AdminEmpresaSimple> construirListaAdmins(ResultSet result) throws SQLException{
        ArrayList<AdminEmpresaSimple> lista = new ArrayList<>();
        while (result.next()) {
                AdminEmpresaSimple admin = new AdminEmpresaSimple();
                admin.setNombre(result.getString("admin_nombre"));
                admin.setCorreo(result.getString("admin_correo"));
                lista.add(admin);
            }
        return lista;
    }
    
    /**
     * para obtener un admin especifico con solo el correo
     * generalmente cuando se da "click" en editar
     * @param correo
     * @return
     * @throws SQLException 
     */
    public AdminEmpresa buscarAdminCompleto(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_ADMIN_POR_CORREO)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                AdminEmpresa admin = new AdminEmpresa();
                admin.setNombre(result.getString("admin_nombre"));
                admin.setCorreo(result.getString("admin_correo"));
                admin.setContraseña(result.getString("admin_contraseña"));
                admin.setFechaNacimiento(result.getDate("admin_fecha_nacimiento").toLocalDate());
                return admin;
            }
        }
        return null;
    }


    @Override
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
