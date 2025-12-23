/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.adminSistema.AvatarAdminSistema;
import com.mycompany.rest.api.games.modelos.empresas.Empresa;
import com.mycompany.rest.api.games.modelos.empresas.EmpresaEdicion;
import com.mycompany.rest.api.games.servicios.AdminSistemaCrudService;
import com.mycompany.rest.api.games.servicios.EmpresasCrudService;
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
@Path("empresas")
public class EmpresasResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearEmpresa(Empresa empresa) {
        EmpresasCrudService crudService = new EmpresasCrudService();
        try {
            crudService.crearEmpresa(empresa);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarEmpresa(EmpresaEdicion edicion) {
        EmpresasCrudService crudService = new EmpresasCrudService();
        try {
            crudService.editarEmpresa(edicion);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
             return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEmpresas() {
        EmpresasCrudService crudService = new EmpresasCrudService();
        ArrayList<Empresa> Empresas;
        try {
            Empresas = crudService.obtenerEmpresas();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
        return Response.ok(Empresas).build();

    }

    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEmpresa(@PathParam("nombre") String nombre) {
        System.out.println(nombre);
        EmpresasCrudService crudService = new EmpresasCrudService();

        try {
           Empresa Empresa =  crudService.buscarEmpresa(nombre);
            return Response.ok(Empresa).build();
        } catch (DatosInvalidosException |NoEncontradoException  ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException ex) {
             return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        } 
    }
    
    
    @DELETE
    @Path("{nombre}")
    public Response eliminarEmpresa(@PathParam("nombre") String nombre) {
        EmpresasCrudService crudService = new EmpresasCrudService();

        try {
            crudService.eliminarEmpresa(nombre);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException |NoEncontradoException  ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } 
    }
    
    
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response agregarFotoAdmin(@FormDataParam("correo") String correo, @FormDataParam("imagen") InputStream imagenCargada) {

        EmpresasCrudService crudService = new EmpresasCrudService();
        crudService.agregarImagen(new AvatarEntidad(correo, imagenCargada));
        return Response.status(Response.Status.OK).build();

    }

    /**
     * Para devolver la imagen MediaType.APPLICATION_OCTET_STREAM porque no se sabe el formato
     *
     * @return
     */
    @GET
    @Path("imagenes/{correo}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response recuperarImagen(@PathParam("correo") String id) {
        EmpresasCrudService crudService = new EmpresasCrudService();
        try {
            StreamingOutput stream = crudService.recuperarImagen(id);
            
            if (stream == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(stream).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
}
