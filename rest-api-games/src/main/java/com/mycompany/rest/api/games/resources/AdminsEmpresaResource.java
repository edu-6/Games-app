/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.db.AdminEmpresaDB;
import com.mycompany.rest.api.games.dtos.usuarios.adminEmpresa.AdminEmpresaRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresa;
import com.mycompany.rest.api.games.modelos.empresas.admin.AdminEmpresaSimple;
import com.mycompany.rest.api.games.servicios.AdminEmpresaCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author edu
 */
@Path("admins-empresa")
public class AdminsEmpresaResource {    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearNuevoAdmin(AdminEmpresaRequest admin) throws SQLException, IdentidadRepetidaException {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        try {
            crudService.crearEntidad(admin);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarAdmin(AdminEmpresaRequest admin) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        try {
            crudService.editarEntidad(admin);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("{correo}")
    public Response eliminarAdmin(@PathParam("correo") String correo) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        try {
            crudService.eliminarEntidad(correo);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException | NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    @GET
    @Path("adminUnico/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAdminParaEditar(@PathParam("correo") String parametro) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        try {
            AdminEmpresa admin = crudService.buscarAdminParaEditar(parametro);
            return Response.ok(admin).build();
        } 
        catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAdmins(@PathParam("correo") String correoAdmin) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();

        try {
            ArrayList<AdminEmpresaSimple> lista = crudService.buscarAdminsEnEmpresa(correoAdmin);
            return Response.ok(lista).build();
        } catch (DatosInvalidosException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("admins/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAdmins(@PathParam("correo") String correoAdmin){
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();

        ArrayList<AdminEmpresaSimple> lista;
        try {
            lista = crudService.obtenerTodosLosAdmins(correoAdmin);
            return Response.ok(lista).build();
        } catch (DatosInvalidosException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
        
    }


    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response agregarFotoAdmin(@FormDataParam("correo") String correo, @FormDataParam("imagen") InputStream imagenCargada) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        AdminEmpresaDB db= new AdminEmpresaDB();
        crudService.agregarImagen(new AvatarEntidad(correo, imagenCargada), db);
        return Response.status(Response.Status.OK).build();
    }
    


    /**
     * Para devolver la imagen MediaType.APPLICATION_OCTET_STREAM porque no se sabe el formato
     * @return
     */
    @GET
    @Path("imagenes/{correo}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response recuperarImagen(@PathParam("correo") String correo) {
        AdminEmpresaCrudService crudService = new AdminEmpresaCrudService();
        AdminEmpresaDB db= new AdminEmpresaDB();
        try {
            StreamingOutput stream = crudService.recuperarImagen(correo, db);
            
            if (stream == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(stream).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
           // return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
    
}
