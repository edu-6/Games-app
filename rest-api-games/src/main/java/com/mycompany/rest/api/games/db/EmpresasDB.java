/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.db;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.empresas.Empresa;
import com.mycompany.rest.api.games.modelos.empresas.EmpresaEdicion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class EmpresasDB {
    
        private static final String CREAR_EMPRESA = "insert into empresa (nombre_empresa, permite_comentarios) values (?,?)";
        private static final String EDITAR_EMPRESA = "update  empresa set nombre_empresa  = ?, permite_comentarios  = ? where nombre_empresa = ?";
        private static final String BUSCAR_EMPRESA = "select *from empresa where nombre_empresa = ?";
        private static final String ELIMINAR_EMPRESA = "delete from empresa where nombre_empresa = ?";
        private static final String RECUPERAR_IMAGEN = "select empresa_avatar from empresa where nombre_empresa = ?";
        private static final String AGREGAR_IMAGEN = "UPDATE  empresa set empresa_avatar = ? where nombre_empresa = ?";
        private static final String OBTENER_TODAS_LAS_EMPRESAS = "select * from empresa";

        public boolean existeEmpresa(String nombre) {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_EMPRESA)) {
                insert.setString(1, nombre);
                ResultSet result = insert.executeQuery();
                return result.next();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public Empresa buscarEmpresa(String nombre) throws SQLException {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(BUSCAR_EMPRESA)) {
                insert.setString(1, nombre);
                ResultSet result = insert.executeQuery();
                if(result.next()){
                    return new Empresa(result.getString("nombre_empresa"), result.getBoolean("permite_comentarios"));
                }
                return null;
            }
        }

        public void crearEmpresa(Empresa empresa) throws SQLException {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(CREAR_EMPRESA)) {
                insert.setString(1, empresa.getNombre());
                insert.setBoolean(2, empresa.isAceptaComentarios());
                insert.executeUpdate();
            }
        }

        public void editarEmpresa(EmpresaEdicion empresa) throws SQLException {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(EDITAR_EMPRESA)) {
                insert.setString(1, empresa.getNuevoNombre());
                insert.setBoolean(2, empresa.isAceptaComentarios());
                insert.setString(3, empresa.getNombre());

                insert.executeUpdate();
            }
        }

        public void eliminarEmpresa(String nombre) {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(ELIMINAR_EMPRESA)) {
                insert.setString(1, nombre);
                insert.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<Empresa> obtenerEmpresas() throws SQLException {
            try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement insert = connection.prepareStatement(OBTENER_TODAS_LAS_EMPRESAS)) {
                ArrayList<Empresa> lista = new ArrayList();
                ResultSet result = insert.executeQuery();

                while (result.next()) {
                    lista.add(new Empresa(result.getString("nombre_empresa"), result.getBoolean("permite_comentarios")));
                }
                return lista;
            }
        }
        
        
        public void agregarImagen(AvatarEntidad avatar) {
        try (Connection connection = DBConnectionSingleton.getInstance().getConnection(); PreparedStatement query = connection.prepareStatement(AGREGAR_IMAGEN);) {
            query.setBlob(1, avatar.getImagen());
            query.setString(2, avatar.getIdIdentidad());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public byte [] recuperarImagen(String id) throws SQLException {
        ImagenesDB db = new ImagenesDB();
        return  db.recuperarImagen(RECUPERAR_IMAGEN, id, "empresa_avatar");
    }
    }

