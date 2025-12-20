/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.AdminSistemaDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistema;
import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistemaSimple;
import com.mycompany.rest.api.games.modelos.adminSistema.AvatarAdminSistema;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author edu
 */
public class AdminSistemaCrudService {

    public void crearAdmin(AdminSistema admin) throws DatosInvalidosException, SQLException, IdentidadRepetidaException {
        AdminSistemaDB db = new AdminSistemaDB();
        if (!admin.valido()) {
            throw new DatosInvalidosException("datos vacios o muy grandes");
        }

        if (db.existeAdmin(admin.getCorreo())) {
            throw new IdentidadRepetidaException("el correo " + admin.getCorreo() + " ya está asociado a una cuenta");
        }

        db.crearAdmin(admin);

    }
    
    public AdminSistema buscarAdminCompleto(String correo) throws NoEncontradoException, SQLException{
        AdminSistemaDB db = new AdminSistemaDB();
        AdminSistema admin = db.buscarAdminCompleto(correo);
        if(admin == null){
            throw new NoEncontradoException();
        }
        return admin;
    }

    public void editarAdmin(AdminSistema admin) throws DatosInvalidosException, SQLException {
        AdminSistemaDB db = new AdminSistemaDB();
        if (!admin.valido()) {
            throw new DatosInvalidosException("datos vacios o muy grandes");
        }

        db.editarAdmin(admin);

    }

    public void eliminarAdmin(String correo) throws DatosInvalidosException, SQLException, NoEncontradoException {
        AdminSistemaDB db = new AdminSistemaDB();
        if (correo == null) {
            throw new DatosInvalidosException("ingrese el correo");
        }

        if (!db.existeAdmin(correo)) {
            throw new NoEncontradoException();
        }

        db.eliminarAdmin(correo);
    }

    public ArrayList<AdminSistemaSimple> buscarAdmins(String parametro) throws DatosInvalidosException, SQLException, NoEncontradoException {
        AdminSistemaDB db = new AdminSistemaDB();
        if (parametro == null) {
            throw new DatosInvalidosException("ingrese el correo o nombre");
        }

        return db.buscarAdmins(parametro);
    }

    public ArrayList<AdminSistemaSimple> obtenerAdmins() throws SQLException {
        AdminSistemaDB db = new AdminSistemaDB();
        return db.obtenerAdmins();
    }

    public void agregarImagenAdmin(AvatarAdminSistema avatarGamer) {
        AdminSistemaDB db = new AdminSistemaDB();
        try {
            db.agregarImagen(avatarGamer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StreamingOutput recuperarImagen(String correo) throws SQLException, NoEncontradoException {
        AdminSistemaDB db = new AdminSistemaDB();
        
        byte [] imagen = db.recuperarImage(correo);
        if(imagen == null){
            throw new NoEncontradoException();
        }
        
        // retornar un straming output
        return new StreamingOutput() {
        @Override
        public void write(OutputStream os) throws IOException, WebApplicationException {
            os.write(imagen);
            os.flush();
        }
    };
    }
}
