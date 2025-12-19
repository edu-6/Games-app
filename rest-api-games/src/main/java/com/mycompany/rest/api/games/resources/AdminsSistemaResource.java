/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistema;
import com.mycompany.rest.api.games.modelos.adminSistema.AdminSistemaSimple;
import com.mycompany.rest.api.games.modelos.adminSistema.AvatarAdminSistema;
import com.mycompany.rest.api.games.servicios.AdminSistemaCrudService;

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
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author edu
 */
@Path("admins-sistema")
public class AdminsSistemaResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearNuevoAdmin(AdminSistema admin) {
        AdminSistemaCrudService crudService = new AdminSistemaCrudService();
        try {
            crudService.crearAdmin(admin);

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
    public Response editarAdmin(AdminSistemaSimple admin){
        AdminSistemaCrudService crudService = new AdminSistemaCrudService();
        try {
            crudService.editarAdmin(admin);
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
        AdminSistemaCrudService crudService = new AdminSistemaCrudService();
        try {
            crudService.eliminarAdmin(correo);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException |NoEncontradoException  ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    
    @GET
    @Path("{nombre_correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCategoria(@PathParam("nombre_correo") String parametro) {
       AdminSistemaCrudService crudService = new AdminSistemaCrudService();

        try {
           ArrayList<AdminSistemaSimple> lista =  crudService.buscarAdmins(parametro);
            return Response.ok(lista).build();
        } catch (DatosInvalidosException |NoEncontradoException  ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAdmins() {
        AdminSistemaCrudService crudService = new AdminSistemaCrudService();

        try {
           ArrayList<AdminSistemaSimple> lista =  crudService.obtenerAdmins();
            return Response.ok(lista).build();
        } catch (SQLException e) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response agregarFotoAdminr(@FormDataParam("correo") String correo, @FormDataParam("imagen") InputStream imagenCargada) {

        AdminSistemaCrudService crudService = new AdminSistemaCrudService();
        crudService.agregarImagenAdmin(new AvatarAdminSistema(correo, imagenCargada));
        return Response.status(Response.Status.CREATED).build();

    }

}
