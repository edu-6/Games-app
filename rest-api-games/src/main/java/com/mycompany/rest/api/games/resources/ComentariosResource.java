/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.dtos.comentarios.ComentarioRequest;
import com.mycompany.rest.api.games.dtos.comentarios.ComentarioResponse;
import com.mycompany.rest.api.games.dtos.comentarios.SubcomentarioRequest;
import com.mycompany.rest.api.games.dtos.comentarios.SubcomentarioResponse;
import com.mycompany.rest.api.games.servicios.ComentariosCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author edu
 */
@Path("comentarios")
public class ComentariosResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearComentario(ComentarioRequest nuevo) {
        ComentariosCrudService crudService = new ComentariosCrudService();
        try {
            crudService.registrarComentario(nuevo);
            return Response.status(Response.Status.CREATED).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse("Error al guardar en la base de datos: " + e.getMessage())).build();
        }
    }

    @GET
    @Path("/{nombreJuego}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentarios(@PathParam("nombreJuego") String idJuego) {
        ComentariosCrudService crudService = new ComentariosCrudService();
        try {
            List<ComentarioResponse> comentarios = crudService.obtenerComentariosPorJuego(idJuego);
            return Response.ok(comentarios).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Error al recuperar comentarios: " + e.getMessage())).build();
        }
    }
    
    
    @POST
    @Path("/respuesta")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearSubcomentario(SubcomentarioRequest nuevaRespuesta) {
        ComentariosCrudService crudService = new ComentariosCrudService();
        try {
            crudService.registrarSubcomentario(nuevaRespuesta);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("/respuestas/{idPadre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerRespuestas(@PathParam("idPadre") int idPadre) {
        ComentariosCrudService crudService = new ComentariosCrudService();
        try {
            List<SubcomentarioResponse> respuestas = crudService.obtenerRespuestasPorComentario(idPadre);
            return Response.ok(respuestas).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    
    @GET
    @Path("/permisos/{nombreJuego}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarPermisos(@PathParam("nombreJuego") String nombreJuego) {
        ComentariosCrudService service = new ComentariosCrudService();
        try {
            boolean permitido = service.verificarPermisoComentarios(nombreJuego);
            // se usa map para hacer un json para el parametro 
            return Response.ok(java.util.Collections.singletonMap("permitido", permitido)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
