/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.grupos.Grupo;
import com.mycompany.rest.api.games.modelos.grupos.GrupoCompleto;
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoRequest;
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoResponse;
import com.mycompany.rest.api.games.servicios.GruposCrudService;
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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu
 */
@Path("grupos")
public class GruposResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearGrupo(Grupo grupo) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            crudService.crearEntidad(grupo);
            return Response.status(Response.Status.CREATED).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarGrupo(GrupoCompleto edicion) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            crudService.editarEntidad(edicion);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarGrupo(@PathParam("id") String id) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            GrupoCompleto grupo = crudService.obtenerPorId(id);return Response.ok(grupo).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarGrupo(@PathParam("id") String nombre) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            crudService.eliminarEntidad(nombre);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException | NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
    
    @GET
    @Path("grupos-admin/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarGruposDelAdmin(@PathParam("correo") String correo) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            List<GrupoCompleto> grupos = crudService.obtenerGruposPorAdmin(correo);
            return Response.ok(grupos).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
    
    
    
    @POST
    @Path("añadir-participante")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response añadirParticipante(ParticipanteGrupoRequest req) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            crudService.añadirParticipante(req);
            return Response.status(Response.Status.CREATED).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (NoEncontradoException ex) {
             return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @GET
    @Path("participantes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerParticipantes(@PathParam("id") int id) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            List<ParticipanteGrupoResponse> participantes = crudService.obtenerTodosLosParticipantes(id);
            return Response.ok(participantes).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("eliminar-participante/{id}/{correo}")
    public Response eliminarParticipante(@PathParam("id") int id, @PathParam("correo") String correo) {
        GruposCrudService crudService = new GruposCrudService();
        try {
            crudService.eliminarParticipante(correo, id);
            return Response.status(Response.Status.OK).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }
    
    
}
