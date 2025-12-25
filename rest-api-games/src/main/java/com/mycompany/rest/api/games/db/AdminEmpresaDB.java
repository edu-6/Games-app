/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;

import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.Entidad;
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
public class AdminEmpresaDB extends Crud {

    public static final String CREAR_ADMIN = "INSERT INTO admin_empresa (admin_nombre, admin_correo, admin_contraseña, admin_fecha_nacimiento, id_empresa) VALUES (?, ?, ?, ?, ?)";
    public static final String EDITAR_ADMIN = "UPDATE admin_empresa SET admin_nombre = ?, admin_contraseña = ?, admin_fecha_nacimiento = ? WHERE admin_correo = ?";
    public static final String ELMINAR_ADMIN = "DELETE FROM admin_empresa WHERE admin_correo = ?";
    public static final String BUSCAR_ADMIN_POR_CORREO = "SELECT * FROM admin_empresa WHERE admin_correo = ?";
    public static final String BUSCAR_ADMIN_DENTRO_EMPRESA = "SELECT * FROM admin_empresa WHERE admin_correo = ? and  id_empresa = ?";
    public static final String BUSCAR_TODOS = "SELECT * FROM admin_empresa where id_empresa = ?";
    public static final String RECUPERAR_IMAGEN = "select admin_avatar from admin_empresa where admin_correo = ?";
    public static final String AGREGAR_IMAGEN = "update admin_empresa set admin_avatar = ? where admin_correo = ?";
    public static final String OBTENER_ID_EMPRESA = "select id_empresa from admin_empresa where admin_correo = ? ";
    public static final String OBTENER_ID_EMPRESA_POR_CORREO_ADMIN = "select empresa.id_empresa from empresa"
            + " join admin_empresa on admin_empresa.id_empresa = empresa.id_empresa where admin_correo = ? ";
    public static final String OBTENER_ID_EMPRESA_POR_NOMBRE_EMPRESA = "select id_empresa from empresa WHERE nombre_empresa = ? ";
    
     public static final String BUSCAR_ADMINS_CON_NOMBRE_O_CORREO = "SELECT * FROM admin_empresa WHERE  admin_correo = ? or admin_nombre = ? and id_empresa = ?";

    @Override
    public boolean existeEntidad(String correo) throws SQLException {
        ValidadorUsuarioUnico validador = new ValidadorUsuarioUnico();
        return validador.usuarioYaExiste(correo);
    }

    private int buscarIdEmpresa(String query, String parametro) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, parametro);
            ResultSet result =  ps.executeQuery();
            if(result.next()){
                return result.getInt("id_empresa");
            }
        }
        return -1;
    }

    /**
     * Para encontrar el id de una empresa tengiendo el correo de un admin o el
     * nombre de la empresa
     *
     * @param parametro
     * @return
     * @throws SQLException
     */
    public int encontrarIdEmpresa(String parametro) throws SQLException {
        
        int idEmpresa = this.buscarIdEmpresa(OBTENER_ID_EMPRESA_POR_CORREO_ADMIN, parametro);
        if(idEmpresa > 0){
            return idEmpresa;
        }
        int idEmpresa2 = this.buscarIdEmpresa(OBTENER_ID_EMPRESA_POR_NOMBRE_EMPRESA, parametro);
        if(idEmpresa2 >0){
            return idEmpresa2;
        }
        return 0;
    }

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException {
        AdminEmpresa admin = (AdminEmpresa) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(CREAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getCorreo());
            ps.setString(3, admin.getContraseña());
            ps.setDate(4, Date.valueOf(admin.getFechaNacimiento()));
            ps.setInt(5, admin.getIdEmpresa());
            ps.executeUpdate();
        }
    }

    @Override
    public void editarEntidad(Entidad entidad) throws SQLException {
        AdminEmpresa admin = (AdminEmpresa) entidad;
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(EDITAR_ADMIN)) {
            ps.setString(1, admin.getNombre());
            ps.setString(2, admin.getContraseña());
            ps.setDate(3, Date.valueOf(admin.getFechaNacimiento()));
            ps.setString(4, admin.getCorreo());
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
    
    public int obtenerNombreEmpresa(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(OBTENER_ID_EMPRESA)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return result.getInt("id_empresa");
            }
        }
        return -1;
    }
    
    /**
     * para obtener un admin especifico con solo el correo generalmente cuando
     * se da "click" en editar
     *
     * @param correo
     * @return
     * @throws SQLException
     */
    public AdminEmpresa buscarAdminCompleto(String correo) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_ADMIN_POR_CORREO)) {
            ps.setString(1, correo);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
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

    /**
     * Sirve para buscar admins dentro deuna empresa con correo o nombre
     *
     * @param parametro
     * @param nombreEmpresa
     * @return
     * @throws SQLException
     */
    public ArrayList<AdminEmpresaSimple> buscarAdminsEmpresa(String parametro, int id_empresa) throws SQLException {
        ArrayList<AdminEmpresaSimple> lista = new ArrayList<>();
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_ADMINS_CON_NOMBRE_O_CORREO)) {
            ps.setString(1, parametro);
            ps.setString(2, parametro);
            ps.setInt(3, id_empresa);
            ResultSet result = ps.executeQuery();
            return construirListaAdmins(result);
        }
    }

    /**
     * Sirve para traer todos los admins dentro de una empresa con vista simple
     *
     * @param nombreEmpresa
     * @return
     * @throws SQLException
     */
    public ArrayList<AdminEmpresaSimple> obtenerAdmins(int id_empresa) throws SQLException {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement ps = connection.prepareStatement(BUSCAR_TODOS)) {
            ps.setInt(1, id_empresa);
            ResultSet result = ps.executeQuery();
            return construirListaAdmins(result);
        }
    }

    /**
     * Para constuir un admin simple, sin contraseña etcc
     *
     * @param result
     * @return
     * @throws SQLException
     */
    private ArrayList<AdminEmpresaSimple> construirListaAdmins(ResultSet result) throws SQLException {
        ArrayList<AdminEmpresaSimple> lista = new ArrayList<>();
        while (result.next()) {
            AdminEmpresaSimple admin = new AdminEmpresaSimple();
            admin.setNombre(result.getString("admin_nombre"));
            admin.setCorreo(result.getString("admin_correo"));
            lista.add(admin);
        }
        return lista;
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
    
    @Override
    public byte[] recuperarImagen(String correo) throws SQLException, NoEncontradoException {
        ImagenesDB db = new ImagenesDB();
        return db.recuperarImagen(RECUPERAR_IMAGEN, correo, "admin_avatar");
    }

}
