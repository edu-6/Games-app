/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;
import com.mycompany.rest.api.games.db.AdminEmpresaDB;
import com.mycompany.rest.api.games.dtos.usuarios.adminEmpresa.AdminEmpresaRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresa;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresaSimple;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class AdminEmpresaCrudService extends CrudService {

    @Override
    public void crearEntidad(Entidad entidad) throws SQLException, IdentidadRepetidaException, DatosInvalidosException {
       AdminEmpresaDB db = new AdminEmpresaDB();
       AdminEmpresa admin = (AdminEmpresa) entidad;
       if(!admin.valido()){
           throw new DatosInvalidosException(" datos vacios o muy largos");
       }
       if(db.existeEntidad(admin.getCorreo())){
           throw new IdentidadRepetidaException(" el correo: "+ admin.getCorreo()+ " ya está en uso");
       }
       
       db.crearEntidad(entidad);
    }

    @Override
    public void editarEntidad(Entidad entidad) throws DatosInvalidosException, SQLException{
         AdminEmpresaDB db = new AdminEmpresaDB();
        AdminEmpresa admin = (AdminEmpresa) entidad; 
        if (!admin.valido()) {
            throw new DatosInvalidosException("datos vacios o muy grandes");
        }
        db.editarEntidad(admin);
       
    }
    
    @Override
    public void eliminarEntidad(String correo) throws DatosInvalidosException, NoEncontradoException, SQLException {
         AdminEmpresaDB db = new AdminEmpresaDB();
        if (correo == null) {
            throw new DatosInvalidosException("ingrese el correo");
        }
        if (!db.existeEntidad(correo)) {
            throw new NoEncontradoException();
        }
        db.eliminarEntidad(correo);
    }
    
    /**
     * Para buscar un admin por correo cuando se quiere editar
     * @param correo
     * @return 
     */
    public AdminEmpresa buscarAdminParaEditar(String correo) throws SQLException, NoEncontradoException{
         AdminEmpresaDB db = new AdminEmpresaDB();
         AdminEmpresa admin = db.buscarAdminCompleto(correo);
         if(admin == null){
             throw new NoEncontradoException();
         }
         return admin;
    }
    
    /**
     * Para buscar admins con correo o nombre dentro de una empresa
     * @param correoAdmin
     * @return
     * @throws DatosInvalidosException
     * @throws SQLException 
     */
    
    public ArrayList<AdminEmpresaSimple> buscarAdminsEnEmpresa(String correoAdmin) throws DatosInvalidosException, SQLException{
        AdminEmpresaDB db = new AdminEmpresaDB();
        String nombreEmpresa = db.obtenerNombreEmpresa(correoAdmin);
        return  db.buscarAdminsEmpresa(correoAdmin, nombreEmpresa);
    }
    
    /**
     * Para buscar todos los admins
     * @param correoAdmin
     * @return
     * @throws DatosInvalidosException
     * @throws SQLException 
     */
    public ArrayList<AdminEmpresaSimple> obtenerTodosLosAdmins(String correoAdmin) throws DatosInvalidosException, SQLException{
        AdminEmpresaDB db = new AdminEmpresaDB();
        String nombreEmpresa = db.obtenerNombreEmpresa(correoAdmin);
        return db.obtenerAdmins(nombreEmpresa);
    }
    
    
    
    private AdminEmpresa extraerAdmin(AdminEmpresaRequest request) throws SQLException{
        AdminEmpresaDB db = new AdminEmpresaDB();
        String nombreEmpresa = db.obtenerNombreEmpresa(request.getCorreoAdminCreador());
        return new AdminEmpresa(
                request.getNombre(),
                request.getCorreo(),
                request.getContrasena(),
                request.getFechaNacimiento(),
                nombreEmpresa
        );
    }
}
